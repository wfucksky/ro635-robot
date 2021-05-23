package com.cgh.ro635bot.processor;

import com.cgh.ro635bot.entity.Notice;
import com.cgh.ro635bot.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import love.forte.simbot.core.SimbotContext;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.selector.Html;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class NoticePageProcessor {

    @Autowired
    SimbotContext context;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    NoticeService noticeService;

    public void init(String key, String url, String xpath) throws IOException {

//        String url = "https://wbfex.zendesk.com/hc/zh-cn/sections/360006886913";

        OkHttpClient client = new OkHttpClient().newBuilder().callTimeout(5, TimeUnit.SECONDS).connectTimeout(5, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("获取公告失败:{},{}", key, url);
            return;
        }

        Html html = new Html(response.body().string(), url);

//        String xpath = "/html/body/main/div[2]/div/section/ul";
        List<String> links = html.xpath(xpath).links().all();
        for (String link : links) {
            String decode = null;
            try {
                decode = URLDecoder.decode(link, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            int end = decode.indexOf("-", decode.indexOf("-") + 1);
            String href = StringUtils.substring(decode, 0, end);
//            System.out.println("url = " + href);
            String title = StringUtils.substring(decode, end + 1, decode.length());
//            System.out.println("title = " + title);

            // TODO: 2021/5/22 检查是否已经存在当前公告 redis
            Object o = stringRedisTemplate.opsForHash().get(key, href);
            if (o == null) {
                log.info("新增公告: {} {} {}", key, title, href);
                stringRedisTemplate.opsForHash().put(key, href, title);
                // TODO: 2021/5/22 临时关闭
//                context.getBotManager().getDefaultBot().getSender().SENDER.sendGroupMsg(958554525, key + "\n----------\n" + title + "\n" + href + "\n无敌大帅哥采集时间" + LocalDateTime.now());
                Notice entity = new Notice();
                entity.setUrl(href);
                entity.setTitle(title);
                entity.setType(key);
                entity.setUpdateTime(LocalDateTime.now());
                noticeService.save(entity);
            }

        }


    }


//    public static void main(String[] args) throws IOException {
//
//        String url = "https://wbfex.zendesk.com/hc/zh-cn/sections/360006886913";
//        WbfPageProcessor wbfPageProcessor = new WbfPageProcessor();
//        wbfPageProcessor.init(url);
//
//
//    }
}
