package me.ele.eleme.security.handle;

import cn.hutool.json.JSONUtil;
import me.ele.eleme.common.AjaxResult;
import me.ele.eleme.common.HttpStatus;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static me.ele.eleme.common.Constants.APPLICATION_JSON;

/**
 * 自定义退出处理类 返回成功
 */
@Configuration
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler
{
    /**
     * 退出处理
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        response.setContentType(APPLICATION_JSON);

        response.getWriter().write(JSONUtil.toJsonStr(AjaxResult.error(HttpStatus.SUCCESS, "退出成功")));
    }
}
