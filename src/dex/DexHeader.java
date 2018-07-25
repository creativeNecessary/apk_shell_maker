package dex;

import util.ByteUtil;
import util.DexConstant;
import util.FileUtil;
import util.Util;

import java.util.ArrayList;
import java.util.List;

public class DexHeader {
    private int current_skip = 0;
    private HeaderItem dex_magic;
    private HeaderItem checksum;
    private HeaderItem sha1_signature;
    private HeaderItem uint_file_size;
    private HeaderItem uint_header_size;
    private HeaderItem uint_endian_tag;
    private HeaderItem uint_link_size;
    private HeaderItem uint_link_off;
    private HeaderItem uint_map_off;
    private HeaderItem uint_string_ids_size;
    private HeaderItem uint_string_ids_off;
    private HeaderItem uint_type_ids_size;
    private HeaderItem uint_type_ids_off;
    private HeaderItem uint_proto_ids_size;
    private HeaderItem uint_proto_ids_off;
    private HeaderItem uint_field_ids_size;
    private HeaderItem uint_field_ids_off;
    private HeaderItem uint_method_ids_size;
    private HeaderItem uint_method_ids_off;
    private HeaderItem uint_class_defs_size;
    private HeaderItem uint_class_defs_off;
    private HeaderItem uint_data_size;
    private HeaderItem uint_data_off;
    private byte[] dexBytes;

    public static DexHeader init(byte[] dexBytes) {
        return new DexHeader(dexBytes);
    }

    private DexHeader(byte[] dexBytes) {
        this.dexBytes = dexBytes;
        dex_magic = initItem("dex_magic", DexConstant.DEX_MAGIC_LENGTH);
        checksum = initItem("checksum", DexConstant.CHECK_SUM_LENGTH);
        sha1_signature = initItem("sha1_signature", DexConstant.SHA1_SIGNATURE_LENGTH);

        uint_file_size = initItem("uint_file_size", DexConstant.HEADER_DATA_LENGTH);
        uint_header_size = initItem("uint_header_size", DexConstant.HEADER_DATA_LENGTH);
        uint_endian_tag = initItem("uint_endian_tag", DexConstant.HEADER_DATA_LENGTH);
        uint_link_size = initItem("uint_link_size", DexConstant.HEADER_DATA_LENGTH);
        uint_link_off = initItem("uint_link_off", DexConstant.HEADER_DATA_LENGTH);
        uint_map_off = initItem("uint_map_off", DexConstant.HEADER_DATA_LENGTH);
        uint_string_ids_size = initItem("uint_string_ids_size", DexConstant.HEADER_DATA_LENGTH);
        uint_string_ids_off = initItem("uint_string_ids_off", DexConstant.HEADER_DATA_LENGTH);
        uint_type_ids_size = initItem("uint_type_ids_size", DexConstant.HEADER_DATA_LENGTH);
        uint_type_ids_off = initItem("uint_type_ids_off", DexConstant.HEADER_DATA_LENGTH);
        uint_proto_ids_size = initItem("uint_proto_ids_size", DexConstant.HEADER_DATA_LENGTH);
        uint_proto_ids_off = initItem("uint_proto_ids_off", DexConstant.HEADER_DATA_LENGTH);
        uint_field_ids_size = initItem("uint_field_ids_size", DexConstant.HEADER_DATA_LENGTH);
        uint_field_ids_off = initItem("uint_field_ids_off", DexConstant.HEADER_DATA_LENGTH);
        uint_method_ids_size = initItem("uint_method_ids_size", DexConstant.HEADER_DATA_LENGTH);
        uint_method_ids_off = initItem("uint_method_ids_off", DexConstant.HEADER_DATA_LENGTH);
        uint_class_defs_size = initItem("uint_class_defs_size", DexConstant.HEADER_DATA_LENGTH);
        uint_class_defs_off = initItem("uint_class_defs_off", DexConstant.HEADER_DATA_LENGTH);
        uint_data_size = initItem("uint_data_size", DexConstant.HEADER_DATA_LENGTH);
        uint_data_off = initItem("uint_data_off", DexConstant.HEADER_DATA_LENGTH);

    }


