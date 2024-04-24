package br.com.dbengine.springb4.entity;

import lombok.*;

@Getter
@Setter
public class ControleGlicemia {
    private String id; //  SERIAL PRIMARY KEY,
    private String data; //           date      NOT NULL,
    private String valor; //           INT       NOT NULL,
    private String aparelho; //    varchar(15),
    private String estado; //       varchar(50),
    private String comentario; // varchar(50)
}
