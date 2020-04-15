package com.framework.web;

import com.framework.common.util.encrypt.RSAEncrypt;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web
 * @Description 测试工具类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class RSATest {
    public static void main(String[] args) {
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCvOrX0spLc5QrfpwOzn6q7Ui4nLvdY06F08yw4fvkkN0EH2hCzN/IZIAk0hI3iPFwDx5TJIJnBdR4Is4soztb/GGbxePJ6fh4D7R32/GX2LVlzWlDbXtgcfWbd34hB5zUAo13ikYsOX2XwmnCy9qdEaszT1vfV92eRgYM4MJB5CwIDAQAB";
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAK86tfSyktzlCt+nA7OfqrtSLicu91jToXTzLDh++SQ3QQfaELM38hkgCTSEjeI8XAPHlMkgmcF1HgiziyjO1v8YZvF48np+HgPtHfb8ZfYtWXNaUNte2Bx9Zt3fiEHnNQCjXeKRiw5fZfCacLL2p0RqzNPW99X3Z5GBgzgwkHkLAgMBAAECgYB365Mo8qkFmY52f0NOQ6BBcD17vW2zkan2cpE53NO+kD3mFLvHGyuJPF5wXaMISEyknYraloADYJZ8V/uNoviJGC/9xUwtLLGRlxGqMKfahe/msjqvt/5mlm0qUOCMKqjfcOdu85FVIFTEUuXBHrqDll/0Trk53QYpt1K2Y4SBmQJBAPlmVazfCkXnLW7DPfddrxnem4MC0eAQutwNX2t34sBVEO13hYEvLF+Pu7ZW+mmQRY+NpeebN0Zm2xUrBK0ybU0CQQCz3eB+lONnSyOLibUwGmG4o1MpqoDo4VZKNc9yV0prhDHL7UsqDcMWgMNTOCBN9rpEPoH6cvCQAA1N7N+ltjO3AkEAn6+AZ+GfiEaO9n6YbIxVkzDPTcE+dEMCAeemEv999jwBTKrclmyH8t/rBsMGLxUrFNFTSso9SByPZixXIDZLeQJAc2rfEZrsyW2X44R8Nd/X8SksxkHY8pcWTgx1j4OgB5oOcjNU1il6lEgQDqinYh/+qqgJs/Jpl53Q674iTS27xQJBAMqjthn7NuLT09y3XnMj/YK857EmmQgE9aAuL5N3cXUpvvabVVk/KpCvLs/2uaRdW1iw8/evEJyKLnxsxFzzdVM=";
        String value = "Xdo1Ptc2Mvt1ax5UsMn98+/NABLr34DJlpyoSc9t3gzhdNPkLi/iNGU9ZDZjHmGeNvRS4gYHE+jqA6G4orku+KnUrMSvYVZoV/7TDo0DktTBupCMyWs8Ks5hAxx0ZFLj3zasxDi0CGn4S7ezQbF3XMFglZgrIs8xSr6LOqqlIQU=";
        try {
            String privateValue = RSAEncrypt.decryptPrivate(value, privateKey);
            System.out.println(privateValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            Map<String, String> map = RSAEncrypt.genKeyPair();
//            String publicKey = map.get(RSAEncrypt.PUBLIC_KEY).toString();
//            String privateKey = map.get(RSAEncrypt.PRIVATE_KEY).toString();
//            System.out.println("公钥：");
//            System.out.println(publicKey);
//            System.out.println("私钥：");
//            System.out.println(privateKey);
//
//            String valueA = "1234";
//            String publicStr = RSAEncrypt.encryptPublic(valueA, publicKey);
//            String privateValue = RSAEncrypt.decryptPrivate(publicStr, privateKey);
//            System.out.println("publicStr:" + publicStr.length());
//            System.out.println("privateValue:" + privateValue);
//
//            String valueB = "abcd";
//            String privateStr = RSAEncrypt.encryptPrivate(valueB, privateKey);
//            String publicValue = RSAEncrypt.decryptPublic(privateStr, publicKey);
//            System.out.println("privateStr:" + privateStr.length());
//            System.out.println("publicValue:" + publicValue);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
