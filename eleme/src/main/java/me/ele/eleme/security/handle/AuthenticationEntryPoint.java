package me.ele.eleme.security.handle;

import cn.hutool.json.JSONUtil;
import me.ele.eleme.common.AjaxResult;
import me.ele.eleme.common.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

import static me.ele.eleme.common.Constants.APPLICATION_JSON;

/**
 * 认证失败处理类 返回未授权
 */
@Component
public class AuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint, Serializable
{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {

        response.setContentType(APPLICATION_JSON);

        response.getWriter().write(JSONUtil.toJsonStr(AjaxResult.error(HttpStatus.UNAUTHORIZED, "用户未登录")));
    }
}
