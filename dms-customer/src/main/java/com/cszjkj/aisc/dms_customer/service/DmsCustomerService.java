package com.cszjkj.aisc.dms_customer.service;

import com.cszjkj.aisc.cm_common.dto.CustomerDTO;
import com.cszjkj.aisc.cm_customer.ICustomerService;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

public class DmsCustomerService implements ICustomerService {
    private final static Logger logger = Logger.getLogger(DmsCustomerService.class.getCanonicalName());
    @Override
    public List<CustomerDTO> getCustomers(String realName, Calendar startTime, Calendar endTime, int startIndex, int amount) {
        logger.info("call DmsCustomerService.getCustomers");
        return null;
    }

    @Override
    public CustomerDTO getCustomerById(long customerId) {
        logger.info("call DmsCustomerService.getCustomerById");
        return null;
    }

    @Override
    public void addCustomer(CustomerDTO customerDTO) {
        logger.info("call DmsCustomerService.addCustomer");
    }
}
