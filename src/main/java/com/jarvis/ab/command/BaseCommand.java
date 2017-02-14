package com.jarvis.ab.command;

import com.jarvis.ab.entity.Address;
import com.jarvis.ab.service.BaseService;

/**
 * Created by jarvis on 10/02/2017.
 */
public abstract class BaseCommand implements Runnable{
    protected BaseService<Address> baseService;

    public void setBaseService(BaseService<Address> baseService) {
        this.baseService = baseService;
    }
}
