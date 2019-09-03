package com.study.luxin.netty.nettydemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * 要启动一个Netty服务端，必须要指定三类属性，(1)分别是线程模型、(2)IO模型、(3)连接读写处理逻辑,再次之后调动 bind()方法
 */
public class NettyServer {
    public static void main(String[] args) {
        RunNettyServer();
    }

    private static void RunNettyServer() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.childAttr(AttributeKey.newInstance("clientKey"), "clientValue");

        //----------------childOption()可以给每条连接设置一些TCP底层相关的属性----------------------------------------------------------------
        // 开启心跳机制
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

        // Nagle算法，true表示关闭，false表示开启，通俗地说，如果要求高实时性，有数据发送时就马上发送，就关闭，如果需要减少发送次数减少网络交互，就开启
        serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
        //-----------------------------------------------------------------------------------------------------------------------------


        //----(1)-----------start-------------
        // 接受新连接线程,主要负责创建新连接
        NioEventLoopGroup boss = new NioEventLoopGroup();

        //中的负责读取数据的线程，主要用于读取数据以及业务逻辑处理--对应ClientSelector
        NioEventLoopGroup worker = new NioEventLoopGroup();
        ChannelFuture channelFuture = serverBootstrap
                .group(boss, worker)
                //----(1)-----------end---------------

                //----(2)-----------start-------------
                .channel(NioServerSocketChannel.class)
                //----(2)-----------end---------------

                //----(3)-----------start-------------
                .childHandler(
                        // ChannelInitializer 主要用户处理每条连接的数据读写
                        new ChannelInitializer<NioSocketChannel>() {
                            protected void initChannel(NioSocketChannel ch) {

                                ch.pipeline().addLast(new StringDecoder());
                                ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, String msg) {

                                        System.out.println(ch.attr(AttributeKey.valueOf("clientKey")));

                                        System.out.println(msg);
                                    }
                                });


                            }
                        }
                ).bind(8000);
        //----(3)-----------end---------------


        // 可以增加绑定监听器,在绑定返回结果后做一些处理
        channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    System.out.println("端口绑定成功!");
                } else {
                    System.err.println("端口绑定失败!");
                }
            }
        });
    }


    /**
     * 如果绑定失败,自动重试绑定方法,可以替代之前的绑定方法.
     *
     * @param serverBootstrap
     * @param port
     */
    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    System.out.println("端口[" + port + "]绑定成功!");
                } else {
                    System.err.println("端口[" + port + "]绑定失败!");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }


}
