package com.study.luxin.netty.decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.util.CharsetUtil;

public class LineBasedHandlerInitializer extends ChannelInitializer<Channel> {

    static int i = 0;

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LineBasedFrameDecoder(65 * 1024));   //1
        pipeline.addLast(new FrameHandler());  //2
    }

    public static final class FrameHandler extends SimpleChannelInboundHandler<ByteBuf> {
        @Override
        public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {  //3
            System.out.println("frame" + i++ + msg.toString(CharsetUtil.US_ASCII));
        }
    }
}
