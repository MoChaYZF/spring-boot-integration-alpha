package top.yangzefeng.integration.common.util;

/**
 * IP转换工具类
 *
 * @author MoCha
 * @date 2019/12/21
 */
public final class IpUtils {
    /**
     * 将IPV4字符串转换成long值
     *
     * @param ipv4 IPV4字符串
     * @return IP对应的long值
     */
    public static long ipv4ConvertLong(String ipv4) {
        String[] ip = ipv4.split("\\.");
        return (Long.parseLong(ip[0]) << 24) + (Long.parseLong(ip[1]) << 16)
                + (Long.parseLong(ip[2]) << 8) + Long.parseLong(ip[3]);
    }

    /**
     * 将long值转换成对应的IPV4字符串
     *
     * @param ipv4LongValue IPV4的long值
     * @return IPV4字符串
     */
    public static String longConvertIpv4(long ipv4LongValue) {
        String ip = "";
        ip = ip + (ipv4LongValue >>> 24);
        ip = ip + "." + ((0x00ffffff & ipv4LongValue) >>> 16);
        ip = ip + "." + ((0x0000ffff & ipv4LongValue) >>> 8);
        ip = ip + "." + (0x000000ff & ipv4LongValue);
        return ip;
    }

    /**
     * 阻止实例化
     * <p>
     * Prevents instantiation
     */
    private IpUtils() {
    }
}
