package xmu.crms.view;


import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xmu.crms.mapper.UserMapper;
import xmu.crms.security.auth.JwtService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtService jwtService;
    @Test
    public void test() {
        String code = "021vEgkC0zOmeg2HEWmC0Q7fkC0vEgkq";
        BufferedReader in = null;
        String openid = null;
        try {
            StringBuilder result =new StringBuilder();
            String appid = "wx57e13e3a357301c4";
            String secret = "14b7cb557cbd1e042daeb1a70919cb99";
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret
                    + "&js_code=" + code + "&grant_type=authorization_code";
            URL realurl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realurl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            JSONObject json = new JSONObject(result.toString());
            System.out.println("1111111111111111"+json);

            openid = json.getString("openid");
            System.out.println(openid);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
