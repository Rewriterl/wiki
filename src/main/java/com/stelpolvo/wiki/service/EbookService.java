package com.stelpolvo.wiki.service;

import com.stelpolvo.wiki.domain.Ebook;
import com.stelpolvo.wiki.domain.EbookExample;
import com.stelpolvo.wiki.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EbookService {
    @Resource
    private EbookMapper ebookMapper;

    public Ebook getEbookByid(Long id){
        return ebookMapper.selectByPrimaryKey(id);
    }
}
