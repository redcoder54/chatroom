package redcoder.chat.core.mock;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import redcoder.chat.core.model.RcMessage;
import redcoder.chat.core.model.RcUser;

import java.util.UUID;

public class ChatServerMockHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RcMessage message = (RcMessage) msg;
        System.out.printf("来自客户端的消息: %s%n", message);
        ctx.writeAndFlush(new RcMessage(RcMessage.TEXT_MESSAGE,
                new RcUser("server_mock", "服务端", "Server"),
                UUID.randomUUID().toString(), "你好，我是服务端"));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
