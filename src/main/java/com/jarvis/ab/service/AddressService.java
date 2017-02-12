package com.jarvis.ab.service;

import com.jarvis.ab.entity.Address;
import com.jarvis.ab.persistent.AddressBookStoreService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jarvis on 11/02/2017.
 */
public class AddressService implements BaseService<Address>{

    private AddressBookStoreService addressBookStoreService;

    private List<Address> addresses;

    public void setAddressBookStoreService(AddressBookStoreService addressBookStoreService) {
        this.addressBookStoreService = addressBookStoreService;
    }

    public List<Address> search(String condition) {
        return null;
    }

    private List<Address> searchByName(String name) {
        return null;
    }

    private List<Address> searchByMobile(String mobile) {
        return null;
    }

    private List<Address> searchByAddress(String address) {
        return null;
    }

    public void add(Address address) {
        this.addresses.add(address);
    }

    public void delete(String condition) {
        if (condition.equals("name"))this.deleteByName(condition);
        else if (condition.equals("mobile")) this.deleteByMobile(condition);
        else if (condition.equals("address")) this.deleteByAddr(condition);

    }

    private void deleteByName(String name) {
        List<Address> removeAddresses = new LinkedList<Address>();
        for (Address addr : addresses) {
            String reg = name;
            Pattern pattern = Pattern.compile(reg);
            if (addr.getName().matches(reg)) {
                removeAddresses.add(addr);
            }
        }
        addresses.removeAll(removeAddresses);
    }

    private void deleteByMobile(String mobile) {
        List<Address> removeAddresses = new LinkedList<Address>();
        for (Address addr : addresses) {
            removeAddresses.add(addr);
        }
        addresses.removeAll(removeAddresses);
    }

    private void deleteByAddr(String address) {
        List<Address> removeAddresses = new LinkedList<Address>();
        for (Address addr : addresses) {
            removeAddresses.add(addr);

        }
        addresses.removeAll(removeAddresses);
    }

    public void save() {
        this.addressBookStoreService.save(addresses);
    }

    public void load() {
        this.addresses = this.addressBookStoreService.load();
    }

    public AddressService getInstance() {
        return new AddressService();
    }

    private AddressService(){}
}
