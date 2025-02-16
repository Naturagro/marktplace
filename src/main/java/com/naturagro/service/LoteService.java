package com.naturagro.service;

import com.naturagro.DAO.DAO;
import com.naturagro.models.Lote;

public class LoteService extends DAO<Lote> {
    public LoteService() {
        super(Lote.class);
    }
}
