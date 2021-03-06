package redcoder.chat.core.log;

import redcoder.chat.core.utils.SystemUtils;

import java.io.File;
import java.io.InputStream;
import java.util.logging.LogManager;

public class LoggingUtils {

    public static void resetLogManager() {
        try (InputStream in = LoggingUtils.class.getClassLoader().getResourceAsStream("log.properties")) {
            ensureLogDirExist();
            LogManager.getLogManager().readConfiguration(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void ensureLogDirExist() {
        String home = SystemUtils.getUserHome();
        File dir = new File(home, "rc-chat");
        if (dir.exists()) {
            return;
        }
        dir.mkdir();
    }
}
