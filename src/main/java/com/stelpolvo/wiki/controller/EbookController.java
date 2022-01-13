package com.stelpolvo.wiki.controller;

import com.stelpolvo.wiki.annotation.Log;
import com.stelpolvo.wiki.domain.vo.EbookVo;
import com.stelpolvo.wiki.domain.Resp;
import com.stelpolvo.wiki.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    /* 由于基于字段的情况@Autowired使用set方式,如果有构造方法使用注入的属性的话则会报控制值异常
     静态代码块>代码块>构造器>执行顺序，而Spring会等待类完全被加载完的时候才会进行注入。
     所以为了避免这种情况的空指针我们可以选择使用@Resource或者使用@Autowire的构造器注入。
     为了规范化代码之后我都会使用@Resource来注入变量 */
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    @Log("条件查询电子书列表")
    public Resp list(EbookVo ebookVo) {
        return Resp.ok(ebookService.list(ebookVo));
    }
}
