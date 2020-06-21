package com.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/demo")
@Slf4j
public class RankingListController {

    @Autowired
    public StringRedisTemplate template;

    public ObjectMapper mapper = new ObjectMapper();

    public static final String SCORE_RANK = "score_rank";

    @RequestMapping("/show")
    public void show() {
        batchAdd();
        top10();
        add();
        find();
        count();
    }

    public void batchAdd() {
        Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            DefaultTypedTuple<String> tuple = new DefaultTypedTuple<>("张三" + i, 1D + i);
            tuples.add(tuple);
        }
        long runTime = System.currentTimeMillis() - startTime;
        log.info("runTime:" + runTime);
        template.opsForZSet().add(SCORE_RANK, tuples);
    }

    public void top10() {
        try {
            Set<String> range = template.opsForZSet().reverseRange(SCORE_RANK, 0, 10);
            log.info("获取到的排行列表:" + mapper.writeValueAsString(range));
            Set<ZSetOperations.TypedTuple<String>> rangeWithScores = template.opsForZSet().reverseRangeWithScores(SCORE_RANK, 0, 10);
            log.info("获取到的排行和分数列表:" + mapper.writeValueAsString(rangeWithScores));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void add() {
        template.opsForZSet().add(SCORE_RANK, "李四", 9000);
    }

    public void find() {
        Long rankNum = template.opsForZSet().reverseRank(SCORE_RANK, "李四");
        log.info("李四的个人排名：" + rankNum);
        Double score = template.opsForZSet().score(SCORE_RANK, "李四");
        log.info("李四的分数:" + score);
    }

    public void count() {
        Long count = template.opsForZSet().count(SCORE_RANK, 8001, 9000);
        log.info("统计8001-9000之间的人数:" + count);
    }

}
