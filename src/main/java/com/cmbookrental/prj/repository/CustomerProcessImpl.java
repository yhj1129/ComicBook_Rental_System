package com.cmbookrental.prj.repository;

import com.cmbookrental.prj.dto.CollectionDB;
import com.cmbookrental.prj.dto.CustomerDTO;

import java.util.ArrayList;

import static com.cmbookrental.prj.dto.CollectionDB.CUSTOMER_DATA;
import static com.cmbookrental.prj.dto.CollectionDB.allData;

public class CustomerProcessImpl implements Procezz<CustomerDTO> {

    //-----------------------------------------------------------------//
    //CollectionDB 객체 생성
    CollectionDB collectionDB = CollectionDB.getInstance();

    //CUSTOMER_DATA를 key로 하는 arrayList 가져옴
    ArrayList<CustomerDTO> customerList = (ArrayList<CustomerDTO>) allData.get(CUSTOMER_DATA);

    //-----------------------------------------------------------------//
    @Override
    public ArrayList<CustomerDTO> findAll() {
        return customerList;
    }

    @Override
    public void create(CustomerDTO customer) {
        //가져와서 customer add
        customerList.add(customer);
        //add한 ArrayList를 Map에 다시 저장
        allData.put(CUSTOMER_DATA, customerList);
    }

    @Override
    public void update(Integer index, CustomerDTO customer) {
        // 수정된 customer를 index에 저장
        customerList.set(index, customer);
    }

    @Override
    public void delete(Integer id) {
        for (int i = 0; i < customerList.size(); i++){
            if(customerList.get(i).getId() == id){
                customerList.remove(i);
            }
        }
    }
}
