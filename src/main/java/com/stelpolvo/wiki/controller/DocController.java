package com.stelpolvo.wiki.controller;

import com.stelpolvo.wiki.annotation.Log;
import com.stelpolvo.wiki.domain.Doc;
import com.stelpolvo.wiki.domain.Resp;
import com.stelpolvo.wiki.domain.vo.BaseVo;
import com.stelpolvo.wiki.service.DocService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

@RestController
@RequestMapping("/doc")
public class DocController {
    @Resource
    private DocService docService;

    @GetMapping("/list")
    @Log("条件查询文档列表")
    public Resp list(BaseVo docVo) {
        return Resp.ok(docService.list(docVo));
    }

    @PostMapping("/save")
    @Log("修改或保存文档信息")
    public Resp save(@RequestBody Doc doc) {
        if (ObjectUtils.isEmpty(doc.getEbookId()) ||
                ObjectUtils.isEmpty(doc.getParent()) ||
                ObjectUtils.isEmpty(doc.getName()) ||
                ObjectUtils.isEmpty(doc.getSort())) {
            return Resp.error("请填写全部信息");
        }
        return docService.save(doc) > 0 ? Resp.ok("保存成功") : Resp.error("保存失败");
    }

    @DeleteMapping("/delete/{ids}")
    @Log("批量删除文档")
    public Resp delete(@PathVariable String ids) {
        int delete = docService.delete(Arrays.asList(ids.split(",")));
        return delete > 0 ? Resp.ok("批量删除成功") : Resp.error("批量删除失败");
    }
}
