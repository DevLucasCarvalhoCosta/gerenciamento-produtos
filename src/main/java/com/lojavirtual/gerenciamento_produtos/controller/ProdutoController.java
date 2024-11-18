package com.lojavirtual.gerenciamento_produtos.controller;

import com.lojavirtual.gerenciamento_produtos.model.Produto;
import com.lojavirtual.gerenciamento_produtos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

   
    @PostMapping
    public Produto adicionar(@RequestBody Produto produto) {
        return produtoService.adicionar(produto);
    }


    @DeleteMapping("/{codigo}")
    public void remover(@PathVariable String codigo) {
        produtoService.remover(codigo);
    }

    @PutMapping("/{codigo}")
    public void atualizar(@PathVariable String codigo, @RequestBody Produto novoProduto) {
        produtoService.atualizar(codigo, novoProduto.getNome(), novoProduto.getDescricao(), novoProduto.getPreco());
    }


    @GetMapping
    public List<Produto> getAll() {
        return produtoService.getAll();
    }
}
