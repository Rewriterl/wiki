package com.stelpolvo.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stelpolvo.wiki.domain.Content;
import com.stelpolvo.wiki.domain.Doc;
import com.stelpolvo.wiki.domain.DocExample;
import com.stelpolvo.wiki.domain.RespPage;
import com.stelpolvo.wiki.domain.vo.BaseVo;
import com.stelpolvo.wiki.mapper.ContentMapper;
import com.stelpolvo.wiki.mapper.DocMapper;
import com.stelpolvo.wiki.utils.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DocService {
    @Resource
    private DocMapper docMapper;

    @Resource

    private ContentMapper contentMapper;

    @Resource
    private SnowFlake snowFlake;

    public List<Doc> all(Long ebookId) {
        DocExample docExample = new DocExample();
        docExample.createCriteria().andEbookIdEqualTo(ebookId);
        docExample.setOrderByClause("sort asc");
        return docMapper.selectByExample(docExample);
    }

    public RespPage<Doc> list(BaseVo docVo) {
        DocExample docExample = new DocExample();
        docExample.setOrderByClause("sort asc");
        PageHelper.startPage(docVo.getPage(), docVo.getSize());
        List<Doc> docList = docMapper.selectByExample(docExample);
        PageInfo<Doc> docPageInfo = new PageInfo<>(docList);
        return new RespPage<>(docPageInfo.getTotal(), docList);
    }

    public int save(Doc doc) {
        if (ObjectUtils.isEmpty(doc.getId())) {
            doc.setId(snowFlake.nextId());
            return docMapper.insert(doc);
        }
        return docMapper.updateByPrimaryKey(doc);
    }

    public int delete(Long id) {
        return docMapper.deleteByPrimaryKey(id);
    }

    public int delete(List<String> ids) {
        DocExample docExample = new DocExample();
        DocExample.Criteria criteria = docExample.createCriteria();
        criteria.andIdIn(ids);
        return docMapper.deleteByExample(docExample);
    }

}
