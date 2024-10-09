package br.com.dbengine.springb4.interfaces;

import java.io.Serializable;
import java.util.List;

public interface DAOInterface<E> {

    public List<E> getList();

    public void add(E obj);

    public void update(E obj);

    // public default E getItem(String id);
    public E getItem(String id);

//    public default E getItem(String id) {
//        return null;
//    }

    public String getCount();

    public String delete(String id);

}
