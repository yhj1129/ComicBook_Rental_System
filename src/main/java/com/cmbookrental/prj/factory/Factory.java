package com.cmbookrental.prj.factory;

import com.cmbookrental.prj.comm.CommonCode;
import com.cmbookrental.prj.controller.*;
import com.cmbookrental.prj.repository.ComicBookProcessImpl;
import com.cmbookrental.prj.repository.CustomerProcessImpl;
import com.cmbookrental.prj.repository.Procezz;
import com.cmbookrental.prj.repository.RentalProcessImpl;

public class Factory {
    private static Factory instance;

    public static Factory getInstance(){
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }
    // 레포지토리 반환하기 ========================
    public Procezz getRepository(int type){
        Procezz repository = null;
        if (type == CommonCode.COMIC_BOOK)
            repository = new ComicBookProcessImpl();
        else if (type == CommonCode.CUSTOMER) {
            repository = new CustomerProcessImpl();
        }else if (type == CommonCode.RENTAL) {
            repository = new RentalProcessImpl();
        }
        return repository;
    }

//    public RentalProcess getRentalRepository(int type) {
//        RentalProcess repository = null;
//        if (type == CommonCode.REPOSITORY_RENTAL) {
//            repository = new RentalProcessImpl();
//        }
//        return repository;
//    }
    // =======================================
    // 컨트롤러 반환하기 ========================

    public Controller getController(int type){
        Controller controller = null;
        if (type == CommonCode.COMIC_BOOK)
            controller = new ComicBookControllerImpl();
        else if (type == CommonCode.CUSTOMER) {
            controller = new CustomerControllerImpl();
        }else if (type == CommonCode.RENTAL) {
            controller = new RentalControllerImpl();
        }
        return controller;
    }
    public final Controller getComicBookController() {
        return new ComicBookControllerImpl();
    }
    public final Controller getCustomerController() {
        return new CustomerControllerImpl();
    }
    public final Controller getRentalController() {
        return new RentalControllerImpl();
    }
    // =======================================
}

