package util;

import java.util.ArrayList;
import java.util.List;

public class ByteUtil {

    public static byte[] getByteArray(byte [] bytes,int skip, int size) {
        byte[] result = new byte[size];
        System.arraycopy(bytes, skip, result, 0, size);

        return result;
    }

    public static byte[] getByteArray(byte [] bytes,int skip) {
        byte[] result = new byte[bytes.length-skip];
        System.arraycopy(bytes, skip, result, 0, bytes.length-skip);

        return result;
    }


    public static byte [] getDexAttachBytes(){
        List<byte[]> data = new ArrayList<>();
        byte[] encrypt_apk = FileUtil.getInstance().getSourceApkByteArray();
        data.add(encrypt_apk);
        data.add(Util.intToBytesLe(encrypt_apk.length));
        return Util.concatAll(data);
    }
}
