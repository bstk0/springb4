package br.com.dbengine.springb4.DAO;

import br.com.dbengine.springb4.Singleton.*;
import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.*;
import br.com.dbengine.springb4.interfaces.*;
import org.json.simple.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class ImobiliariaDAO implements DAOInterface<Imobiliaria> {

    @Autowired
    private CanonicClient canDb; // = new CanonicClient();

    @Override
    public List<Imobiliaria> getList() {
        Sysout.s("Imob.getList ...");
        return ImobListSingleton.getInstance();
    }

    @Override
    public void add(Imobiliaria obj) {

    }

    @Override
    public void update(Imobiliaria obj) {

    }

    @Override
    public Imobiliaria getItem(String id) {
        return ImobListSingleton.getItem( Integer.parseInt(id)); //DAOInterface.super.getItem(id);
    }

    @Override
    public String getCount() {
        return String.valueOf(ImobListSingleton.getInstance().size());
    }

    @Override
    public String delete(String id) {
        return "";
    }
}
