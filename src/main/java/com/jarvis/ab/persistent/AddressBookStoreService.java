package com.jarvis.ab.persistent;

import com.jarvis.ab.entity.Address;

import java.util.List;

/**
 * Created by jarvis on 12/02/2017.
 */
public interface AddressBookStoreService {

    /**
     * store all addresses to the local file.
     * @param addresses
     */
    public void save(List<Address> addresses);

    /**
     * retrieve all addresses to a list.
     * @return
     */
    public List<Address> load();
}
