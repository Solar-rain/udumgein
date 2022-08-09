package me.ele.eleme.security.handle;

import cn.hutool.json.JSONUtil;
import me.ele.eleme.common.AjaxResult;
import me.ele.eleme.common.Constants;
import me.ele.eleme.security.LoginUser;
import me.ele.eleme.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static me.ele.eleme.common.Constants.APPLICATION_JSON;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

    final TokenService tokenService;
    @Autowired
    public SuccessHandler(TokenService tokenService){
        this.tokenService = tokenService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        AjaxResult ajax = AjaxResult.success();

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        System.out.println(loginUser);

        ajax.put(Constants.TOKEN, tokenService.createToken(loginUser));

        response.setContentType(APPLICATION_JSON);

        response.getWriter().write(JSONUtil.toJsonStr(ajax));
    }
}
