//package com.cgh.ro635bot.listener.qqprivate;
//
//import love.forte.simbot.annotation.OnPrivate;
//import love.forte.simbot.api.message.events.PrivateMsg;
//import love.forte.simbot.api.sender.MsgSender;
//import org.springframework.stereotype.Service;
//
///**
// * 私聊监听
// *
// * @author Akuma
// * @date 2021/4/5 14:56
// */
//@Service
//public class TestPrivateListener {
//
////    private final MessageContentBuilderFactory messageContentBuilderFactory;
////
////    @Autowired
////    public TestPrivateListener(MessageContentBuilderFactory messageContentBuilderFactory) {
////        this.messageContentBuilderFactory = messageContentBuilderFactory;
////    }
//
////    @Autowired
////    private TestDao testDao;
//
//    /**
//     * 复读测试
//     *
//     * @param privateMsg
//     * @param sender
//     */
//    @OnPrivate
//    public void repeatMsg(PrivateMsg privateMsg, MsgSender sender) {
////        MessageContentBuilder builder = messageContentBuilderFactory.getMessageContentBuilder();
////        Test test = testDao.getTestById(1);
////        MessageContent msg = builder.text(test.getWord()).build();
//        sender.SENDER.sendPrivateMsg(privateMsg.getAccountInfo(), privateMsg.getMsgContent());
//    }
//
//}
