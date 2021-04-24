package top.yangzefeng.integration.common.constant;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Optional;

/**
 * 描述 : .
 *
 * @author : MoCha
 * @version : v1
 * @date : 2021-03-26 22:43
 */
public enum DemoEnum {
    /**
     * DOC
     */
    DOC("doc"),

    /**
     * DOCX
     */
    DOCX("docx"),

    /**
     * PPT
     */
    PPT("ppt"),

    /**
     * PPTX
     */
    PPTX("pptx"),

    /**
     * PDF
     */
    PDF("pdf"),

    /**
     * TXT
     */
    TXT("txt"),

    /**
     * JPG
     */
    JPG("jpg"),

    /**
     * JPEG
     */
    JPEG("jpeg"),

    /**
     * PNG
     */
    PNG("png"),

    XLS("xls"),

    XLSX("xlsx"),

    /**
     * 空类型，针对不存在类型的场景
     */
    NONE("none");

    private final String name;

    public String getName() {
        return name;
    }

    DemoEnum(String name) {
        this.name = name;
    }

    private static final Map<String, DemoEnum> OFFICE_MAP = Maps.newHashMapWithExpectedSize(DemoEnum.values().length);

    static {
        for (DemoEnum officeEnum : DemoEnum.values()) {
            OFFICE_MAP.put(officeEnum.getName(), officeEnum);
        }
    }

    /**
     * 根据枚举类型名字获取对应的可预览附件格式枚举类
     *
     * @param name 可预览后缀格式
     * @return 可预览附件格式枚举类
     */
    public static DemoEnum getPreviewOfficeEnum(String name) {
        return Optional.ofNullable(OFFICE_MAP.get(name)).orElse(NONE);
    }
}
