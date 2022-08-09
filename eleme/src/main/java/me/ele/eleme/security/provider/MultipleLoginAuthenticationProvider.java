package me.ele.eleme.security.provider;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import me.ele.eleme.domain.User;
import me.ele.eleme.security.LoginUser;
import me.ele.eleme.service.LoginService;
import me.ele.eleme.utils.SecurityUtils;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Setter
public class MultipleLoginAuthenticationProvider implements AuthenticationProvider {
    private PasswordEncoder passwordEncoder;
    private LoginService loginService;
    private String passwordParameter = "password";
    private String methodParameter = "method";
    private String codeParameter = "code";
    private String uuidParameter = "uuid";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JSONObject params = determineParams(authentication);
        String username = determineUsername(authentication);

        User user = new User();
        user.setUsername(username);
        user.setPassword(SecurityUtils.encryptPassword("123456"));
        if (params.getStr(methodParameter) == null) {
            throw new BadCredentialsException("登录出错");
        }
        switch (params.getStr(methodParameter)) {
            case "USERNAME_PASSWORD":
                String password = params.getStr(passwordParameter);
                if (password == null || matchesPassword(password, user)) {
                    throw new BadCredentialsException("用户名或密码错误");
                }
                break;
            case "USERNAME_CODE":
                String code = params.getStr(codeParameter);
                String uuid = params.getStr(uuidParameter);
                loginService.validateEmailCode(code, uuid);
                break;
            default:
                throw new BadCredentialsException("登录出错");
        }
        LoginUser loginUser = new LoginUser(user, authorityList());
        return createSuccessAuthentication(loginUser, authentication, authorityList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Authentication createSuccessAuthentication(Object principal, Authentication authentication, Collection<? extends GrantedAuthority> authorities) {
        UsernamePasswordAuthenticationToken result = UsernamePasswordAuthenticationToken.authenticated(principal, authentication.getCredentials(), authorities);
        result.setDetails(authentication.getDetails());
        return result;
    }

    private String determineUsername(Authentication authentication) {
        return authentication.getPrincipal() == null ? "NONE_PROVIDED" : authentication.getName();
    }

    private JSONObject determineParams(Authentication authentication) {
        Object o = authentication.getCredentials();
        return JSONUtil.parseObj(o);
    }

    private boolean matchesPassword(String password, User user) {
        return !this.passwordEncoder.matches(password, user.getPassword());
    }

    private List<GrantedAuthority> authorityList() {
        List<String> list = new ArrayList<>();
        list.add("ROLE_ADMIN");
        return list.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}

