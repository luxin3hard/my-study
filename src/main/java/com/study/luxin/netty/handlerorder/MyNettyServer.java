package com.study.luxin.netty.handlerorder;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.SocketAddress;

public class MyNettyServer {

    public static void main(String[] args) throws InterruptedException {

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        serverBootstrap.channel(NioServerSocketChannel.class).group(boss, work).handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<NioSocketChannel>() {

            @Override
            protected void initChannel(NioSocketChannel ch) {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new OutBoundHandler());
                pipeline.addLast(new InBoundHandler());
            }
        });

        serverBootstrap.bind(9999).sync();
    }


    static class OutBoundHandler extends ChannelOutboundHandlerAdapter {

    }

    static class InBoundHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {

            System.out.println("InBoundHandler channelActive");
            super.channelActive(ctx);
        }
    }

}
