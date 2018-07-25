package dex;

import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import util.Util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class HeaderItem {
    private byte[] byte_data;
    private String byteString;
    private String name;
    private int size;
    private int offset;
    private int byte_content;

    public static HeaderItem initItem(byte[] byte_data, String name, int size,int offset) {

        return new HeaderItem(byte_data, name, size,offset);

    }

    public HeaderItem(byte[] byte_data, String name, int size,int offset) {
        this.byte_data = byte_data;
        this.name = name;
        this.size = size;
        this.offset = offset;
        initData();

    }

    public byte[] getByte_data() {
        return byte_data;
    }

    public String getByteString() {
        return byteString;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getByte_content() {
        return byte_content;
    }

    public int getOffset() {
        return offset;
    }

    private void initData(){
        Buffer buffer = new Buffer();
        buffer.write(byte_data);
        ByteString byteString = buffer.readByteString();
        this.byteString = byteString.toString();

        buffer = new Buffer();
        buffer.write(byte_data);
        if (size == 4) {
            this.byte_content = buffer.readIntLe();
        }
    }

    public void setIntValue(int value){
        byte_data = Util.intToBytesLe(value);
        initData();
    }

    public void setByteData(byte [] bytes){
        byte_data = bytes;
        initData();
    }
}
