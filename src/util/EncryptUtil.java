package util;

import okio.BufferedSink;
import okio.Okio;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.Adler32;
import java.util.zip.Checksum;

public class EncryptUtil {
    private static final String ALGORITHM_AES = "AES";
    private static final String SECURE_SEED = "ScDataEclipse";

    public static void generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM_AES);
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.setSeed(SECURE_SEED.getBytes());
            keyGenerator.init(secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            Log.d(secretKey);
            File encrypt = new File(DexConstant.ENCRYPT_KEY_FILE_PATH);
            encrypt.createNewFile();
            BufferedSink bufferedSink = Okio.buffer(Okio.sink(encrypt));
            bufferedSink.write(secretKey.getEncoded());
            bufferedSink.flush();


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] encryptApk() {
        //1、指定算法、获取Cipher对象
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_AES);
            SecretKeySpec secretKey = new SecretKeySpec(FileUtil.getInstance().getEncryptKeyByteArray(), ALGORITHM_AES);
            //3、用指定的密钥初始化Cipher对象，指定是加密模式，还是解密模式
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            //4、更新需要加密的内容

            //5、进行最终的加解密操作
            //也可以把4、5步组合到一起，但是如果保留了4步，同时又是如下这样使用的话，加密的内容将是之前update传递的内容和doFinal传递的内容的和。
//      byte[] result = cipher.doFinal(content.getBytes());

            return cipher.doFinal(FileUtil.getInstance().getSourceApkByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //2、生成/读取用于加解密的密钥

        return null;
    }


    //解密
    public static byte[] decryptApk(byte[] data) {
        //1、指定算法、获取Cipher对象
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_AES);
            SecretKeySpec secretKey = new SecretKeySpec(FileUtil.getInstance().getEncryptKeyByteArray(), ALGORITHM_AES);
            //3、用指定的密钥初始化Cipher对象，指定是加密模式，还是解密模式
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //2、生成/读取用于加解密的密钥

        return null;
    }


    public static byte[] generateDex(byte[] originDex, byte[] encrypt_apk) {
        List<byte[]> data = new ArrayList<>();
        data.add(originDex);
        data.add(encrypt_apk);
        data.add(Util.intToBytesLe(encrypt_apk.length));

        return Util.concatAll(data);
    }


    public static byte[] adler32(byte[] bytes) {

        Adler32 a32 = new Adler32();
        a32.update(bytes, 12, bytes.length - 12);
        int checksum = (int) a32.getValue();
        return Util.intToBytesLe(checksum);
    }

    public static byte[] sha1(byte[] bytes) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(bytes,32,bytes.length-32);

        return messageDigest.digest();
    }

}
