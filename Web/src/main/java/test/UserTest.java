package test;
import example.dao.Patient_accountMapper;
import example.pojo.Patient_account;
import example.uitl.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

public class UserTest {
    //static GynaecologyLogic g=new GynaecologyLogic();
    public static void main(String[] args) {
        SqlSession session = SFactory.getSqlSession();
        Patient_accountMapper mapper = session.getMapper(Patient_accountMapper.class);
        Patient_account user=mapper.selectByIdentiId("420624199902165131");
//        String host = "http://cowsms.market.alicloudapi.com";
//        String path = "/intf/smsapi";
//        String method = "GET";
//        String appcode = "6bc92f85b3fe497f8ce6052a239bfb96";
//        Map<String, String> headers = new HashMap<String, String>();
//        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//        headers.put("Authorization", "APPCODE " + appcode);
//        Map<String, String> querys = new HashMap<>();
//
//        String mobile = "18727069828";
//        int num=(int)(Math.random()*9000)+1000;
//        String paras = String.valueOf(num)+",1";
//
//        querys.put("mobile", mobile);
//        querys.put("paras", paras);
//        querys.put("sign", "医疗系统");
//        querys.put("tpid", "009");
//        HttpResponse response = null;
//        String msg = "OK";
//        try {
//            response = HttpUtils.doGet(host, path, method, headers, querys);
//            System.out.println(response.toString());
//            System.out.println(EntityUtils.toString(response.getEntity()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
