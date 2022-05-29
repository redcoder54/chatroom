package redcoder.chat.client;

import redcoder.chat.client.ui.Framework;
import redcoder.chat.core.log.LoggingUtils;

public class ChatClient {

    public static void main(String[] args) {
        LoggingUtils.resetLogManager();
        Framework.createLoginFrame();
    }
}
