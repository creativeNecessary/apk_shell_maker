package dex;

import util.Log;
import util.Util;

import java.util.ArrayList;
import java.util.List;

public class Dex {
    private DexHeader dexHeader;
    private DexStringIdList dexStringIdList;
    private DexTypeIdList dexTypeIdList;
    private DexProtoIdList dexProtoIdList;
    private DexFieldIdList dexFieldIdList;
    private DexMethodIdList dexMethodIdList;
    private DexClassDefItemList dexClassDefItemList;
    private DexMapListType dexMapListType;
    private DexData dexData;
    private DexLink dexLink;
    private byte [] dexAttach;

    private Dex() {
    }



    public static Dex init(byte[] dex_bytes , byte [] attach_bytes) {
        Dex dex = new Dex();
        dex.dexHeader = DexHeader.init(dex_bytes);
        dex.dexStringIdList = DexStringIdList.init(dex.dexHeader);
        dex.dexTypeIdList = DexTypeIdList.init(dex.dexHeader);
        dex.dexProtoIdList = DexProtoIdList.init(dex.dexHeader);
        dex.dexFieldIdList = DexFieldIdList.init(dex.dexHeader);

        dex.dexMethodIdList = DexMethodIdList.init(dex.dexHeader);
        dex.dexClassDefItemList = DexClassDefItemList.init(dex.dexHeader);
        dex.dexData = DexData.init(dex.dexHeader);
        dex.dexMapListType = DexMapListType.init(dex.dexHeader);
        dex.dexAttach = attach_bytes;
        return dex;
    }



    public byte[] getDexFileByte() {
        List<byte[]> bytes = new ArrayList<>();
        bytes.add(dexHeader.getBytes());
        bytes.add(dexStringIdList.getBytes());
        bytes.add(dexTypeIdList.getBytes());
        bytes.add(dexProtoIdList.getBytes());
        bytes.add(dexFieldIdList.getBytes());
        bytes.add(dexMethodIdList.getBytes());
        bytes.add(dexClassDefItemList.getBytes());
        bytes.add(dexData.getBytes());
        bytes.add(dexAttach);
        return Util.concatAll(bytes);
    }

    public DexHeader getDexHeader() {
        return dexHeader;
    }
}
