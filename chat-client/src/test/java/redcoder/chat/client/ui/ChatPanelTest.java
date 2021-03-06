package redcoder.chat.client.ui;

import redcoder.chat.client.model.Message;
import redcoder.chat.client.model.TextMessage;
import redcoder.chat.client.model.User;
import redcoder.chat.client.resource.IconResource;
import redcoder.chat.client.ui.message.MessageDisplayPanel;
import redcoder.chat.client.ui.user.UserPanel;

import javax.swing.*;
import java.util.Random;
import java.util.UUID;

public class ChatPanelTest {

    private static final String[] IMAGES = {"Male.png", "Female.png"};
    private static final Random RANDOM = new Random();
    private static final User me = new User("me", "me", "Male.png");

    private static ChatPanel createChatPanel() {
        ChatPanel chatPanel = new ChatPanel(new ChatFrame(me));
        initUserPanel(chatPanel.getUserPanel());
        initMessagePanel(chatPanel.getMessagePanel().getDisplayPanel());
        return chatPanel;
    }

    private static void initUserPanel(UserPanel userPanel) {
        for (int i = 0; i < 10; i++) {
            userPanel.addUser(createUser());
        }
    }

    private static void initMessagePanel(MessageDisplayPanel messageDisplayPanel) {
        for (int i = 1; i <= 10; i++) {
            messageDisplayPanel.addMessage(createMessage(), i % 5 == 0);
        }
    }

    private static Message createMessage() {
        User user = createUser();
        return new TextMessage(user, "hello, i'm " + user.getNickname());
    }

    private static User createUser() {
        String image = IMAGES[RANDOM.nextInt(2)];
        return new User(UUID.randomUUID().toString(), "大" + RANDOM.nextInt(100), IconResource.getAvatarIcon(image));
    }

    public static void main(String[] args) {
        // Me.setMe(me);

        JFrame frame = new JFrame("Chat Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(createChatPanel());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
