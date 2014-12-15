package com.cssweb.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class CustomDecoder extends ByteToMessageDecoder {
    private static final Logger logger = LogManager.getLogger(
            CustomDecoder.class.getName());

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        logger.info("decode");


        int readBytes = in.readableBytes();
        if (readBytes < CustomMessageHeader.MSG_HEADER_SIZE) {
            logger.info("��Ϣͷ���������Ѷ��ֽ���" + readBytes);
            return;
        }
        in.markReaderIndex();

        // ����Ϣͷ
        byte[] msgHeaderBytes = new byte[CustomMessageHeader.MSG_HEADER_SIZE];
        in.readBytes(msgHeaderBytes);

        CustomMessageHeader msgHeader = new CustomMessageHeader();
        msgHeader.decode(msgHeaderBytes);


        logger.info("��Ϣ���ݴ�С" + msgHeader.getMsgContentSize());


        // �ȴ�ֻ����Ϣ���ݽ������
        readBytes = in.readableBytes();
        if (readBytes < msgHeader.getMsgContentSize()) {
            in.resetReaderIndex();
            logger.info("��Ϣ���ݲ�����");
            return;
        }


        byte[] msgContent = new byte[msgHeader.getMsgContentSize()];
        in.readBytes(msgContent);


        CustomMessage customMessage = new CustomMessage();
        customMessage.setCustomMessageHeader(msgHeader);
        customMessage.setMsgContent(msgContent);
        customMessage.setChannelHandlerContext(ctx);

        out.add(customMessage);

    }
}
