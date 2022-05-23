package com.ycyoes.demos.geetime.concurrent.practise.chapter39;

import com.ycyoes.demos.geetime.concurrent.practise.chapter16.ObjPool;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyEchoTest {
    public static void main(String[] args) {
        //事件处理器
        final EchoServerHandler serverHandler = new EchoServerHandler();
        //boss线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //worker线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();


        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(serverHandler);
                        }
                    });

            //bind服务端端口
            ChannelFuture f = b.bind(9090).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    //socket连接处理器
    static class EchoServerHandler extends ChannelInboundHandlerAdapter {
        //处理读事件
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            ctx.write(msg);
        }

        //处理读完成事件
        public void channelReadComplete(ChannelHandlerContext ctx) {
            ctx.flush();
        }

        //处理异常事件
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }
}
