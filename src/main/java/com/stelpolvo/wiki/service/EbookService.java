package com.stelpolvo.wiki.service;

import com.stelpolvo.wiki.domain.Ebook;
import com.stelpolvo.wiki.domain.EbookExample;
import com.stelpolvo.wiki.domain.vo.EbookVo;
import com.stelpolvo.wiki.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {
    @Resource
    private EbookMapper ebookMapper;

    public Ebook getEbookByid(Long id) {
        return ebookMapper.selectByPrimaryKey(id);
    }

    public List<Ebook> list(EbookVo req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");
        return ebookMapper.selectByExample(ebookExample);
    }
}
