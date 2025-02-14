package com.naturagro.service;

import com.naturagro.DAO.DAO;
import com.naturagro.controllers.ControlException;
import com.naturagro.models.Funcionario;

public class LoginService extends DAO {

    public LoginService(){
        super(Funcionario.class);
    }

    private boolean existeLogin(String cpf, String senha) {
        String jpql = "SELECT f FROM Funcionario f WHERE f.cpf = :cpf AND f.senha = :senha";
        Funcionario funcionario = getEntityManager()
                .createQuery(jpql, Funcionario.class)
                .setParameter("cpf", cpf)
                .setParameter("senha", senha)
                .getResultStream()
                .findFirst()
                .orElse(null);

        return funcionario != null;
    }

    public void validarLogin(String cpf, String senha) throws ControlException{
        if(!(existeLogin(cpf, senha))){
            throw new ControlException("CPF ou senha não encontrada no sistema.");
        }
    }

}