    private HeaderItem initItem(String name, int size) {
        HeaderItem item = HeaderItem.initItem(ByteUtil.getByteArray(dexBytes, current_skip
                , size)
                , name
                , size
                , current_skip);
        current_skip += size;

        return item;
    }


    public int getHeaderSize() {
        return uint_header_size.getByte_content();
    }

    public int getClassDefOff() {
        return uint_class_defs_off.getByte_content();
    }

    public int getClassDefSize() {

        return uint_class_defs_size.getByte_content();
    }


    public int getStringIdsOff() {
        return uint_string_ids_off.getByte_content();
    }

    public int getStringIdsSize() {

        return uint_string_ids_size.getByte_content();
    }


    public int getTypeIdsOff() {
        return uint_type_ids_off.getByte_content();
    }

    public int getTypeIdsSize() {

        return uint_type_ids_size.getByte_content();
    }


    public int getProtoIdsOff() {
        return uint_proto_ids_off.getByte_content();
    }

    public int getProtoIdsSize() {

        return uint_proto_ids_size.getByte_content();
    }


    public int getFieldIdsOff() {
        return uint_field_ids_off.getByte_content();
    }

    public int getFieldIdsSize() {

        return uint_field_ids_size.getByte_content();
    }


    public int getMethodIdsOff() {
        return uint_method_ids_off.getByte_content();
    }

    public int getMethodIdsSize() {

        return uint_method_ids_size.getByte_content();
    }

    public int getMapListOff() {
        return uint_map_off.getByte_content();
    }


    public int getDataOff() {
        return uint_data_off.getByte_content();
    }

    public int getDataSize() {

        return uint_data_size.getByte_content();
    }

    public int getLinkOff() {
        return uint_link_off.getByte_content();
    }

    public int getLinkSize() {

        return uint_link_size.getByte_content();
    }


    public int getDexFileSize() {
        return uint_file_size.getByte_content();
    }

    public byte[] getDexBytes() {
        return dexBytes;
    }

    public void setChecksum(byte [] bytes) {
        this.checksum.setByteData(bytes);
    }

    public void setFileSize(int size) {
        this.uint_file_size.setIntValue(size);
    }

    public void setSha1_signature(byte [] bytes) {
        this.sha1_signature.setByteData(bytes);
    }

    public byte[] getBytes() {
        List<byte[]> data = new ArrayList<>();
        data.add(dex_magic.getByte_data());
        data.add(checksum.getByte_data());
        data.add(sha1_signature.getByte_data());
        data.add(uint_file_size.getByte_data());
        data.add(uint_header_size.getByte_data());
        data.add(uint_endian_tag.getByte_data());
        data.add(uint_link_size.getByte_data());
        data.add(uint_link_off.getByte_data());
        data.add(uint_map_off.getByte_data());
        data.add(uint_string_ids_size.getByte_data());
        data.add(uint_string_ids_off.getByte_data());
        data.add(uint_type_ids_size.getByte_data());
        data.add(uint_type_ids_off.getByte_data());
        data.add(uint_proto_ids_size.getByte_data());
        data.add(uint_proto_ids_off.getByte_data());
        data.add(uint_field_ids_size.getByte_data());
        data.add(uint_field_ids_off.getByte_data());
        data.add(uint_method_ids_size.getByte_data());
        data.add(uint_method_ids_off.getByte_data());
        data.add(uint_class_defs_size.getByte_data());
        data.add(uint_class_defs_off.getByte_data());
        data.add(uint_data_size.getByte_data());
        data.add(uint_data_off.getByte_data());
        return Util.concatAll(data);
    }
}
