package com.cmbookrental.prj.dto;

public class CustomerDTO {

    private int id;
    private String customerName;

    public CustomerDTO(int id, String customerName){
        this.id = id;
        this.customerName = customerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "고객 정보 | " +
                "고객 번호 : " + id +
                ", 고객 이름 : '" + customerName + '\'';
    }
}
