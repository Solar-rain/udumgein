package me.ele.eleme.security;

import me.ele.eleme.domain.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

@Data
public class LoginUser implements Serializable {

    private User user;

    private String token;

    private Long loginTime;

    private Long expireTime;
    private final List<GrantedAuthority> authorities;

    public LoginUser(User user,List<GrantedAuthority> authorities){
        this.user = user;
        this.authorities = authorities;
    }

    public String getUsername(){
        return  user.getUsername();
    }

    public String getPassword(){
        return  user.getPassword();
    }
}
