package com.study.luxin.netty.nettydemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;
import java.util.Date;

public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });

        Channel channel = bootstrap.connect("127.0.0.1", 8000).channel();


        // 服务端的channel用户服务端,客户端的用于客户端
        System.out.println(channel.hasAttr(AttributeKey.valueOf("clientKey")));


        //ByteBuf byteBuf = Unpooled.copiedBuffer(new Date() + ": hello world!", CharsetUtil.US_ASCII);
        ByteBuf byteBuf = Unpooled.copiedBuffer("1234".getBytes());
        ChannelFuture channelFuture = channel.writeAndFlush(byteBuf);

        // 同步等待发送是否成功的的结果,如果没有建立连接,会抛出异常
        channelFuture.sync();

        System.out.println("是否发送成功: " + channelFuture.isSuccess());


    }
}
