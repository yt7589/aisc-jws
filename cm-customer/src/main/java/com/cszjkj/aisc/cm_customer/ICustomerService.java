package com.cszjkj.aisc.cm_customer;

import com.cszjkj.aisc.cm_common.dto.CustomerDTO;

import java.util.Calendar;
import java.util.List;

public interface ICustomerService {
    /**
     * 根据条件查询符合条件的客户列表
     * @param realName 真实姓名
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param startIndex 开始位置
     * @param amount 获取条数
     * @return 符合条件的客户列表
     */
    public List<CustomerDTO> getCustomers(String realName,
                                          Calendar startTime, Calendar endTime,
                                          int startIndex, int amount);

    /**
     * 通过客户编号获取客户信息
     * @param customerId 客户编号
     * @return 客户信息值对像
     */
    public CustomerDTO getCustomerById(long customerId);

    /**
     * 添加客户
     * @param customerDTO 除customerId外的客户信息,users为空
     */
    public void addCustomer(CustomerDTO customerDTO);
}
