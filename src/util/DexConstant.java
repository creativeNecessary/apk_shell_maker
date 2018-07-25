package util;

public class DexConstant {

    public static final int DEX_MAGIC_LENGTH = 8;
    public static final int CHECK_SUM_LENGTH = 4;
    public static final int SHA1_SIGNATURE_LENGTH = 20;

    public static final int CLASS_DEF_ITEM_LENGTH = 32;
    public static final int STRING_ID_LIST_ITEM_LENGTH = 4;
    public static final int TYPE_ID_LIST_ITEM_LENGTH = 4;
    public static final int PROTO_ID_LIST_ITEM_LENGTH = 12;
    public static final int FIELD_ID_LIST_ITEM_LENGTH = 8;
    public static final int METHOD_ID_LIST_ITEM_LENGTH = 8;
    public static final int MAP_ITEM_LENGTH = 12;

    public static final byte [] class_off_hick_date = {0x0,0x0,0x0,0x0};

    public static final int HEADER_DATA_LENGTH = 4;

    public static final String WORK_DIR_PATH = "/Users/zhanglimin/Desktop/scdata_dex/";
    public static final String ENCRYPT_KEY_FILE_PATH = "/Users/zhanglimin/Desktop/scdata_dex/aes.keygen";
    public static final String SHELL_DIR = "/Users/zhanglimin/Desktop/scdata_dex/shell-release/";
    public static final String SHELL_META_INF_DIR = "/Users/zhanglimin/Desktop/scdata_dex/shell-release/META-INF";
    public static final String OUT_PUT_APK_PATH = "/Users/zhanglimin/Desktop/scdata_dex/encrypt.apk";
    public static final String SOURCE_DIR = "/Users/zhanglimin/Desktop/scdata_dex/source";

    public static final String SHELL_DEX_PATH = "/Users/zhanglimin/Desktop/scdata_dex/shell-release/classes.dex";


    public static final String SOURCE_ENCRYPT_APK_FILE_PATH = "/Users/zhanglimin/Desktop/scdata_dex/encrypt_source.apk";
    public static final String SOURCE_APK_FILE_PATH = "/Users/zhanglimin/Desktop/scdata_dex/source-release.apk";
    public static final String SHELL_APK_FILE_PATH = "/Users/zhanglimin/Desktop/scdata_dex/shell-release.apk";


}
