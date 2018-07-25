package dex;

public class DexOriginData  {
    private int offset;
    private int length;
    private byte [] bytes;

    public DexOriginData(int offset, int length, byte[] bytes) {
        this.offset = offset;
        this.length = length;
        this.bytes = bytes;
    }

    public int getOffset() {
        return offset;
    }

    public int getLength() {
        return length;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
