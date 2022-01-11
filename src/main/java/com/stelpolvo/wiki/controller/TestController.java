package com.stelpolvo.wiki.controller;

import com.stelpolvo.wiki.domain.Doc;
import com.stelpolvo.wiki.mapper.DocMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    DocMapper mapper;

    @GetMapping("/test")
    public Doc testQuery(){
        return mapper.selectByPrimaryKey(1L);
    }
}
