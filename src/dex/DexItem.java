package dex;

import util.FileUtil;

import java.io.Serializable;
import java.util.List;

public class DexItem implements Serializable {

    protected byte[] bytes;
    protected List<DexOriginData> originDataList;
    protected DexHeader header;

    public DexItem(DexHeader header) {
        this.header = header;
    }

    public byte[] getBytes() {
        return bytes;
    }


    protected byte[] getDexBytes() {

        return header.getDexBytes();
    }

}
