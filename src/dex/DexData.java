package dex;

import util.ByteUtil;
import util.FileUtil;

import java.util.ArrayList;

public class DexData extends DexItem {
    public static int ITEM_LENGTH = 1;

    private DexData(DexHeader dexHeader) {
        super(dexHeader);
        originDataList = new ArrayList<>();
        int start_off = dexHeader.getDataOff();
        int size = dexHeader.getDataSize();

        int length =size * ITEM_LENGTH;
        bytes = ByteUtil.getByteArray(getDexBytes(),start_off,length);


    }


    public static DexData init(DexHeader dexHeader) {

        return new DexData(dexHeader);
    }
}
