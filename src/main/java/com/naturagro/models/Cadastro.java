package com.naturagro.models;

import com.naturagro.DAO.DAO;

import javax.persistence.*;
import static com.naturagro.DAO.DAO.emf;

//criando a classe que guarda as informações de login
@Entity
//todo: alterar a tabela automaticamente pra o id ser autoincrement
@Table(name = "funcionarios") //tabela genérica de teste
public class Cadastro extends DAO {
    @Id
    // Remova @GeneratedValue se nomeusuario é manual
    private String nomeusuario;

    private String senha;
    private String nome;
    private String cpf;

    public Cadastro(String nomeusuario, String senha) {
        this.nomeusuario = nomeusuario;
        this.senha = senha;
    }

    public Cadastro(){};

    //todo: mudar logica (desde o front) pra quando cadastrar funcionario, tambem ter nome e cpf
    public void adicionarCadastroBanco(Cadastro cadastro){
        abrirT();
        incluir(cadastro);
        fecharT();
        fechar();
    }
}
