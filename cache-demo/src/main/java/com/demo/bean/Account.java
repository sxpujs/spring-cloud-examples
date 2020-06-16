package com.demo.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author chao.cheng
 * @createTime 2020/5/9 3:22 下午
 * @description
 **/

@Getter
@Setter
public class Account {
    private int id;
    private String name;

    public Account(String name) {
        this.name = name;
    }

}

