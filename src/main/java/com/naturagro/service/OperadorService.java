package com.naturagro.service;

import com.naturagro.DAO.DAO;
import com.naturagro.models.Operador;

public class OperadorService extends DAO<Operador> {

    public OperadorService() {
        super(Operador.class);
    }
}
