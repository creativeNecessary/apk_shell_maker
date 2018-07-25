package util;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

public class ZipUtil {
    private static final int BUFFER = 1024;

    public static void unZip(String fileName, String filePath) throws Exception{
        ZipFile zipFile = new ZipFile(fileName);
        Enumeration emu = zipFile.entries();

        while(emu.hasMoreElements()){
            ZipEntry entry = (ZipEntry) emu.nextElement();
            if (entry.isDirectory()){
                new File(filePath+entry.getName()).mkdirs();
                continue;
            }
            BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));

            File file = new File(filePath + entry.getName());
            File parent = file.getParentFile();
            if(parent != null && (!parent.exists())){
                parent.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos,BUFFER);

            byte [] buf = new byte[BUFFER];
            int len = 0;
            while((len=bis.read(buf,0,BUFFER))!=-1){
                fos.write(buf,0,len);
            }
            bos.flush();
            bos.close();
            bis.close();
        }
        zipFile.close();
    }




    static final int ZIP_BUFFER = 8192;

    public static void compress(String srcPath , String dstPath) throws IOException {
        File srcFile = new File(srcPath);
        File dstFile = new File(dstPath);
        if (!srcFile.exists()) {
            throw new FileNotFoundException(srcPath + "不存在！");
        }

        FileOutputStream out = null;
        ZipOutputStream zipOut = null;
        try {
            out = new FileOutputStream(dstFile);
            CheckedOutputStream cos = new CheckedOutputStream(out,new CRC32());
            zipOut = new ZipOutputStream(cos);
            String baseDir = "";
            File [] files = srcFile.listFiles();
            for(File src : files){
                compress(src, zipOut, baseDir);
            }
        }
        finally {
            if(null != zipOut){
                zipOut.close();
                out = null;
            }

            if(null != out){
                out.close();
            }
        }
    }

    private static void compress(File file, ZipOutputStream zipOut, String baseDir) throws IOException{
        if (file.isDirectory()) {
            compressDirectory(file, zipOut, baseDir);
        } else {
            compressFile(file, zipOut, baseDir);
        }
    }

    /** 压缩一个目录 */
    private static void compressDirectory(File dir, ZipOutputStream zipOut, String baseDir) throws IOException{
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            compress(files[i], zipOut, baseDir + dir.getName() + "/");
        }
    }

    /** 压缩一个文件 */
    private static void compressFile(File file, ZipOutputStream zipOut, String baseDir)  throws IOException{
        if (!file.exists()){
            return;
        }

        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            ZipEntry entry = new ZipEntry(baseDir + file.getName());
            zipOut.putNextEntry(entry);
            int count;
            byte data[] = new byte[ZIP_BUFFER];
            while ((count = bis.read(data, 0, ZIP_BUFFER)) != -1) {
                zipOut.write(data, 0, count);
            }

        }finally {
            if(null != bis){
                bis.close();
            }
        }
    }


}
