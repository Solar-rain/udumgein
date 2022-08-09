package me.ele.eleme.controller;

import me.ele.eleme.common.AjaxResult;
import me.ele.eleme.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/{email}")
    public AjaxResult sendEmailCode(@PathVariable String email){
        String uuid = loginService.sendEmailCode(email);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("uuid",uuid);
        return ajax;
    }
}
