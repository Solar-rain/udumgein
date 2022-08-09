package me.ele.eleme.service;

public interface LoginService {
    String sendEmailCode(String email);

    void validateEmailCode(String code,String uuid);

//    void registerNewUser(String username);
}
