package util;

import dex.DexException;
import dex.DexOriginData;
import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {



    public static int getByteContentInt(byte[] data) {
        if (data.length > 4) {
            throw new DexException("");
        }
        Buffer buffer = new Buffer();
        buffer.write(data);
        return buffer.readIntLe();
    }


    public static byte[] concatAll(List<byte []> bytes) {
        int totalLength = 0;
        for (byte [] array : bytes) {
            totalLength += array.length;
        }
        byte[] result = new byte[totalLength];
        int offset = 0;
        for (byte[] array : bytes) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }


    public static List<byte []> getOriginByteArray(List<DexOriginData> dexOriginDataList){
        List<byte []> bytes = new ArrayList<>();
        for (DexOriginData data: dexOriginDataList) {
            int length = data.getLength();
            byte [] result = new byte[length];
            System.arraycopy(data.getBytes(),0,result,0,length);
            bytes.add(result);
        }
        return bytes;
    }


    //int类型转换为byte[]数组
    public static byte[] intToBytesLe(int i) {
        byte[] result = new byte[4];
        // 由高位到低位
        result[3] = (byte) ((i >> 24) & 0xFF);
        result[2] = (byte) ((i >> 16) & 0xFF);
        result[1] = (byte) ((i >> 8) & 0xFF);
        result[0] = (byte) (i & 0xFF);
        return result;
    }


    private static ByteBuffer buffer = ByteBuffer.allocate(8);
    //byte 数组与 long 的相互转换

    //小端
    public static byte[] longToBytesLe(long x) {
        Buffer buffer = new Buffer();
        buffer.writeLongLe(x);
        buffer.flush();
        byte [] data = buffer.readByteArray();
        buffer.close();
        return  data;
    }


}
