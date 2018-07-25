import dex.*;
import okio.BufferedSink;
import okio.Okio;
import util.*;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Adler32;

public class Main {


    public static void main(String[] args) {
//        EncryptUtil.generateKey();

        //流程
        //1 找到壳apk 将壳apk 解压
        //2 找到壳apk 的dex文件
        //3 加密源apk文件
        //4 将源apk文件拼接在壳的dex文件中
        //将 加密 key 拼接到dex文件中
        //5 修改dex文件
        //6 重新打包

        try {
            Dex encrypt_dex = Dex.init(FileUtil.getInstance().getOriginDexByteArray(), ByteUtil.getDexAttachBytes());

            encrypt_dex.getDexHeader().setFileSize(encrypt_dex.getDexFileByte().length);

            encrypt_dex.getDexHeader().setSha1_signature(EncryptUtil.sha1(encrypt_dex.getDexFileByte()));

            encrypt_dex.getDexHeader().setChecksum(EncryptUtil.adler32(encrypt_dex.getDexFileByte()));
//             将apk 的byte 使用AES128加密
//             再将 apk 拼接到 dex 中
            deleteFile(DexConstant.SHELL_META_INF_DIR);
            reWriteDex(encrypt_dex.getDexFileByte());
            ZipUtil.compress(DexConstant.SHELL_DIR, DexConstant.OUT_PUT_APK_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void reWriteDex(byte[] bytes) {

        try {
            File file = new File(DexConstant.SHELL_DEX_PATH);

            file.createNewFile();
            BufferedSink bufferedSink = Okio.buffer(Okio.sink(file));
            bufferedSink.write(bytes);
            bufferedSink.flush();
            bufferedSink.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static void deleteFile(String path){
        File file = new File(path);
        if(file.exists() && file.isDirectory()){
            File [] childs = file.listFiles();
            for(File delete : childs){
                deleteFile(delete.getAbsolutePath());
            }
            file.delete();
        }else if(file.exists() && file.isFile()){
            file.delete();
        }
    }




}
