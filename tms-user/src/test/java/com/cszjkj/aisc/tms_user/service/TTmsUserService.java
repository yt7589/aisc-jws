package com.cszjkj.aisc.tms_user.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TTmsUserService {
    @DisplayName("测试用户注册接口1")
    @Test
    public void callRegisterUser1() {
        String targetRst = "Ok";
        String rst = "abc";
        Assertions.assertEquals(targetRst, rst);
    }
    @DisplayName("测试用户注册接口2")
    @Test
    public void callRegisterUser2() {
        String targetRst = "Ok";
        String rst = "Ok";
        Assertions.assertEquals(targetRst, rst);
    }
}
