package com.studydemo.demo.annotation;

/**
 * @author 孙浩林
 * @date: 3/4/23 15:29
 */
public class PassWordUtils {

    @UserCase.UserCases(id="47",description = "密码至少有一个数")
    public boolean validatePassword(String password){
        return (password.matches("\\w*\\d\\w*"));
    }

    @UserCase.UserCases(id="48")
    public String encryptPassword(String password){
        return new StringBuffer(password).reverse().toString();
    }


}
