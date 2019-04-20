package com.example.netty.xml;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {


        String str = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?><Service> <Service_Header><service_sn>1010015000000125570</service_sn><service_id>00320000000001</service_id><system_id>0032</system_id><requester_id>0015</requester_id><branch_id>062001</branch_id><channel_id>01</channel_id><service_time>20190422161034</service_time><need_request>true</need_request><BnkSrlNo>001520190422062001181568</BnkSrlNo><SvcCd></SvcCd><SvcScn></SvcScn><FileFlg>0</FileFlg><FilePath></FilePath></Service_Header><Service_Body><ext_attributes></ext_attributes><request><REQ_FILE_FLAG>0</REQ_FILE_FLAG><REQ_FILE_ROWS>00000</REQ_FILE_ROWS><TABLE_NAME></TABLE_NAME><UPDATE_TYPE></UPDATE_TYPE><BRANCH_ID>062001</BRANCH_ID><BATCH_ID></BATCH_ID><TERMINAL_ID>14.10.2.15</TERMINAL_ID><PP0020>062001013103086281</PP0020><PP0607>57C1V</PP0607><PP1000>TP0421</PP1000><PP0609>01101</PP0609><PP0030>D6950</PP0030><PPTL10>1</PPTL10><PP0608>00000</PP0608><PP0070>20190422161034</PP0070><PP0140>20190422</PP0140><PP0601>01101</PP0601><PP0310>062001</PP0310><PP0320>062101</PP0320><PP0330>062001</PP0330><PP060D>000</PP060D><PP060C>1</PP060C><PP0540></PP0540><PP0550></PP0550><PP0606>14.10.2.15</PP0606><PP0605>1111</PP0605><PP060E>2</PP060E><PP0120>181476</PP0120><PP0110>181568</PP0110><PP0604>161042</PP0604></request></Service_Body></Service>";
        final int port = 10001;
        Socket socket = new Socket("127.0.0.1", port);
        final OutputStream out = socket.getOutputStream();
        XmlMsg customMsg = new XmlMsg(str.length(), str);
        try {
            out.write(customMsg.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}