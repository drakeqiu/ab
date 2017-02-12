package com.jarvis.ab.service;

import com.jarvis.ab.entity.Address;
import com.jarvis.ab.persistent.AddressBookStoreService;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jarvis on 11/02/2017.
 */
public class AddressService implements BaseService<Address>{

    private AddressBookStoreService addressBookStoreService;

    private List<Address> addresses;

    public void setAddressBookStoreService(AddressBookStoreService addressBookStoreService) {
        this.addressBookStoreService = addressBookStoreService;
    }

    public List<Address> search(String condition, String value) {
        Iterator<Address> addressIterator = this.addresses.iterator();
        List<Address> resAddresses = new LinkedList<Address>();
        while (addressIterator.hasNext()) {
            String reg = value;
            try {
                Address resAddress = addressIterator.next();
                String propertyVal = BeanUtils.getProperty(resAddress, condition);
                if (propertyVal.matches(value)) {
                    resAddresses.add(resAddress);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return Collections.unmodifiableList(resAddresses);
    }

    public void add(Address address) {
        this.addresses.add(address);
    }

    public int delete(String condition,String value) {

        int count = 0;
        Iterator<Address> addressIterator = addresses.iterator();
        while (addressIterator.hasNext()) {
            try {
                Address curAddress = addressIterator.next();
                String propertyVal = BeanUtils.getProperty(curAddress, condition);
                if (propertyVal.matches(value)) {
                    addressIterator.remove();
                    ++count;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

        }
        return count;
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
