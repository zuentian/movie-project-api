package com.zuer.zuerlvdoubanauth;

import com.zuer.zuerlvdoubanauth.demo.DemoController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZuerLvdoubanAuthApplicationTests {
   @Autowired
   DemoController demoController;
    @Test
    public void tt() throws Exception {

        //开启myBatis二级缓存 注意实体类要序列化
        /*
        一级缓存的作用域在SqlSession中，
        mybatis在查询数据库的时候会创建SqlSessionFactory，类似于JDBC的Connection对象的statement对象，
        一个查询会创建sqlsession会话
        开启二级缓存之后，作用域于同一个namespace下的mapper映射文件内容，多个SqlSession共享
        开启方式，在service接口前面加上注解@CacheNamespace
        或者<mapper>标签下面加上<cache/>
        如果一个执行语句不想开启二级缓存，使用useCache="false


        为什么要实现序列化：

        （1）缓存机制：将查询结果保存到内存中

        （2）内存饱满，需要移出时，MyBatis就会自动将内存中的内容进行移除，
        但是文件很重要，不能，此时就需要进行序列化，以文件的形式将内容从内存保存到硬盘上，
        一个内容保存成文件的读写，必须实现序列化。
        **/
        System.out.println("第一次查询数据库");
        System.out.println(demoController.query());
        System.out.println("第二次查询数据库");
        System.out.println(demoController.query());
        /*
        如果强行手动在数据库中修改数据，查询的时候依然还是要取缓存，所以采用二级缓存就不要手动数据库
         */
    }

}
