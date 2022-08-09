package me.ele.eleme.security.handle;

import cn.hutool.json.JSONUtil;
import me.ele.eleme.common.AjaxResult;
import me.ele.eleme.common.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static me.ele.eleme.common.Constants.APPLICATION_JSON;

/**
 * 用户权限处理
 */
@Component
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException {

        response.setContentType(APPLICATION_JSON);

        response.getWriter().write(JSONUtil.toJsonStr(AjaxResult.error(HttpStatus.FORBIDDEN, "权限不足")));
    }

}
