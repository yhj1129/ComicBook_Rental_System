package com.cmbookrental.prj.repository;

import java.util.ArrayList;

public interface Procezz<T> {

    ArrayList<T> findAll();

    void create(T parameter);

    void update(Integer index, T parameter1);

    void delete(Integer parameter);
}
