package com.cssweb.network;

import java.io.UnsupportedEncodingException;
import java.util.Map;


import com.cssweb.xinyi.ACServiceClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.google.common.base.Splitter;


public class WorkerThread implements Runnable {
    private static final Logger logger = LogManager.getLogger(
            WorkerThread.class.getName());

    private static final String FIELD_SEPERATOR = String.valueOf((char) 0x01);

    private CustomMessage req;

    private String response;


    public WorkerThread(CustomMessage req) {

        this.req = req;


    }

    public void run() {


        String request = new String(req.getMsgContent());
        logger.info("原始请求：" + request);


        request = request.replace(FIELD_SEPERATOR, "&");
        if (request.substring(request.length() - 1).compareTo("&") == 0)
            request = request.substring(0, request.length() - 1);
        logger.info("转换后请求：" + request);

        Map<String, String> mapRequest = Splitter.on('&').withKeyValueSeparator('=').split(request);

        if (mapRequest.size() <= 0) {


            response = "1" + FIELD_SEPERATOR + "2" + FIELD_SEPERATOR;
            response += "cssweb_errcode" + FIELD_SEPERATOR + "cssweb_errmsg" + FIELD_SEPERATOR;
            response += "1000" + FIELD_SEPERATOR + "参数错误" + FIELD_SEPERATOR;

            sendResponse();
            return;
        }


        if (!mapRequest.containsKey("cssweb_funcid")) {
            response = "1" + FIELD_SEPERATOR + "2" + FIELD_SEPERATOR;
            response += "cssweb_errcode" + FIELD_SEPERATOR + "cssweb_errmsg" + FIELD_SEPERATOR;
            response += "1000" + FIELD_SEPERATOR + "参数错误" + FIELD_SEPERATOR;

            sendResponse();
            return;
        }

/*
        ACServiceClient client = new ACServiceClient();
        if (!client.createService()) {
            response = "1" + FIELD_SEPERATOR + "2" + FIELD_SEPERATOR;
            response += "cssweb_errcode" + FIELD_SEPERATOR + "cssweb_errmsg" + FIELD_SEPERATOR;
            response += "1002" + FIELD_SEPERATOR + "连接新意服务器失败" + FIELD_SEPERATOR;
            sendResponse();
            return;
        }

        String func = mapRequest.get("cssweb_funcid");

        if (func.compareToIgnoreCase("userRegister") == 0) {
            response = client.userRegister(mapRequest);
        } else if (func.compareToIgnoreCase("userLogin") == 0) {
            response = client.userLogin(mapRequest);
        } else if (func.compareToIgnoreCase("userLogOut") == 0) {
            response = client.userLogOut(mapRequest);
        } else if (func.compareToIgnoreCase("queryCustInfo") == 0) {
            response = client.queryCustInfo(mapRequest);
        } else if (func.compareToIgnoreCase("queryFundAccountList") == 0) {
            response = client.queryFundAccountList(mapRequest);
        } else
        */
        {
            response = "1" + FIELD_SEPERATOR + "2" + FIELD_SEPERATOR;
            response += "cssweb_errcode" + FIELD_SEPERATOR + "cssweb_errmsg" + FIELD_SEPERATOR;
            response += "1001" + FIELD_SEPERATOR + "服务未实现" + FIELD_SEPERATOR;

        }


        sendResponse();
        return;

    }

    private void sendResponse() {


        CustomMessage res = new CustomMessage();
        // res.setChannel(req.getChannel());
/*
        String gbk = "";
        try {
            gbk = new String(response.getBytes(), "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            gbk = "1" + FIELD_SEPERATOR + "2" + FIELD_SEPERATOR;
            gbk += "cssweb_errcode" + FIELD_SEPERATOR + "cssweb_errmsg" + FIELD_SEPERATOR;
            gbk +=  "1001" + FIELD_SEPERATOR + "转gbk错误" + FIELD_SEPERATOR;
        }
*/
        try {
            res.setMsgContent(response.getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
        }

        CustomMessageHeader msgHeader = new CustomMessageHeader();
        msgHeader.setCrc(0);
        msgHeader.setFunctionNo(0);
        msgHeader.setZip((byte) 0);
        try {
            msgHeader.setMsgContentSize(response.getBytes("GBK").length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        msgHeader.setMsgType((byte) 0);
        msgHeader.encode();

        res.setCustomMessageHeader(msgHeader);


        req.getChannelHandlerContext().writeAndFlush(res);

		/*
        //ByteBuffer pkgBody = ByteBuffer.wrap(response.getBytes());
		ByteBuffer pkgHeader = null;
		try
		{
			pkgHeader = ByteBuffer.wrap(res.getMsgHeader());
		}
		catch(IOException exp){
			logger.error("");
		}
		
		
		//res.getChannel().write(pkgHeader, res, writerPkgHeaderHandler);
		*/

    }

}
