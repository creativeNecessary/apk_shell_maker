package dex;

import util.ByteUtil;
import util.FileUtil;
import util.Log;
import util.Util;

import java.util.ArrayList;
import java.util.List;

public class DexClassDefItemList extends DexItem{


    public static int ITEM_LENGTH = 32;
    private List<byte []> origin_class_off_data = new ArrayList<>();

    //uint class_data_off skip 28

    public static DexClassDefItemList init(DexHeader header){
        return new DexClassDefItemList(header);

    }

    private DexClassDefItemList(DexHeader header){
        super(header);
        originDataList = new ArrayList<>();
        int start_off = header.getClassDefOff();
        int size = header.getClassDefSize();
        int length =size * ITEM_LENGTH;
        bytes = ByteUtil.getByteArray(getDexBytes(),start_off,length);
        for (int i=0 ;i< size ; i++){
            int item_start = start_off + (i * ITEM_LENGTH);
            byte [] item = ByteUtil.getByteArray(getDexBytes(),item_start,ITEM_LENGTH);
            originDataList.add(new DexOriginData(item_start,ITEM_LENGTH,item));

        }

    }
    public byte [] getEncryptData(){
        List<byte []>  itemList = Util.getOriginByteArray(originDataList);
        int index = 0;
        for(byte [] class_data : itemList){
            for (int i = 24 ; i < 28 ; i++){
                class_data[i] = 0x0;
            }
            index++;
            Log.d("修改 "+index);
        }
        Log.d("");
        return Util.concatAll(itemList);
    }



    public byte [] getOriginClassOffData(){
        origin_class_off_data.clear();
        List<byte []>  itemList = Util.getOriginByteArray(originDataList);
        int index = 0;
        for(byte [] class_data : itemList){
            byte [] origin_class_bytes = new byte[4];
            System.arraycopy(class_data,24,origin_class_bytes,0,4);
            origin_class_off_data.add(origin_class_bytes);
        }
        Log.d("");
        return Util.concatAll(origin_class_off_data);
    }






    public byte[] getBytes() {
        return bytes;
    }
}
