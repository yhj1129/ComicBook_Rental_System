package com.cmbookrental.prj.controller;

import java.util.ArrayList;

public interface Controller<T> {
    public ArrayList<T> findAll();

    public void create(T parameter);

    public void update(Integer index, T parameter);

    public void delete(Integer ID);

}
