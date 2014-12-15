package com.cssweb.network;

import io.netty.channel.ChannelHandlerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Created by chenhf on 14-1-4.
 */
public class CustomMessage {
    private static final Logger logger = LogManager
            .getLogger(CustomMessage.class.getName());

    private CustomMessageHeader customMessageHeader;
    private byte[] msgContent = null;

    private ChannelHandlerContext channelHandlerContext;


    public ChannelHandlerContext getChannelHandlerContext() {
        return channelHandlerContext;
    }

    public void setChannelHandlerContext(ChannelHandlerContext channelHandlerContext) {
        this.channelHandlerContext = channelHandlerContext;
    }


    public void setMsgContent(byte[] msgContent) {
        this.msgContent = msgContent;
    }

    public byte[] getMsgContent() {
        return msgContent;
    }

    public CustomMessageHeader getCustomMessageHeader() {
        return customMessageHeader;
    }

    public void setCustomMessageHeader(CustomMessageHeader customMessageHeader) {
        this.customMessageHeader = customMessageHeader;
    }


}
