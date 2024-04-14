package com.datn.watch.front.manage.sample.controller;

import com.datn.watch.common.Constant;
import com.datn.watch.front.manage.sample.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = Constant.PAGE_DEMO)
public class DemoController {
    @Autowired
    ArticleRepository mapper;

    @GetMapping()
    public String sample(Model model) {
        model.addAttribute("articles", mapper.findAll(null));
        return "sample/sample";
    }
}
