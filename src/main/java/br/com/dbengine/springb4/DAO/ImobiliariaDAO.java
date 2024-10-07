package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.entity.*;
import br.com.dbengine.springb4.interfaces.*;

import java.util.*;

public class ImobiliariaDAO implements DAOInterface<Imobiliaria> {


    @Override
    public List<Imobiliaria> getList() {
        return List.of();
    }

    @Override
    public void add(Imobiliaria obj) {

    }

    @Override
    public void update(Imobiliaria obj) {

    }

    @Override
    public Imobiliaria getItem(String id) {
        return DAOInterface.super.getItem(id);
    }

    @Override
    public String getCount() {
        return "";
    }

    @Override
    public String delete(String id) {
        return "";
    }
}
