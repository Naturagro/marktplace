package com.naturagro.service;

import com.naturagro.DAO.DAO;
import com.naturagro.controllers.ControlException;
import com.naturagro.models.Funcionario;

public class LoginService extends DAO {

    public LoginService(){
        super(Funcionario.class);
    }

    public Funcionario obterFuncionario(String cpf, String senha) {
        String jpql = "SELECT f FROM Funcionario f WHERE f.cpf = :cpf AND f.senha = :senha";
        return getEntityManager()
                .createQuery(jpql, Funcionario.class)
                .setParameter("cpf", cpf)
                .setParameter("senha", senha)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public String validarLogin(String cpf, String senha) throws ControlException {
        Funcionario funcionario = obterFuncionario(cpf, senha);

        if (funcionario == null) {
            throw new ControlException("CPF ou senha não encontrada no sistema.");
        }

        return funcionario.getCargo().name();
    }
}
