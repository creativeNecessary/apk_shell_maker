package util;

import okio.BufferedSource;
import okio.Okio;

import java.io.*;

public class FileUtil {

    private File originDexFile;
    private File sourceApkFile;
    private File encryptKeyFile;
    private byte[] dex_file_bytes;
    private byte[] source_apk_bytes;
    private byte[] encrypt_key_bytes;

    private FileUtil() {

        try {
            //解压 获取文件
            ZipUtil.unZip(DexConstant.SHELL_APK_FILE_PATH, DexConstant.SHELL_DIR);

            originDexFile = new File(DexConstant.SHELL_DEX_PATH);
            sourceApkFile = new File(DexConstant.SOURCE_APK_FILE_PATH);
            encryptKeyFile = new File(DexConstant.ENCRYPT_KEY_FILE_PATH);
            if(!originDexFile.exists()){
                throw new FileNotFoundException("壳程序dex文件不存在，请修改DexConstant中SHELL_DEX_PATH的路径");
            }
            if(!sourceApkFile.exists()){
                throw new FileNotFoundException("待加壳程序apk不存在，请修改DexConstant中SOURCE_APK_FILE_PATH的路径");
            }
            if(!encryptKeyFile.exists()){
                throw new FileNotFoundException("密钥文件不存在，请修改DexConstant中ENCRYPT_KEY_FILE_PATH的路径");
            }
            BufferedSource dex_bufferedSource = Okio.buffer(Okio.source(originDexFile));
            BufferedSource source_apk_bufferedSource = Okio.buffer(Okio.source(sourceApkFile));
            BufferedSource encryptKeySource = Okio.buffer(Okio.source(encryptKeyFile));

            dex_file_bytes = dex_bufferedSource.readByteArray();
            source_apk_bytes = source_apk_bufferedSource.readByteArray();
            encrypt_key_bytes = encryptKeySource.readByteArray();

//            dex_file_bytes = readFileBytes(originDexFile);
//            source_apk_bytes = readFileBytes(sourceApkFile);
//            encrypt_key_bytes = readFileBytes(encryptKeyFile);

            dex_bufferedSource.close();
            source_apk_bufferedSource.close();
            encryptKeySource.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private  byte[] readFileBytes(File file) throws IOException {
        byte[] arrayOfByte = new byte[1024];
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(file);
        while (true) {
            int i = fis.read(arrayOfByte);
            if (i != -1) {
                localByteArrayOutputStream.write(arrayOfByte, 0, i);
            } else {
                return localByteArrayOutputStream.toByteArray();
            }
        }
    }


    public static FileUtil getInstance() {
        return DexFileUtilHolder.instance;
    }

    static class DexFileUtilHolder {
        static FileUtil instance = new FileUtil();

    }



    public byte[] getOriginDexByteArray() {

        return dex_file_bytes;
    }

    public byte[] getSourceApkByteArray() {

        return source_apk_bytes;
    }
    public byte[] getEncryptKeyByteArray() {

        return encrypt_key_bytes;
    }






}
