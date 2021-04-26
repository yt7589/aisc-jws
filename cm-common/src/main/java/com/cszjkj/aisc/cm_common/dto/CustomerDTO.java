package com.cszjkj.aisc.cm_common.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

public class CustomerDTO implements Serializable {
    private long customerId;
    private String realName;
    private String emailAddr;
    private Calendar registerDate;
    private List<UserDTO> user;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public Calendar getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Calendar registerDate) {
        this.registerDate = registerDate;
    }

    public List<UserDTO> getUser() {
        return user;
    }

    public void setUser(List<UserDTO> user) {
        this.user = user;
    }
}
