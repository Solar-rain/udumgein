package me.ele.eleme.security.handle;

import cn.hutool.json.JSONUtil;
import me.ele.eleme.common.AjaxResult;
import me.ele.eleme.common.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static me.ele.eleme.common.Constants.APPLICATION_JSON;

@Component
public class FailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {

        response.setContentType(APPLICATION_JSON);

        response.getWriter().write(JSONUtil.toJsonStr(AjaxResult.error(HttpStatus.ERROR, exception.getMessage())));
    }
}
