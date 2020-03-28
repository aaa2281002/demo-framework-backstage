package com.framework.common.util.encrypt;

import com.framework.common.util.other.CodingUtil;
import com.framework.common.util.other.NumeralUtil;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
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
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.util.encrypt
 * @Description RSA加密解密工具类
 * @Date 2020/3/24 13:55
 * @Version 1.0
 */
public class RSAEncrypt {
    //算法种类
    public static final String KEY_ALGORITHM = "RSA";
    //获取公钥键
    public static final String PUBLIC_KEY = "RSAPublicKey";
    //获取私钥键
    public static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * @return java.util.Map
     * @Titel RSA生成密钥对(公钥和私钥)
     * @Description RSA生成密钥对(公钥和私钥)
     * @Author 邋遢龘鵺
     * @DateTime 2020/3/24 14:03
     */
    public static Map<String, String> genKeyPair() throws Exception {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
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
     * @Titel RSA从字符串加载公钥
     * @Description RSA从字符串加载公钥
     * @Author 邋遢龘鵺
     * @DateTime 2020/3/24 14:14
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
     * @Titel RSA从字符串加载私钥
     * @Description RSA从字符串加载私钥
     * @Author 邋遢龘鵺
     * @DateTime 2020/3/24 14:20
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
     * @Titel RSA公钥加密
     * @Description RSA公钥加密
     * @Author 邋遢龘鵺
     * @DateTime 2020/3/24 14:26
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
     * @param value      1 加密参数
     * @param privateKey 2 加密私钥
     * @return java.lang.String
     * @Titel RSA私钥加密
     * @Description RSA私钥加密
     * @Author 邋遢龘鵺
     * @DateTime 2020/3/24 14:26
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
     * @param value     1 解密参数
     * @param publicKey 2 解密公钥
     * @return java.lang.String
     * @Titel RSA公钥解密
     * @Description RSA公钥解密
     * @Author 邋遢龘鵺
     * @DateTime 2020/3/24 14:27
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
     * @param value      1 解密参数
     * @param privateKey 2 解密私钥
     * @return java.lang.String
     * @Titel RSA私钥解密
     * @Description RSA私钥解密
     * @Author 邋遢龘鵺
     * @DateTime 2020/3/24 14:27
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


}
