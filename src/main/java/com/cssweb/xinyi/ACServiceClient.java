package com.cssweb.xinyi;

import java.util.Map;

import com.cssweb.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class ACServiceClient {
    private static final Logger logger = LogManager.getLogger(
            ACServiceClient.class.getName());

    private static final String FIELD_SEPERATOR = String.valueOf((char) 0x01);
/*
    ACService acService;

    public boolean createService() {
        String ACSERVICE_URL = Config.getInstance().getService();

        acService = ACService.CreateService(ACSERVICE_URL, null, null);


        if (acService == null) {
            logger.error("连接新意失败");
            return false;
        }

        logger.info("连接新意成功");
        return true;
    }

    public String userRegister(Map<String, String> mapRequest) {

        String response = "";


        ShineParameter params[] = new ShineParameter[5];

        params[0] = new ShineParameter("FUNDACCOUNT", mapRequest.get("fundaccount"));
        params[1] = new ShineParameter("NICKNAME", mapRequest.get("nickname"));
        params[2] = new ShineParameter("USERPASS", mapRequest.get("userpass"));
        params[3] = new ShineParameter("CERTNO", mapRequest.get("certno"));
        params[4] = new ShineParameter("RELATIONTYPE", mapRequest.get("relationtype"));


        ShineResult result;
        result = acService.callService("userRegister", null, null, params);

        if (!result.getResultCode().equals("0000")) {


            response = "1" + FIELD_SEPERATOR + "2" + FIELD_SEPERATOR;
            response += "cssweb_errcode" + FIELD_SEPERATOR + "cssweb_errmsg" + FIELD_SEPERATOR;
            response += result.getResultCode() + FIELD_SEPERATOR + result.getResultMessage() + FIELD_SEPERATOR;


            return response;
        }


        response = "1" + FIELD_SEPERATOR + "4" + FIELD_SEPERATOR;
        response += "cssweb_errcode" + FIELD_SEPERATOR + "cssweb_errmsg" + FIELD_SEPERATOR + "custid" + FIELD_SEPERATOR + "passport" + FIELD_SEPERATOR;
        response += result.getResultCode() + FIELD_SEPERATOR + result.getResultMessage() + FIELD_SEPERATOR + result.getCustId() + FIELD_SEPERATOR + result.getPassport() + FIELD_SEPERATOR;


        return response;
    }


    public String userLogin(Map<String, String> mapRequest) {

        String response = "";


        ShineParameter params[] = new ShineParameter[3];

        params[0] = new ShineParameter("CUSTID", mapRequest.get("custid"));
        params[1] = new ShineParameter("USERPASS", mapRequest.get("userpass"));
        params[2] = new ShineParameter("IDTYPE", mapRequest.get("idtype"));


        ShineResult result;
        result = acService.callService("userLogin", mapRequest.get("custid"), null, params);

        if (!result.getResultCode().equals("0000")) {


            response = "1" + FIELD_SEPERATOR + "2" + FIELD_SEPERATOR;
            response += "cssweb_errcode" + FIELD_SEPERATOR + "cssweb_errmsg" + FIELD_SEPERATOR;
            response += result.getResultCode() + FIELD_SEPERATOR + result.getResultMessage() + FIELD_SEPERATOR;


            return response;
        }

        ShineResultSet rs = result.getResultSet();
        if (rs != null) {

        } else {

        }

        response = "1" + FIELD_SEPERATOR + "4" + FIELD_SEPERATOR;
        response += "cssweb_errcode" + FIELD_SEPERATOR + "cssweb_errmsg" + FIELD_SEPERATOR + "custid" + FIELD_SEPERATOR + "passport" + FIELD_SEPERATOR;
        response += result.getResultCode() + FIELD_SEPERATOR + result.getResultMessage() + FIELD_SEPERATOR + result.getCustId() + FIELD_SEPERATOR + result.getPassport() + FIELD_SEPERATOR;

        logger.info("登录返回：" + response);

        return response;
    }

    public String queryCustInfo(Map<String, String> mapRequest) {

        String response = "";


        ShineParameter params[] = new ShineParameter[2];

        params[0] = new ShineParameter("CUSTID", mapRequest.get("custid"));
        params[1] = new ShineParameter("PASSPORT", mapRequest.get("passport"));


        ShineResult result;
        result = acService.callService("queryCustInfo", mapRequest.get("custid"), mapRequest.get("passport"), params);

        if (!result.getResultCode().equals("0000")) {


            response = "1" + FIELD_SEPERATOR + "2" + FIELD_SEPERATOR;
            response += "cssweb_errcode" + FIELD_SEPERATOR + "cssweb_errmsg" + FIELD_SEPERATOR;
            response += result.getResultCode() + FIELD_SEPERATOR + result.getResultMessage() + FIELD_SEPERATOR;


            return response;
        }

        ShineResultSet rs = result.getResultSet();
        if (rs != null) {

            ColumnInfo[] cols = rs.getColumnInfoList();

            int colCount = cols.length;
            int rowCount = rs.getRecordCount();
            response = rowCount + FIELD_SEPERATOR + colCount + FIELD_SEPERATOR;

            for (int i = 0; i < cols.length; i++) {
                response += cols[i].getName() + FIELD_SEPERATOR;

            }

            String value = "";
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    value = rs.getString(row, col);
                    response += value + FIELD_SEPERATOR;
                }
            }
        }


        return response;
    }

    public String queryFundAccountList(Map<String, String> mapRequest) {

        String response = "";


        ShineParameter params[] = new ShineParameter[2];

        params[0] = new ShineParameter("CUSTID", mapRequest.get("custid"));
        params[1] = new ShineParameter("PASSPORT", mapRequest.get("passport"));


        ShineResult result;
        result = acService.callService("queryFundAccountList", mapRequest.get("custid"), mapRequest.get("passport"), params);

        if (!result.getResultCode().equals("0000")) {


            response = "1" + FIELD_SEPERATOR + "2" + FIELD_SEPERATOR;
            response += "cssweb_errcode" + FIELD_SEPERATOR + "cssweb_errmsg" + FIELD_SEPERATOR;
            response += result.getResultCode() + FIELD_SEPERATOR + result.getResultMessage() + FIELD_SEPERATOR;


            return response;
        }

        ShineResultSet rs = result.getResultSet();
        if (rs != null) {

            ColumnInfo[] cols = rs.getColumnInfoList();

            int colCount = cols.length;
            int rowCount = rs.getRecordCount();
            response = rowCount + FIELD_SEPERATOR + colCount + FIELD_SEPERATOR;

            for (int i = 0; i < cols.length; i++) {
                response += cols[i].getName() + FIELD_SEPERATOR;

            }

            String value = "";
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    value = rs.getString(row, col);
                    response += value + FIELD_SEPERATOR;
                }
            }
        }


        return response;
    }

    public String userLogOut(Map<String, String> mapRequest) {

        String response = "";


        ShineParameter params[] = new ShineParameter[2];
        params[0] = new ShineParameter("CUSTID", mapRequest.get("custid"));
        params[1] = new ShineParameter("PASSPORT", mapRequest.get("passport"));


        ShineResult result;
        result = acService.callService("userLogOut", mapRequest.get("custid"), mapRequest.get("passport"), params);

        if (!result.getResultCode().equals("0000")) {


            response = "1" + FIELD_SEPERATOR + "2" + FIELD_SEPERATOR;
            response += "cssweb_errcode" + FIELD_SEPERATOR + "cssweb_errmsg" + FIELD_SEPERATOR;
            response += result.getResultCode() + FIELD_SEPERATOR + result.getResultMessage() + FIELD_SEPERATOR;


            return response;
        }


        response = "1" + FIELD_SEPERATOR + "4" + FIELD_SEPERATOR;
        response += "cssweb_errcode" + FIELD_SEPERATOR + "cssweb_errmsg" + FIELD_SEPERATOR + "custid" + FIELD_SEPERATOR + "passport" + FIELD_SEPERATOR;
        response += result.getResultCode() + FIELD_SEPERATOR + result.getResultMessage() + FIELD_SEPERATOR + result.getCustId() + FIELD_SEPERATOR + result.getPassport() + FIELD_SEPERATOR;

        logger.info("用户注销返回：" + response);

        return response;
    }
*/

}
