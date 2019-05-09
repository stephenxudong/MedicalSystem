package web.service;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.DatatypeConverter;

import org.json.JSONObject;

public class VideoToWord {
    private static final String serverURL = "http://vop.baidu.com/server_api";
    private static String token = "";
    private static final String testFileName = "D:/JAVA/Free-Converter.com-20180704215454-765135698.wav";//需要识别的音频文件
    //put your own params here
    private static final String apiKey = "yFraw4Lp1znbF1ISiMkwarA1";
    private static final String secretKey = "MgjD2nLt5t2RtyfGQKsqkDal74Iw8y5i";
    private static final String cuid = "C8-5B-76-11-B6-B5";

    public VideoToWord(){
        try {
            getToken();
        } catch (Exception e) {
            System.out.println("error in create converter of NLP");
            e.printStackTrace();
        }
    }

    public String toNL(byte[] bytes)
    {
        String str = "";
        try {
            str =  method1(bytes);
        } catch (Exception e) {
            System.out.println("convert error");
            e.printStackTrace();
        }
        if(str!=null)return str;
        return null;
    }


    private static void getToken() throws Exception {
        String getTokenURL = "https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials" +
                "&client_id=" + apiKey + "&client_secret=" + secretKey;
        HttpURLConnection conn = (HttpURLConnection) new URL(getTokenURL).openConnection();
        token = new JSONObject(printResponse(conn)).getString("access_token");
    }


    private static String method1(byte[] bytes) throws Exception {
        File wavFile = new File("/Users/stephen/out.wav");
        HttpURLConnection conn = (HttpURLConnection) new URL(serverURL).openConnection();

//        OutputStream out = new FileOutputStream("/Users/stephen/out.wav");
//        out.write(bytes);
//        out.close();
        // construct params
        JSONObject params = new JSONObject();
        params.put("format", "wav");
        params.put("rate", 16000);
        params.put("channel", "1");
        params.put("token", token);
        params.put("cuid", cuid);
        params.put("lan", "zh");
        params.put("len", bytes.length);
        //write bytes
        params.put("speech", DatatypeConverter.printBase64Binary(bytes));


        // add request header
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");


        conn.setDoInput(true);
        conn.setDoOutput(true);


        // send request
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(params.toString());
        wr.flush();
        wr.close();


        return printResponse(conn);
    }


    private static void method2() throws Exception {
        File wavFile = new File(testFileName);
        HttpURLConnection conn = (HttpURLConnection) new URL(serverURL
                + "?cuid=" + cuid + "&token=" + token).openConnection();


        // add request header
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "audio/wav; rate=16000");


        conn.setDoInput(true);
        conn.setDoOutput(true);


        // send request
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.write(loadFile(wavFile));
        wr.flush();
        wr.close();


        printResponse(conn);
    }


    private static String printResponse(HttpURLConnection conn) throws Exception {
        if (conn.getResponseCode() != 200) {
            // request error
            return "";
        }
        InputStream is = conn.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuffer response = new StringBuffer();
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        System.out.println(new JSONObject(response.toString()).toString(4));
        //System.out.println(response.toString());
        return response.toString();
    }


    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);


        long length = file.length();
        byte[] bytes = new byte[(int) length];


        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }


        if (offset < bytes.length) {
            is.close();
            throw new IOException("Could not completely read file " + file.getName());
        }


        is.close();
        return bytes;
    }


    public static String getChinese(String paramValue) {
        String regex = "([\u4e00-\u9fa5]+)";
        String str = "";
        Matcher matcher = Pattern.compile(regex).matcher(paramValue);
        while (matcher.find()) {
            str+= matcher.group(0);
        }
        return str;
    }


}
