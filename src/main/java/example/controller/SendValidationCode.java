package example.controller;

import example.uitl.HttpUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/sendValCode")
public class SendValidationCode {

    private static final Log logger = LogFactory.getLog(SendValidationCode.class);
    @RequestMapping("/getCode")
    public
    @ResponseBody  String addCode(@RequestBody Map<String,Object> info
            ,HttpSession session)
    {
        logger.info("=================================send validation code");
        String host = "http://cowsms.market.alicloudapi.com";
        String path = "/intf/smsapi";
        String method = "GET";
        String appcode = "6bc92f85b3fe497f8ce6052a239bfb96";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<>();

        String mobile = (String)info.get("phone");
        int num=(int)(Math.random()*9000)+1000;
        String paras = String.valueOf(num)+",1";
        session.setAttribute("validationCode",String.valueOf(num));

        querys.put("mobile", mobile);
        querys.put("paras", paras);
        querys.put("sign", "医疗系统");
        querys.put("tpid", "009");
        HttpResponse response = null;
        String msg = "OK";
        try {
             response = HttpUtils.doGet(host, path, method, headers, querys);
             logger.info(response.toString());
             logger.info(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
//            System.out.println(response.toString());
//            //获取response的body
//            System.out.println(EntityUtils.toString(response.getEntity()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return msg;
    }
}
