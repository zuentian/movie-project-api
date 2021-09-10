package com.zuer.zuerlvdoubanmovie.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/9/7 17:09
 */
@EnableAutoConfiguration
@RequestMapping(value = "/Test")
@RestController
public class TestController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    int count = 0;
    //@Autowired
    //private JedisPool jedisPool;
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public void test(){
        logger.info("TestController test start");
        int clientcount = 100;
        CountDownLatch countDownLatch = new CountDownLatch(clientcount);
        ExecutorService executorService = Executors.newFixedThreadPool(clientcount);
        long start  = System.currentTimeMillis();
        for(int i=0;i<clientcount;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    //RedisLock tool = new RedisLock(jedisPool);
                    String id = UUID.randomUUID().toString();
                    try {
                        //tool.lock(id,"AA");
                        count = count+1;
                        countDownLatch.countDown();
                    } finally {
                        //tool.unlock(id,"AA");
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("时间="+(end-start));
        System.out.println("合计="+count);
    }
}
