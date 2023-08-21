package com.framework.common.util.encrypt;

import com.framework.common.util.other.CodingUtil;
import com.framework.common.util.other.NumeralUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.util.encrypt
 * @description RSA加密解密工具类
 * @date 2020/3/24 13:55
 */
public class RSAEncrypt {
    //算法种类
    public static final String KEY_ALGORITHM = "RSA";
    //获取公钥键
    public static final String PUBLIC_KEY = "RSAPublicKey";
    //获取私钥键
    public static final String PRIVATE_KEY = "RSAPrivateKey";
    // MAX_DECRYPT_BLOCK应等于密钥长度/8（1byte=8bit），所以当密钥位数为2048时，最大解密长度应为256.
    // 128 对应 1024，256对应2048
    private static final int KEYSIZE = 2048;

    // RSA最大加密明文大小
    private static final int MAX_ENCRYPT_BLOCK = 117;

    // RSA最大解密密文大小
//	private static final int MAX_DECRYPT_BLOCK = 128;
    private static final int MAX_DECRYPT_BLOCK = KEYSIZE / 8;

    /**
     * @return java.util.Map
     * @title RSA生成密钥对(公钥和私钥)
     * @description RSA生成密钥对(公钥和私钥)
     * @author 邋遢龘鵺
     * @datetime 2020/3/24 14:03
     */
    public static Map<String, String> genKeyPair() throws Exception {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        // 初始化密钥对生成器，密钥大小为1024-2048位
        keyPairGen.initialize(KEYSIZE, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        Map<String, String> keyMap = new HashMap<String, String>(NumeralUtil.POSITIVE_TWO);
        keyMap.put(PUBLIC_KEY, publicKeyString);
        keyMap.put(PRIVATE_KEY, privateKeyString);
        return keyMap;
    }

    /**
     * @param publicKey 1 公钥字符串
     * @return java.security.interfaces.RSAPublicKey
     * @titel RSA从字符串加载公钥
     * @description RSA从字符串加载公钥
     * @author 邋遢龘鵺
     * @datetime 2020/3/24 14:14
     */
    public static RSAPublicKey loadPublicKeyByStr(String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(decoded));
        return pubKey;
    }

    /**
     * @param privateKey 1 私钥字符串
     * @return java.security.interfaces.RSAPrivateKey
     * @titel RSA从字符串加载私钥
     * @description RSA从字符串加载私钥
     * @author 邋遢龘鵺
     * @datetime 2020/3/24 14:20
     */
    public static RSAPrivateKey loadPrivateKeyByStr(String privateKey) throws Exception {
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        return priKey;
    }

    /**
     * @param value     1 加密参数
     * @param publicKey 2 加密公钥
     * @return java.lang.String
     * @titel RSA公钥加密
     * @description RSA公钥加密
     * @author 邋遢龘鵺
     * @datetime 2020/3/24 14:26
     */
    public static String encryptPublic(String value, String publicKey) throws Exception {
        //base64编码的公钥
        RSAPublicKey pubKey = loadPublicKeyByStr(publicKey);
        //RSA加密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(value.getBytes(CodingUtil.CODING_UTF8)));
        return outStr;
    }


    /**
     * @param value     1 加密参数
     * @param publicKey 2 加密公钥
     * @return java.lang.String
     * @titel RSA分段公钥加密
     * @description RSA分段公钥加密
     * @author 邋遢龘鵺
     * @datetime 2020/3/24 14:26
     */
    public static String encryptPublicSubsection(String value, String publicKey) throws Exception {
        //base64编码的公钥
        RSAPublicKey pubKey = loadPublicKeyByStr(publicKey);
        //RSA加密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        // 分段加密
        byte[] data = value.getBytes(CodingUtil.CODING_UTF8);
        // 加密时超过117字节就报错。为此采用分段加密的办法来加密
        byte[] enBytes = null;
        for (int i = NumeralUtil.POSITIVE_ZERO; i < data.length; i += MAX_ENCRYPT_BLOCK) {
            // 注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + MAX_ENCRYPT_BLOCK));
            enBytes = ArrayUtils.addAll(enBytes, doFinal);
        }
        String outStr = Base64.encodeBase64String(enBytes);
        return outStr;


    }

