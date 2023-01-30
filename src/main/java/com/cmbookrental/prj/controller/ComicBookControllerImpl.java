package com.cmbookrental.prj.controller;

import com.cmbookrental.prj.comm.CommonCode;
import com.cmbookrental.prj.dto.ComicBookDTO;
import com.cmbookrental.prj.factory.Factory;
import com.cmbookrental.prj.repository.Procezz;

import java.util.ArrayList;

public class ComicBookControllerImpl implements Controller<ComicBookDTO> {

    //레포지토리를 저장할 객체 생성
    private Procezz repository = null;

    //생성한 객체에 COMICBOOK 코드를 넣어 레포지토리 객체 연결
    public ComicBookControllerImpl() {
        repository = Factory.getInstance().getRepository(CommonCode.COMIC_BOOK);
    }
    // repository는  Procezz에서 선언한 출력, 생성, 수정, 삭제 메서드 사용 가능

    @Override
    public ArrayList<ComicBookDTO> findAll() {
        ArrayList<ComicBookDTO> comicBookList = repository.findAll();
        return comicBookList;
    }

    @Override
    public void create(ComicBookDTO parameter) {
        repository.create(parameter);
    }

    @Override
    public void update(Integer index, ComicBookDTO parameter) {
        repository.update(index, parameter);
    }

    @Override
    public void delete(Integer parameter) {
        repository.delete(parameter);
    }
}
