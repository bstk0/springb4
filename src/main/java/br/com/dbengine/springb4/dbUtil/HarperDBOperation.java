package br.com.dbengine.springb4.dbUtil;

import br.com.dbengine.springb4.entity.ImovelOcorrencia;

import java.util.ArrayList;

public class HarperDBOperation {
    private String operation;
    private String schema;

    private String table;
    //private ArrayList<ImovelOcorrencia> records;

    public HarperDBOperation() {
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

}
