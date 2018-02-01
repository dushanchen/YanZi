package com.yanzi.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

import com.yanzi.common.constants.ReturnCode;
import com.yanzi.common.exception.CommonException;

public class RSAEncrypt {
    /**
     * 字节数据转字符串专用集合
     */
    private static final char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
            'b', 'c', 'd', 'e', 'f' };

    /**
     * 随机生成密钥对
     */
    public static void genKeyPair(String filePath) {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new CommonException(ReturnCode.ENCRYPT_NO_SUCH_ALGORITHM);
        }
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        try {
            // 得到公钥字符串
            String publicKeyString = Base64.encodeBase64String(publicKey.getEncoded());
            // 得到私钥字符串
            String privateKeyString = Base64.encodeBase64String(privateKey.getEncoded());
            // 将密钥对写入到文件
            FileWriter pubfw = new FileWriter(filePath + "/src/main/resources/rsa/publicKey.keystore");
            FileWriter prifw = new FileWriter(filePath + "/src/main/resources/rsa/privateKey.keystore");
            BufferedWriter pubbw = new BufferedWriter(pubfw);
            BufferedWriter pribw = new BufferedWriter(prifw);
            pubbw.write(publicKeyString);
            pribw.write(privateKeyString);
            pubbw.flush();
            pubbw.close();
            pubfw.close();
            pribw.flush();
            pribw.close();
            prifw.close();
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    /**
     * 从文件中输入流中加载公钥
     * 
     * @param in
     *            公钥输入流
     * @throws Exception
     *             加载公钥时产生的异常
     */
    public static String loadPublicKeyByFile(String path) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path + "/src/main/resources/rsa/publicKey.keystore"));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException e) {
            throw new Exception("公钥输入流为空");
        }
    }

    /**
     * 从字符串中加载公钥
     * 
     * @param publicKeyStr
     *            公钥数据字符串
     * @throws Exception
     *             加载公钥时产生的异常
     */
    public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr) throws Exception {
        try {
            byte[] buffer = Base64.decodeBase64(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        } catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }

    /**
     * 从文件中加载私钥
     * 
     * @param keyFileName
     *            私钥文件名
     * @return 是否成功
     * @throws Exception
     */
    public static String loadPrivateKeyByFile(String path) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path + "/src/main/resources/rsa/privateKey.keystore"));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException e) {
            throw new Exception("私钥输入流为空");
        }
    }

    public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr) throws CommonException {
        try {
            byte[] buffer = Base64.decodeBase64(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new CommonException(ReturnCode.ENCRYPT_NO_SUCH_ALGORITHM);
        } catch (InvalidKeySpecException | NullPointerException e) {
            throw new CommonException(ReturnCode.ENCRYPT_INVALID_PRI_KEY);
        }
    }

    /**
     * 公钥加密过程
     * 
     * @param publicKey
     *            公钥
     * @param plainTextData
     *            明文数据
     * @return
     * @throws Exception
     *             加密过程中的异常信息
     */
    public static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) throws Exception {
        if (publicKey == null) {
            throw new Exception("加密公钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(plainTextData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }

    /**
     * 私钥加密过程
     * 
     * @param privateKey
     *            私钥
     * @param plainTextData
     *            明文数据
     * @return
     * @throws Exception
     *             加密过程中的异常信息
     */
    public static byte[] encrypt(RSAPrivateKey privateKey, byte[] plainTextData) throws Exception {
        if (privateKey == null) {
            throw new Exception("加密私钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] output = cipher.doFinal(plainTextData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("加密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }

    /**
     * 私钥解密过程
     * 
     * @param privateKey
     *            私钥
     * @param cipherData
     *            密文数据
     * @return 明文
     * @throws Exception
     *             解密过程中的异常信息
     */
    public static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData) throws CommonException {
        if (privateKey == null) {
            throw new CommonException(ReturnCode.ENCRYPT_INVALID_PRI_KEY);
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[][] arrays = splitArray(cipherData, 128);
            byte[] output = new byte[0];
            for (byte[] arr : arrays) {
                byte[] temp = cipher.doFinal(arr);
                output = addBytes(output, temp);
            }
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new CommonException(ReturnCode.ENCRYPT_NO_SUCH_ALGORITHM);
        } catch (NoSuchPaddingException e) {
            return null;
        } catch (InvalidKeyException e) {
            throw new CommonException(ReturnCode.ENCRYPT_INVALID_PRI_KEY);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new CommonException(ReturnCode.ENCRYPT_MSG_SIZE_INVALID);
        }
    }

    private static byte[][] splitArray(byte[] data, int len) {
        int x = data.length / len;
        int y = data.length % len;
        int z = 0;
        if (y != 0) {
            z = 1;
        }
        byte[][] arrays = new byte[x + z][];
        byte[] arr;
        for (int i = 0; i < x + z; i++) {
            arr = new byte[len];
            if (i == x + z - 1 && y != 0) {
                System.arraycopy(data, i * len, arr, 0, y);
            } else {
                System.arraycopy(data, i * len, arr, 0, len);
            }
            arrays[i] = arr;
        }
        return arrays;
    }

    private static byte[] addBytes(byte[] data1, byte[] data2) {
        byte[] data3 = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, data2.length);
        return data3;
    }

    /**
     * 公钥解密过程
     * 
     * @param publicKey
     *            公钥
     * @param cipherData
     *            密文数据
     * @return 明文
     * @throws Exception
     *             解密过程中的异常信息
     */
    public static byte[] decrypt(RSAPublicKey publicKey, byte[] cipherData) throws Exception {
        if (publicKey == null) {
            throw new Exception("解密公钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(cipherData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("解密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("密文数据已损坏");
        }
    }

    /**
     * 字节数据转十六进制字符串
     * 
     * @param data
     *            输入数据
     * @return 十六进制内容
     */
    public static String byteArrayToString(byte[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            // 取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移
            stringBuilder.append(HEX_CHAR[(data[i] & 0xf0) >>> 4]);
            // 取出字节的低四位 作为索引得到相应的十六进制标识符
            stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);
            if (i < data.length - 1) {
                stringBuilder.append(' ');
            }
        }
        return stringBuilder.toString();
    }

//    public static void main(String args[]) throws Exception {
//        RSAPrivateKey privateKey = RSAEncrypt.loadPrivateKeyByStr(
//                "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKVEdd3fjuFzjG1/1kX8qXpuIQV9KpTn1rtnFQiV51p3qytp2iB8Z+teFoN/z+4yg6aS3EB/1vHrIGVxtLlU/+T4+NDh5h5N/dlRuOV3iiNK/Et8xMrtejwvq9PuqX65yq8J2v9hLlBnQkS8kKhP/BY+51B+6fVbhX43Ay1dKlVPAgMBAAECgYBpUhCfPcoLaRyz54UBAvxqdmZ63gJV9M1GjnG8D/PpFlwyBXopu75qI4LLeJdlIDH/5JWSUSYE86eonmbiuQV9tSt/Hd7mpvxW+AXreK+ox6ztiYMg10tq/8K8UpvGjkMrRVUHb/STRUY8r3Kv/ZeFXJmxcOpNsVz3f2iQlU2FiQJBAP21mktp0XzlKIXnZNgOhvAV/TUmWPWUkFbxtO//lzYOcnkvgW0EndGkUI/ua1mCTVrvib0ojtn1mTh3VuUsJNUCQQCmwnE1XOt2SyfEqOW51swHToyp2MWMT1rbIBRIzL/vmsKKmY1hw9d8M2qnb8oU03dazQKoNYoCqhfOTOAP7/OTAkEApkkCqe7fOObRWoJA3EMZOf6PiOhrYfpPaEzfdHWm2+04Jil2wMdH0QHLM6rmfTIkFTfupSYSCtUn6ZR+RZJbSQJAEup3gQAbTX3U8v/dnyj4V9PXLOUD85iEy9plsqRXGUzKyIIGgZJ/fP0wGfIaUCZ0oX4j0QTRtN+qd6JMwEINtQJAFGMK7tBMRvW8tIW1F87NfLkBm8ygdkR+JWm+fdXT1o8D+97/ltOxIrE42CTef8N7UCK0axaXoA9RhKCi9R526w==");
//        RSAPublicKey publicKey = RSAEncrypt.loadPublicKeyByStr(
//                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQClRHXd347hc4xtf9ZF/Kl6biEFfSqU59a7ZxUIledad6sradogfGfrXhaDf8/uMoOmktxAf9bx6yBlcbS5VP/k+PjQ4eYeTf3ZUbjld4ojSvxLfMTK7Xo8L6vT7ql+ucqvCdr/YS5QZ0JEvJCoT/wWPudQfun1W4V+NwMtXSpVTwIDAQAB");
//        String value = "{thirdPartyId:\"123456\",nickName:\"abcdef\",source:1}";
//        String valuePub = Base64
//                .encodeBase64String(RSAEncrypt.encrypt(publicKey, value.getBytes("UTF-8")));
//        //System.out.println(valuePub);;
//        byte[] result = RSAEncrypt.decrypt(privateKey, Base64.decodeBase64("S/8MSS0K/B2ZzU6HCQiU1jJHB5181ONiFYBeveC6zkqLPv7k4mkd+GLrqcSRFw4EepLSGgPdnHQjnpPk7JroMyufDypt78iBJ18/c4vvi+S9BN1gW0cju4DVyzQzlz9wi9cQg+7uTAFjPMdDbqlJGiLduKEW9Ow0CJdu+OS1CvY="));
//        System.out.println(new String(result, "UTF-8"));
//        String string = "{thirdPartyId:\"123456\",nickName:\"abcdef\",source:1}";
//        String results = Base64
//                .encodeBase64String(RSAEncrypt.encrypt(publicKey, string.getBytes("UTF-8")));
//        
////System.out.println(results);;
//    }
}