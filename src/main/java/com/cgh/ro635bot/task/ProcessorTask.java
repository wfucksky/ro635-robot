package com.cgh.ro635bot.task;

import com.cgh.ro635bot.entity.NoticeParam;
import com.cgh.ro635bot.processor.NoticePageProcessor;
import com.cgh.ro635bot.service.NoticeParamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class ProcessorTask {

    @Autowired
    NoticePageProcessor noticePageProcessor;
    @Autowired
    NoticeParamService noticeParamService;

    @Scheduled(cron = "0/20 * * * * ?")
    public void run() {
        log.info("每10秒执行一次: {}", LocalDateTime.now());

        List<NoticeParam> list = noticeParamService.list();
        for (NoticeParam noticeParam : list) {
//            new Thread(() -> {
//                try {
            try {
                noticePageProcessor.init(noticeParam.getType(), noticeParam.getNoticeUrl(), noticeParam.getXpath());
            } catch (IOException e) {
                e.printStackTrace();
            }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }).start();

        }

    }


}