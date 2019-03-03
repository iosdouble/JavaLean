package com.example.test;

/**
 * @ClassName TestASCII
 * @Author nihui
 * @Date 2019/2/28 15:43
 * @Version 1.0
 * @Description TODO
 */
public class TestASCII {
    public static void main(String[] args) {
        //String str = "HELLOWORLD";
        StringBuffer sb = new StringBuffer();
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Service><Service_Header><service_sn>1010053000001238187</service_sn><service_id>00160000000001</service_id><SvcCd>3004100</SvcCd><SvcScn>0101</SvcScn><branch_id>998003</branch_id><channel_id>08</channel_id><service_time>20191210095434</service_time><version_id>01</version_id><need_request>true</need_request></Service_Header><Service_Body><request><REQ_FILE_FLAG>0</REQ_FILE_FLAG><BRANCH_ID>998003</BRANCH_ID><PAYMENT_THREAD_NO>000954339108317rgdXqu3cBO</PAYMENT_THREAD_NO><PP0030>B3412</PP0030><PP0607>W560</PP0607><PP0110>238187</PP0110><PP0120>238186</PP0120><PP0140>20080918</PP0140><PP0310>998003</PP0310><PP0320>998003</PP0320><PP0641>998003</PP0641><PP0330>998003</PP0330><PP060C>W</PP060C><PP0609>99803</PP0609><PP060E>2</PP060E><PP1040>201912100192822711</PP1040><PP122G>ZFPT</PP122G><PP122H>1006</PP122H><PP1271>20180824</PP1271><PP070K>1</PP070K><PP070P>1</PP070P><PP0709>O</PP0709><PP070L>2</PP070L><PP0490>01</PP0490><PP0820>0.05</PP0820><PP0884>1.00</PP0884><PP0885>0.50</PP0885><PP0886>0.50</PP0886><PP1020>103601201020000169</PP1020><PP115M>天津市空某某</PP115M><PP1030>104801201010000236</PP1030><PP1245>天津市津钢冶炼厂</PP1245><PP1152>代付跨行ly</PP1152><PP070M>1</PP070M><PP0685>104801201010000236</PP0685><PP1212>天津市津钢冶炼厂</PP1212><PP114T>313100000013</PP114T><PP114Q>313110000017</PP114Q><PP0871>013689238</PP0871><PP114H>0</PP114H></request></Service_Body></Service>";
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i!=chars.length-1){
                sb.append((int)chars[i]).append(",");
            }else {
                sb.append((int)chars[i]);
            }
        }
        System.out.println(sb.toString());

        System.out.println(chars.length+ "  + "+str.length());
    }
}
