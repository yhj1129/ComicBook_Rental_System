package com.cmbookrental.prj.controller;

import com.cmbookrental.prj.comm.CommonCode;
import com.cmbookrental.prj.dto.CustomerDTO;
import com.cmbookrental.prj.factory.Factory;
import com.cmbookrental.prj.repository.Procezz;

import java.util.ArrayList;

public class CustomerControllerImpl implements Controller<CustomerDTO> {

    //레포지토리를 저장할 객체 생성
    private Procezz repository = null;

    //생성한 객체에 Customer 코드를 넣어 레포지토리 객체 연결
    public CustomerControllerImpl() {
        repository = Factory.getInstance().getRepository(CommonCode.CUSTOMER);
    }
    // repository는  Procezz에서 선언한 출력, 생성, 수정, 삭제 메서드 사용 가능
    @Override
    public ArrayList<CustomerDTO> findAll() {
        ArrayList<CustomerDTO> customerList = repository.findAll();
        return customerList;
    }

    @Override
    public void create(CustomerDTO parameter) {
        repository.create(parameter);
    }

    @Override
    public void update(Integer index, CustomerDTO parameter) {
        repository.update(index, parameter);
    }

    @Override
    public void delete(Integer parameter) {
        repository.delete(parameter);
    }
}
