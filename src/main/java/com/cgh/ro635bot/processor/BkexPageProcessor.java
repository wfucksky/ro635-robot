//package com.cgh.ro635bot.processor;
//
//import com.cgh.ro635bot.entity.Notice;
//import com.cgh.ro635bot.service.NoticeService;
//import lombok.extern.slf4j.Slf4j;
//import love.forte.simbot.core.SimbotContext;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//import us.codecraft.webmagic.Page;
//import us.codecraft.webmagic.Site;
//import us.codecraft.webmagic.processor.PageProcessor;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLDecoder;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Slf4j
//@Component
//public class BkexPageProcessor implements PageProcessor {
//
//    private String key = "bkex";
//
//    private Site site = Site.me().setRetryTimes(5).setSleepTime(0).setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
//
//    @Autowired
//    SimbotContext context;
//    @Autowired
//    StringRedisTemplate stringRedisTemplate;
//    @Autowired
//    NoticeService noticeService;
//
//    @Override
//    public void process(Page page) {
//        List<String> all = page.getHtml().xpath("/html/body/main/div[2]/div/section/ul").links().all();
//        for (String s : all) {
//            String decode = null;
//            try {
//                decode = URLDecoder.decode(s, "UTF-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//
//            int end = decode.indexOf("-", decode.indexOf("-") + 1);
//            String url = StringUtils.substring(decode, 0, end);
//            System.out.println("url = " + url);
//            String title = StringUtils.substring(decode, end + 1, decode.length());
//            System.out.println("title = " + title);
//
//            // TODO: 2021/5/22 检查是否已经存在当前公告 redis
//            Boolean aBoolean = stringRedisTemplate.opsForHash().putIfAbsent(key, url, title);
//            if (aBoolean) {
//                context.getBotManager().getDefaultBot().getSender().SENDER.sendGroupMsg(958554525, key + "\n发布时间:" + LocalDateTime.now() + "\n----------\n" + title + "\n" + url + "\n无敌大帅哥采集时间" + LocalDateTime.now());
//                Notice entity = new Notice();
//                entity.setUrl(url);
//                entity.setTitle(title);
//                entity.setType(key);
//                entity.setUpdateTime(LocalDateTime.now());
//                noticeService.save(entity);
//            }
//        }
//
//    }
//
//    @Override
//    public Site getSite() {
//        return site;
//    }
//
////    public static void main(String[] args) {
////        Spider.create(new BkexPageProcessor()).addUrl("https://bkex.zendesk.com/hc/zh-cn/sections/360003824154-%E4%B8%8A%E5%B8%81%E5%85%AC%E5%91%8A").thread(1).run();
////    }
//}
