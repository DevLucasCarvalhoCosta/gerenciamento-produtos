package com.lojavirtual.gerenciamento_produtos.service;

import com.lojavirtual.gerenciamento_produtos.model.Produto;
import com.lojavirtual.gerenciamento_produtos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto adicionar(Produto produto) {
        validarProduto(produto);

        if (produtoRepository.findByCodigo(produto.getCodigo()).isPresent()) {
            throw new IllegalArgumentException("Produto com este código já existe");
        }
        return produtoRepository.save(produto);
    }

    public void remover(String codigo) {
        Produto produto = produtoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        produtoRepository.delete(produto);
    }

    public void atualizar(String codigo, String novoNome, String novaDescricao, double novoPreco) {
        Produto produto = produtoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        validarProduto(new Produto(codigo, novoNome, novaDescricao, novoPreco));

        produto.setNome(novoNome);
        produto.setDescricao(novaDescricao);
        produto.setPreco(novoPreco);
        produtoRepository.save(produto);
    }

    public List<Produto> getAll() {
        return produtoRepository.findAll();
    }

    private void validarProduto(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório");
        }
        if (produto.getDescricao().length() > 200) {
            throw new IllegalArgumentException("Descrição do produto excede 200 caracteres");
        }
        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("Preço do produto deve ser maior que zero");
        }
    }
}
