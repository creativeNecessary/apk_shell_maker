package dex;

import util.ByteUtil;
import util.FileUtil;

import java.util.ArrayList;

public class DexMapListType extends DexItem{

    public static int ITEM_LENGTH = 12;

    private DexMapListType(DexHeader dexHeader) {
        super(dexHeader);
        originDataList = new ArrayList<>();
        int start_off = dexHeader.getMapListOff();
        int length = dexHeader.getDexFileSize() - start_off;
        //数据前四位是 size
        int size = (length - 4)/ITEM_LENGTH;
        int list_start_off = start_off + 4;
        bytes = ByteUtil.getByteArray(getDexBytes(),start_off,length);
        for (int i=0 ;i< size ; i++){
            int item_start = list_start_off + (i * ITEM_LENGTH);
            byte [] item = ByteUtil.getByteArray(getDexBytes(),item_start,ITEM_LENGTH);
            originDataList.add(new DexOriginData(item_start,ITEM_LENGTH,item));
        }

    }


    public static DexMapListType init(DexHeader dexHeader) {

        return new DexMapListType(dexHeader);
    }

}
