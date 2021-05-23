package com.cgh.ro635bot;

import love.forte.simbot.spring.autoconfigure.EnableSimbot;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类。
 * 其中，{@link SpringBootApplication} 为springboot的启动注解，
 * {@link EnableSimbot} 为simbot在springboot-starter下的启动注解。
 *
 * @author ForteScarlet
 */
@EnableSimbot
@EnableScheduling
@MapperScan("com.cgh.ro635bot.dao")
@SpringBootApplication
public class Ro635botApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ro635botApplication.class, args);
    }

}
