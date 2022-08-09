package me.ele.eleme.service.impl;

import cn.hutool.core.util.IdUtil;
import me.ele.eleme.common.Constants;
import me.ele.eleme.service.LoginService;
import me.ele.eleme.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    JavaMailSenderImpl mailSender;

    @Autowired
    RedisCache redisCache;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public String sendEmailCode(String email) {
        String code = String.valueOf(new Random().nextInt(899999) + 100000);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(email);
        message.setSubject("系统邮件");
        message.setText("您正在使用验证码登录，本次验证码为：" + code + "\n 如非本人操作，请忽略！谢谢");
        mailSender.send(message);
        String uuid = IdUtil.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        return uuid;
    }

    @Override
    public void validateEmailCode(String code, String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String checkCode = redisCache.getCacheObject(verifyKey);
        if (checkCode == null || !checkCode.equalsIgnoreCase(code)) {
            throw new AuthenticationServiceException("验证码错误或已过期");
        }
        redisCache.deleteObject(verifyKey);
    }

//    @Override
//    public void registerNewUser(String username) {
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(SecurityUtils.encryptPassword(Constants.INITIAL_PASSWORD));
//        userMapper.insert(user);
//    }
}
