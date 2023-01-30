package com.cmbookrental.prj.repository;

import com.cmbookrental.prj.dto.CollectionDB;
import com.cmbookrental.prj.dto.RentalDTO;

import java.util.ArrayList;

import static com.cmbookrental.prj.dto.CollectionDB.RENTAL_DATA;
import static com.cmbookrental.prj.dto.CollectionDB.allData;

public class RentalProcessImpl implements Procezz<RentalDTO>{

    //-----------------------------------------------------------------//
    //CollectionDB 객체 생성
    CollectionDB collectionDB = CollectionDB.getInstance();

    //CUSTOMER_DATA를 key로 하는 arrayList 가져옴
    ArrayList<RentalDTO> rentalList = (ArrayList<RentalDTO>) allData.get(RENTAL_DATA);

    //-----------------------------------------------------------------//
    @Override
    public ArrayList<RentalDTO> findAll() {
        return rentalList;
    }

    @Override
    public void create(RentalDTO rental) {
        //가져와서 rental add
        rentalList.add(rental);
        //add한 ArrayList를 Map에 다시 저장
        allData.put(RENTAL_DATA, rentalList);
    }

    @Override
    public void update(Integer index, RentalDTO rental) {
        // 수정된 rental을 index에 저장
        rentalList.set(index, rental);
    }

    @Override
    public void delete(Integer id) {
        // 대여 번호
        // 반납하면 삭제
        for (int i = 0; i < rentalList.size(); i++){
            if(rentalList.get(i).getId() == id){
                rentalList.remove(i);
            }
        }
    }
}
