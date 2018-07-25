package dex;

import util.ByteUtil;
import util.FileUtil;

import java.util.ArrayList;

public class DexTypeIdList extends DexItem{
    public static int ITEM_LENGTH = 4;

    private DexTypeIdList(DexHeader dexHeader) {
        super(dexHeader);
        originDataList = new ArrayList<>();
        int start_off = dexHeader.getTypeIdsOff();
        int size = dexHeader.getTypeIdsSize();

        int length =size * ITEM_LENGTH;
        bytes = ByteUtil.getByteArray(getDexBytes(),start_off,length);
        for (int i=0 ;i< size ; i++){
            int item_start = start_off + (i * ITEM_LENGTH);
            byte [] item = ByteUtil.getByteArray(getDexBytes(),item_start,ITEM_LENGTH);
            originDataList.add(new DexOriginData(item_start,ITEM_LENGTH,item));
        }

    }


    public static DexTypeIdList init(DexHeader dexHeader) {

        return new DexTypeIdList(dexHeader);
    }

}
