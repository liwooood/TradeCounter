package com.cssweb.network;


import com.cssweb.util.ByteArrayIntUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * Created by chenhf on 14-1-4.
 */
public class CustomMessageHeader {
    private static final Logger logger = LogManager
            .getLogger(CustomMessageHeader.class.getName());
    public final static int MSG_HEADER_SIZE = 14;
    //public final static int MSG_HEADER_SIZE = 4;

    private int msgContentSize;
    private int crc;
    private byte zip;
    private byte msgType;
    private int functionNo;
    private byte[] msgHeader;


    public byte[] getMsgHeader() {
        return msgHeader;
    }

    public void setMsgHeader(byte[] msgHeader) {
        this.msgHeader = msgHeader;
    }

    public int getMsgContentSize() {


        return msgContentSize;
    }

    public void setMsgContentSize(int msgContentSize) {
        this.msgContentSize = msgContentSize;
    }

    public int getCrc() {
        return crc;
    }

    public void setCrc(int crc) {
        this.crc = crc;
    }

    public byte getZip() {
        return zip;
    }

    public void setZip(byte zip) {
        this.zip = zip;
    }

    public byte getMsgType() {
        return msgType;
    }

    public void setMsgType(byte msgType) {
        this.msgType = msgType;
    }

    public int getFunctionNo() {
        return functionNo;
    }

    public void setFunctionNo(int functionNo) {
        this.functionNo = functionNo;
    }


    /*
    编码消息头
     */
    public boolean encode() {
        // 可以使用java aio ByteBuffer
        // 可以使用netty ByteBuf
        try {
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(buf);

            byte[] temp = ByteArrayIntUtil.intToByteArrayN(msgContentSize, 4); // 新版本
            // byte[] temp =  Util.hton(msgContentSize); // 老版本

            out.write(temp, 0, 4);


            out.writeInt(crc);
            out.writeByte(zip);
            out.writeByte(msgType);
            out.writeInt(functionNo);


            msgHeader = buf.toByteArray();

            out.close();
            buf.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /*
    解析消息头
     */
    public boolean decode(byte[] msgHeader) {
        try {

            ByteArrayInputStream buf = new ByteArrayInputStream(msgHeader);
            DataInputStream in = new DataInputStream(buf);

            byte[] size = new byte[4];

            in.readFully(size);

            msgContentSize = ByteArrayIntUtil.byteArrayToIntN(size); // 新版本

            //  msgContentSize = Util.ntoh(size); // 老版本
            //logger.info("消息内容大小" + msgContentSize);

            crc = in.readInt();
            zip = in.readByte();
            msgType = in.readByte();
            functionNo = in.readInt();

            in.close();
            buf.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


}
