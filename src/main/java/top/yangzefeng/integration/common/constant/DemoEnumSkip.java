package top.yangzefeng.integration.common.constant;

import lombok.Getter;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 描述 : .
 *
 * @author : MoCha
 * @version : v1
 * @date : 2021-03-27 09:30
 */
@Getter
public enum DemoEnumSkip {
    /**
     * DOC
     */
    DOC("doc", "1"),

    /**
     * DOCX
     */
    DOCX("docx", "2"),

    /**
     * PPT
     */
    PPT("ppt", "3"),

    /**
     * PPTX
     */
    PPTX("pptx", "4"),

    /**
     * PDF
     */
    PDF("pdf", "5"),

    /**
     * TXT
     */
    TXT("txt", "6"),

    /**
     * JPG
     */
    JPG("jpg", "7"),

    /**
     * JPEG
     */
    JPEG("jpeg", "8"),

    /**
     * PNG
     */
    PNG("png", "9"),

    XLS("xls", "10"),

    XLSX("xlsx", "11"),

    /**
     * 空类型，针对不存在类型的场景
     */
    NONE("none", "-1");

    private final String name;

    private final String code;

    private static final Map<String, DemoEnumSkip> OFFICE_MAP = new ConcurrentSkipListMap<>();

    static {
        for (DemoEnumSkip officeEnum : DemoEnumSkip.values()) {
            OFFICE_MAP.put(officeEnum.getCode(), officeEnum);
        }
    }

    DemoEnumSkip(String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * 根据枚举类型名字获取对应的可预览附件格式枚举类
     *
     * @param name 可预览后缀格式
     * @return 可预览附件格式枚举类
     */
    public static DemoEnumSkip getPreviewOfficeEnum(String name) {
        return Optional.ofNullable(OFFICE_MAP.get(name)).orElse(NONE);
    }

    public static String getEnumName(String name) {
        return getPreviewOfficeEnum(name).getName();
    }

    public static void setExtension(String code, DemoEnumSkip demoEnumSkip) {
        OFFICE_MAP.put(code, demoEnumSkip);
    }
}
