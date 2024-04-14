package com.datn.watch.front.manage.merchandise;

import com.datn.watch.front.common.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(Constant.PAGE_MERCHANDISE_REVIEW)
public class ReviewController {
    @GetMapping(value = {"","list.html", "list"})
    public String list(Model model) {
        return "manage/merchandise/review/list";
    }

    @GetMapping("/insert")
    public String insert() {
        return "manage/merchandise/review/insert";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") long id, Model model) {
        model.addAttribute("id", id);
        return "manage/merchandise/review/update";
    }
}
