package com.cmbookrental.prj.repository;

import com.cmbookrental.prj.dto.CollectionDB;
import com.cmbookrental.prj.dto.ComicBookDTO;

import java.util.ArrayList;


import static com.cmbookrental.prj.dto.CollectionDB.COMIC_BOOK_DATA;
import static com.cmbookrental.prj.dto.CollectionDB.allData;

public class ComicBookProcessImpl implements Procezz<ComicBookDTO> {
    //-----------------------------------------------------------------//
    //CollectionDB 객체 생성
    CollectionDB collectionDB = CollectionDB.getInstance();

    //COMIC_BOOK_DATA를 key로 하는 arrayList 가져옴
    ArrayList<ComicBookDTO> comicBookList = (ArrayList<ComicBookDTO>) allData.get(COMIC_BOOK_DATA);

    //-----------------------------------------------------------------//
    @Override
    public ArrayList<ComicBookDTO> findAll() {

        return comicBookList;
    }

    // 새로운 만화책을 등록하는 메서드
    @Override
    public void create(ComicBookDTO comicBook) {
        //가져와서 comicBook add
        comicBookList.add(comicBook);
        //add한 ArrayList를 Map에 다시 저장
        allData.put(COMIC_BOOK_DATA, comicBookList);
    }

    // 만화책의 정보를 수정하는 메서드
    @Override
    public void update(Integer index, ComicBookDTO comicBook) {
        // 수정된 comicBook을 index에 저장
        comicBookList.set(index, comicBook);
    }

    // 입력받은 만화책을 삭제
    @Override
    public void delete(Integer id) {
        for (int i = 0; i < comicBookList.size(); i++){
            if(comicBookList.get(i).getId() == id){
                comicBookList.remove(i);
            }
        }
    }
}
