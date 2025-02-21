package com.naturagro.bd;

import com.naturagro.models.CategoriaProduto;
import com.naturagro.models.Produto;
import com.naturagro.service.ProdutoService;

import java.util.Arrays;
import java.util.List;

public class AdicionarProdutos {
    public void adicionarP() {

        // Frutas
        Produto fruta1 = new Produto("Maçã", "Maçã vermelha fresca",2.50,  CategoriaProduto.Frutas);
        Produto fruta2 = new Produto("Banana", "Banana prata", 1.50, CategoriaProduto.Frutas);
        Produto fruta3 = new Produto("Laranja", "Laranja pera", 3.00, CategoriaProduto.Frutas);

        // Verduras
        Produto verdura1 = new Produto("Alface", "Alface crespa orgânica", 1.50, CategoriaProduto.Verduras);
        Produto verdura2 = new Produto("Couve", "Couve manteiga fresca", 2.00, CategoriaProduto.Verduras);
        Produto verdura3 = new Produto("Espinafre", "Espinafre orgânico", 2.50, CategoriaProduto.Verduras);

        // Carnes
        Produto carne1 = new Produto("Filé de Frango", "Filé de frango congelado", 10.00, CategoriaProduto.Carnes);
        Produto carne2 = new Produto("Carne Moída", "Carne moída de primeira", 15.00, CategoriaProduto.Carnes);
        Produto carne3 = new Produto("Costela Suína", "Costela suína defumada", 20.00, CategoriaProduto.Carnes);

        // Peixes
        Produto peixe1 = new Produto("Salmão", "Salmão fresco", 25.00, CategoriaProduto.Peixes);
        Produto peixe2 = new Produto("Tilápia", "Filé de tilápia", 12.00, CategoriaProduto.Peixes);
        Produto peixe3 = new Produto("Bacalhau", "Bacalhau dessalgado", 30.00,  CategoriaProduto.Peixes);

        // Laticinios
        Produto laticinio1 = new Produto("Leite", "Leite integral", 3.00, CategoriaProduto.Laticinios);
        Produto laticinio2 = new Produto("Queijo Mussarela", "Queijo mussarela fatiado", 20.00,
                CategoriaProduto.Laticinios);
        Produto laticinio3 = new Produto("Iogurte", "Iogurte natural",5.00,  CategoriaProduto.Laticinios);

        // Bebidas
        Produto bebida1 = new Produto("Cerveja", "Cerveja artesanal", 8.00, CategoriaProduto.Bebidas);
        Produto bebida2 = new Produto("Suco de Laranja", "Suco de laranja natural", 4.00,  CategoriaProduto.Bebidas);
        Produto bebida3 = new Produto("Água Mineral", "Água mineral sem gás", 2.00,  CategoriaProduto.Bebidas);

        // Alimentos
        Produto alimento1 = new Produto("Arroz", "Arroz branco", 4.00,  CategoriaProduto.Alimentos);
        Produto alimento2 = new Produto("Feijão", "Feijão preto", 6.00,  CategoriaProduto.Alimentos);
        Produto alimento3 = new Produto("Macarrão", "Macarrão espaguete", 3.00,  CategoriaProduto.Alimentos);

        // Higiene
        Produto higiene1 = new Produto("Sabonete", "Sabonete líquido", 5.00,  CategoriaProduto.Higiene);
        Produto higiene2 = new Produto("Shampoo", "Shampoo anticaspa", 12.00,  CategoriaProduto.Higiene);
        Produto higiene3 = new Produto("Creme Dental", "Creme dental branqueador", 6.00,  CategoriaProduto.Higiene);

        // Limpeza
        Produto limpeza1 = new Produto("Desinfetante", "Desinfetante para pisos", 6.00,  CategoriaProduto.Limpeza);
        Produto limpeza2 = new Produto("Detergente", "Detergente líquido", 3.00,  CategoriaProduto.Limpeza);
        Produto limpeza3 = new Produto("Limpador Multiuso", "Limpador multiuso", 7.00,
                CategoriaProduto.Limpeza);

        // Cosmeticos
        Produto cosmetico1 = new Produto("Batom", "Batom vermelho", 15.00, CategoriaProduto.Cosmeticos);
        Produto cosmetico2 = new Produto("Base", "Base líquida para pele", 25.00,  CategoriaProduto.Cosmeticos);
        Produto cosmetico3 = new Produto("Máscara de Cílios", "Máscara de cílios à prova d'água",20.00, CategoriaProduto.Cosmeticos);


        List<Produto> produtos = Arrays.asList(fruta1, fruta2, fruta3, verdura1, verdura2, verdura3, carne1, carne2, carne3, peixe1, peixe2, peixe3, laticinio1, laticinio2, laticinio3, bebida1, bebida2, bebida3, alimento1, alimento2, alimento3, higiene1, higiene2, higiene3, limpeza1, limpeza2, limpeza3, cosmetico1, cosmetico2, cosmetico3);

        ProdutoService produtoService = new ProdutoService();

        for (Produto produto : produtos) {
            produtoService.incluirAtomico(produto);
        }

    }
}
