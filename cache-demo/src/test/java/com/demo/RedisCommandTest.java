package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chao.cheng
 * @createTime 2020/5/25 2:20 下午
 * @description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@Slf4j
public class RedisCommandTest {
    @Autowired
    private StringRedisTemplate template;

    @Test
    public void test() {
        //string
        template.opsForValue().set("abc","bbb");
        log.info("set:"+template.opsForValue().get("abc"));

        template.opsForValue().set("initValue", "1");
        template.opsForValue().increment("initValue");
        log.info("string set incr:"+template.opsForValue().get("initValue"));

        template.opsForValue().increment("initValue", 2);
        log.info("string incr:"+template.opsForValue().get("initValue"));

        template.opsForValue().decrement("initValue");
        log.info("string desc:"+template.opsForValue().get("initValue"));

        template.opsForValue().decrement("initValue", 2);
        log.info("string desc:"+template.opsForValue().get("initValue"));

        //list
        template.opsForList().rightPush("list","aaa");
        template.opsForList().rightPush("list","bbb");
        template.opsForList().rightPush("list","ccc");
        template.opsForList().rightPush("list","ddd");
        log.info("list rpop:"+template.opsForList().rightPop("list"));
        log.info("list index:"+template.opsForList().index("list",1));
        log.info("list range:"+template.opsForList().range("list",0,1).toString());

        //hash
        template.opsForHash().put("map","aaa","value-a");
        template.opsForHash().put("map","bbb","value-b");
        template.opsForHash().put("map","ccc","value-c");
        log.info("hash key:"+template.opsForHash().keys("map"));
        log.info("hash value:"+template.opsForHash().get("map","aaa"));
        log.info(template.opsForHash().hasKey("map","sss").toString());
        log.info("hash values:"+template.opsForHash().values("map"));

        //set
        template.opsForSet().add("set","aaa");
        template.opsForSet().add("set","bbb");
        template.opsForSet().add("set","ccc");
        log.info("set pop:"+template.opsForSet().pop("set"));

        //zset
        template.opsForZSet().add("zset","aaa",2);
        template.opsForZSet().add("zset","ccc",3);
        template.opsForZSet().add("zset","bbb",1);
        log.info("zset rank:"+template.opsForZSet().rank("zset","aaa").toString());
        log.info("zset zcard:"+template.opsForZSet().zCard("zset"));
    }
}
