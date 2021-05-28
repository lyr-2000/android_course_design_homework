package io.github.lyr2000.novel_app.util;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author lyr
 * @create 2021/4/13 21:20
 */
@Slf4j
@Component
@AllArgsConstructor
public class OkHttpClientUtil {
    // @Autowired
    private final OkHttpClient okHttpClient;

    private static List<String> UserAgent
            = new ArrayList<>(35);

    static {
        UserAgent.add("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
        UserAgent.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.1 Safari/537.36");
        UserAgent.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36");
        UserAgent.add("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2226.0 Safari/537.36");
        UserAgent.add("Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; AS; rv:11.0) like Gecko");
        UserAgent.add("Mozilla/5.0 (compatible, MSIE 11, Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko");
        UserAgent.add("Mozilla/5.0 (compatible; MSIE 10.6; Windows NT 6.1; Trident/5.0; InfoPath.2; SLCC1; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET CLR 2.0.50727) 3gpp-gba UNTRUSTED/1.0");
        UserAgent.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 7.0; InfoPath.3; .NET CLR 3.1.40767; Trident/6.0; en-IN)");
        UserAgent.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)");
        UserAgent.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/6.0)");
        UserAgent.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/5.0)");
        UserAgent.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/4.0; InfoPath.2; SV1; .NET CLR 2.0.50727; WOW64)");
        UserAgent.add("Mozilla/5.0 (compatible; MSIE 10.0; Macintosh; Intel Mac OS X 10_7_3; Trident/6.0)");
        UserAgent.add("Mozilla/4.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/5.0)");
        UserAgent.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/532.2 (KHTML, like Gecko) ChromePlus/4.0.222.3 Chrome/4.0.222.3 Safari/532.2");
        UserAgent.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.28.3 (KHTML, like Gecko) Version/3.2.3 ChromePlus/4.0.222.3 Chrome/4.0.222.3 Safari/525.28.3");
        UserAgent.add("Opera/9.80 (X11; Linux i686; Ubuntu/14.10) Presto/2.12.388 Version/12.16");
        UserAgent.add("Opera/9.80 (Windows NT 6.0) Presto/2.12.388 Version/12.14");
        UserAgent.add("Mozilla/5.0 (Windows NT 6.0; rv:2.0) Gecko/20100101 Firefox/4.0 Opera 12.14");
        UserAgent.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.0) Opera 12.14");
        UserAgent.add("Opera/12.80 (Windows NT 5.1; U; en) Presto/2.10.289 Version/12.02");
        UserAgent.add("Opera/9.80 (Windows NT 6.1; U; es-ES) Presto/2.9.181 Version/12.00");
        UserAgent.add("Opera/9.80 (Windows NT 5.1; U; zh-sg) Presto/2.9.181 Version/12.00");
        UserAgent.add("Opera/12.0(Windows NT 5.2;U;en)Presto/22.9.168 Version/12.00");
        UserAgent.add("Opera/12.0(Windows NT 5.1;U;en)Presto/22.9.168 Version/12.00");
        UserAgent.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1");
        UserAgent.add("Mozilla/5.0 (Windows NT 6.3; rv:36.0) Gecko/20100101 Firefox/36.0");
        UserAgent.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10; rv:33.0) Gecko/20100101 Firefox/33.0");
        UserAgent.add("Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0");
        UserAgent.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:31.0) Gecko/20130401 Firefox/31.0");
        UserAgent.add("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0");
    }
// ————————————————
//     版权声明：本文为CSDN博主「hamish-wu」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//     原文链接：https://blog.csdn.net/keep_learn/article/details/104808993

    public static String randomIp() {

        //ip范围
        int[][] range = { { 607649792, 608174079 },//36.56.0.0-36.63.255.255
                { 1038614528, 1039007743 },//61.232.0.0-61.237.255.255
                { 1783627776, 1784676351 },//106.80.0.0-106.95.255.255
                { 2035023872, 2035154943 },//121.76.0.0-121.77.255.255
                { 2078801920, 2079064063 },//123.232.0.0-123.235.255.255
                { -1950089216, -1948778497 },//139.196.0.0-139.215.255.255
                { -1425539072, -1425014785 },//171.8.0.0-171.15.255.255
                { -1236271104, -1235419137 },//182.80.0.0-182.92.255.255
                { -770113536, -768606209 },//210.25.0.0-210.47.255.255
                { -569376768, -564133889 }, //222.16.0.0-222.95.255.255
        };

        Random rdint = new Random();
        int index = rdint.nextInt(10);
        String ip = num2ip(range[index][0]
                + new Random().nextInt(range[index][1] - range[index][0]));
        return ip;
        // return "";
    }

    public static String num2ip(int ip) {
        int[] b = new int[4];
        String x = "";

        b[0] = (int) ((ip >> 24) & 0xff);
        b[1] = (int) ((ip >> 16) & 0xff);
        b[2] = (int) ((ip >> 8) & 0xff);
        b[3] = (int) (ip & 0xff);
        x = b[0] + "." + b[1] + "."
                + b[2] + "." + b[3];

        return x;
    }


    public String randomUserAgent() {
        return UserAgent.get(ThreadLocalRandom.current().nextInt(0,UserAgent.size()<<1)%UserAgent.size());
    }

    // MediaType htmlType = MediaType.parse("content-type: text/html; charset=utf-8");
    public Request makeRequest(String url) {
        String myIp = randomIp();



        return new Request.Builder()
                .url(url)
                // .
                // .get()
                .header("Origin","https://www.biquduu.com")

                // .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Connection", "keep-alive")

                .addHeader("Referer","https://www.baidu.com")
                .addHeader("User-Agent",randomUserAgent())
                .addHeader("x-forwarded-for",myIp)
                .build();
    }
    public String getAsHtmlStr(String url) throws IOException {
        try (
                final Response execute = okHttpClient.newCall(makeRequest(url)).execute();
                ) {
            return execute.body().string();
        }catch (Exception ex) {
            // ex.printStackTrace();
            log.error("exceptioin ..{}", ex.getMessage());
            throw ex;
        }
        // return "";

    }


}
