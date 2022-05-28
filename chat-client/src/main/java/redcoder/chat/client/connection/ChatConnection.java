package redcoder.chat.client.connection;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import redcoder.chat.common.handler.ChatMessageDecoder;
import redcoder.chat.common.handler.ChatMessageEncoder;
import redcoder.chat.common.model.ChatMessage;

public class ChatConnection {

    public ChatConnection(ChatMessage chatMessage, MessageReceiver messageReceiver){
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
                            ch.pipeline().addLast(new LengthFieldPrepender(2));
                            ch.pipeline().addLast(new ChatMessageDecoder());
                            ch.pipeline().addLast(new ChatMessageEncoder());
                            ch.pipeline().addLast(new ChatHandler(chatMessage, messageReceiver));
                        }
                    })
                    .option(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = bootstrap.connect("localhost", 8080).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}