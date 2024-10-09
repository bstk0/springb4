package br.com.dbengine.springb4.interfaces;

import br.com.dbengine.springb4.Singleton.*;
import br.com.dbengine.springb4.entity.*;

import java.util.*;

public interface SingletonInterface<E> {

    public List<E> getInstance();

    void setInstance(List<E> instance);

    void refresh();

    E getItem(int id);

    int getCount();
}
