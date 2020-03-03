package top.yangzefeng.integration.common.config;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * 自定义项目启动Banner类
 * <p>
 * 参考:
 * org.springframework.boot.SpringBootBanner
 * org.springframework.boot.SpringApplicationBannerPrinter
 *
 * @author MoCha
 * @date 2019/10/28
 */
public class IceStreamBanner implements Banner {
    private static final String[] BANNER = {"\n" +
            " ___  ________  _______           ________  _________  ________  _______   ________  _____ ______      \n" +
            "|\\  \\|\\   ____\\|\\  ___ \\         |\\   ____\\|\\___   ___\\\\   __  \\|\\  ___ \\ |\\   __  \\|\\   _ \\  _   \\    \n" +
            "\\ \\  \\ \\  \\___|\\ \\   __/|        \\ \\  \\___|\\|___ \\  \\_\\ \\  \\|\\  \\ \\   __/|\\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\   \n" +
            " \\ \\  \\ \\  \\    \\ \\  \\_|/__       \\ \\_____  \\   \\ \\  \\ \\ \\   _  _\\ \\  \\_|/_\\ \\   __  \\ \\  \\\\|__| \\  \\  \n" +
            "  \\ \\  \\ \\  \\____\\ \\  \\_|\\ \\       \\|____|\\  \\   \\ \\  \\ \\ \\  \\\\  \\\\ \\  \\_|\\ \\ \\  \\ \\  \\ \\  \\    \\ \\  \\ \n" +
            "   \\ \\__\\ \\_______\\ \\_______\\        ____\\_\\  \\   \\ \\__\\ \\ \\__\\\\ _\\\\ \\_______\\ \\__\\ \\__\\ \\__\\    \\ \\__\\\n" +
            "    \\|__|\\|_______|\\|_______|       |\\_________\\   \\|__|  \\|__|\\|__|\\|_______|\\|__|\\|__|\\|__|     \\|__|\n" +
            "                                    \\|_________|                                                       \n"};

    private static final String SPRING_BOOT = " :: Spring Boot :: ";

    private static final int STRAP_LINE_SIZE = 42;

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream printStream) {
        for (String line : BANNER) {
            printStream.println(line);
        }
        String version = SpringBootVersion.getVersion();
        version = (version != null) ? " (v" + version + ")" : "";
        StringBuilder padding = new StringBuilder();
        while (padding.length() < STRAP_LINE_SIZE - (version.length() + SPRING_BOOT.length())) {
            padding.append(" ");
        }

        printStream.println(AnsiOutput.toString(AnsiColor.GREEN, SPRING_BOOT, AnsiColor.DEFAULT, padding.toString(),
                AnsiStyle.FAINT, version));
        printStream.println();
    }
}
