package com.naturagro.service;

import com.naturagro.DAO.DAO;
import com.naturagro.models.Funcionario;

public class FuncionarioService extends DAO<Funcionario> {

    public FuncionarioService() {
        super(Funcionario.class);
    }
}
