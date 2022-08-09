package me.ele.eleme.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CommonUtils {
    static public String newFileName(String fileName) {
        return IdUtil.simpleUUID() + fileName.substring(fileName.lastIndexOf("."));
    }

    static public String imageBase64(MultipartFile file) {
        try {
            return Base64.encode(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static public JSONObject fetchRequest2JSON(HttpServletRequest request) throws IOException {
        JSONObject jsonObject = null;
        BufferedReader streamReader = null;
        streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        jsonObject = JSONUtil.parseObj(responseStrBuilder.toString());
        streamReader.close();
        return jsonObject;
    }
}
