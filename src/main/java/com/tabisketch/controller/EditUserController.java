package com.tabisketch.controller;

import com.tabisketch.bean.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/edit")
public class EditUserController {

    @GetMapping
    public String get(final Model model) {
        //Todo　以下は仮の処理のため後ほど書き換える
        final var item = new User();
        item.setMailAddress("user@example.com");
        model.addAttribute("item", item);
        return "user/edit/index";
    }
}