    /**
     * @param value      1 加密参数
     * @param privateKey 2 加密私钥
     * @return java.lang.String
     * @titel RSA私钥加密
     * @description RSA私钥加密
     * @author 邋遢龘鵺
     * @datetime 2020/3/24 14:26
     */
    public static String encryptPrivate(String value, String privateKey) throws Exception {
        //base64编码的私钥
        RSAPrivateKey priKey = loadPrivateKeyByStr(privateKey);
        //RSA加密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, priKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(value.getBytes(CodingUtil.CODING_UTF8)));
        return outStr;
    }

    /**
     * @param value      1 加密参数
     * @param privateKey 2 加密私钥
     * @return java.lang.String
     * @titel RSA分段私钥加密
     * @description RSA分段私钥加密
     * @author 邋遢龘鵺
     * @datetime 2020/3/24 14:26
     */
    public static String encryptPrivateSubsection(String value, String privateKey) throws Exception {
        //base64编码的私钥
        RSAPrivateKey priKey = loadPrivateKeyByStr(privateKey);
        //RSA加密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, priKey);


        // 分段加密
        byte[] data = value.getBytes(CodingUtil.CODING_UTF8);
        // 加密时超过117字节就报错。为此采用分段加密的办法来加密
        byte[] enBytes = null;
        for (int i = NumeralUtil.POSITIVE_ZERO; i < data.length; i += MAX_ENCRYPT_BLOCK) {
            // 注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + MAX_ENCRYPT_BLOCK));
            enBytes = ArrayUtils.addAll(enBytes, doFinal);
        }
        String outStr = Base64.encodeBase64String(enBytes);
        return outStr;
    }

    /**
     * @param value     1 解密参数
     * @param publicKey 2 解密公钥
     * @return java.lang.String
     * @titel RSA公钥解密
     * @description RSA公钥解密
     * @author 邋遢龘鵺
     * @datetime 2020/3/24 14:27
     */
    public static String decryptPublic(String value, String publicKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(value.getBytes(CodingUtil.CODING_UTF8));
        RSAPublicKey pubKey = loadPublicKeyByStr(publicKey);
        //RSA解密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

    /**
     * @param value     1 解密参数
     * @param publicKey 2 解密公钥
     * @return java.lang.String
     * @titel RSA分段公钥解密
     * @description RSA分段公钥解密
     * @author 邋遢龘鵺
     * @datetime 2020/3/24 14:27
     */
    public static String decryptPublicSubsection(String value, String publicKey) throws Exception {
        //64位解码加密后的字符串
        byte[] data = Base64.decodeBase64(value.getBytes(CodingUtil.CODING_UTF8));
        RSAPublicKey pubKey = loadPublicKeyByStr(publicKey);
        //RSA解密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        // 分段解密
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (int i = NumeralUtil.POSITIVE_ZERO; i < data.length; i += MAX_DECRYPT_BLOCK) {
            // 注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + MAX_DECRYPT_BLOCK));
            out.write(doFinal, NumeralUtil.POSITIVE_ZERO, doFinal.length);
            out.flush();
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        String outStr = new String(decryptedData);
        return outStr;
    }


    /**
     * @param value      1 解密参数
     * @param privateKey 2 解密私钥
     * @return java.lang.String
     * @titel RSA私钥解密
     * @description RSA私钥解密
     * @author 邋遢龘鵺
     * @datetime 2020/3/24 14:27
     */
    public static String decryptPrivate(String value, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(value.getBytes(CodingUtil.CODING_UTF8));
        RSAPrivateKey priKey = loadPrivateKeyByStr(privateKey);
        //RSA解密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

    /**
     * @param value      1 解密参数
     * @param privateKey 2 解密私钥
     * @return java.lang.String
     * @titel RSA分段私钥解密
     * @description RSA分段私钥解密
     * @author 邋遢龘鵺
     * @datetime 2020/3/24 14:27
     */
    public static String decryptPrivateSubsection(String value, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] data = Base64.decodeBase64(value.getBytes(CodingUtil.CODING_UTF8));
        RSAPrivateKey priKey = loadPrivateKeyByStr(privateKey);
        //RSA解密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        // 分段解密
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (int i = NumeralUtil.POSITIVE_ZERO; i < data.length; i += MAX_DECRYPT_BLOCK) {
            // 注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + MAX_DECRYPT_BLOCK));
            out.write(doFinal, NumeralUtil.POSITIVE_ZERO, doFinal.length);
            out.flush();
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        String outStr = new String(decryptedData);
        return outStr;
    }

}
