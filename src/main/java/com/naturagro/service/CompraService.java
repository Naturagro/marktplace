package com.naturagro.service;

import com.naturagro.data.DAO;
import com.naturagro.models.Venda;

public class CompraService extends DAO<Venda> {

    public CompraService() {
        super(Venda.class);
    }
}
