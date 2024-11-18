package com.lojavirtual.gerenciamento_produtos;

import com.lojavirtual.gerenciamento_produtos.model.Produto;
import com.lojavirtual.gerenciamento_produtos.repository.ProdutoRepository;
import com.lojavirtual.gerenciamento_produtos.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProdutoServiceTest {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @BeforeEach
    public void setUp() {
        produtoRepository.deleteAll();
    }

    @Test
    public void testAdicionarProdutoComSucesso() {
        Produto produto = new Produto("12345", "Produto Teste", "Descrição válida", 10.0);

        Produto produtoSalvo = produtoService.adicionar(produto);

        assertNotNull(produtoSalvo);
        assertEquals("Produto Teste", produtoSalvo.getNome());
        assertEquals("Descrição válida", produtoSalvo.getDescricao());
        assertEquals(10.0, produtoSalvo.getPreco());
    }

    @Test
    public void testAdicionarProdutoComCodigoDuplicado() {
        Produto produto = new Produto("12345", "Produto Teste", "Descrição válida", 10.0);
        produtoService.adicionar(produto);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
                () -> produtoService.adicionar(produto));
        assertEquals("Produto com este código já existe", exception.getMessage());
    }

    @Test
    public void testAtualizarProdutoComSucesso() {
        Produto produto = new Produto("12345", "Produto Teste", "Descrição válida", 10.0);
        produtoService.adicionar(produto);

        produtoService.atualizar("12345", "Produto Atualizado", "Nova descrição", 15.0);

        Produto produtoAtualizado = produtoRepository.findByCodigo("12345").orElseThrow();
        assertEquals("Produto Atualizado", produtoAtualizado.getNome());
        assertEquals("Nova descrição", produtoAtualizado.getDescricao());
        assertEquals(15.0, produtoAtualizado.getPreco());
    }

    @Test
    public void testRemoverProduto() {
        Produto produto = new Produto("12345", "Produto Teste", "Descrição válida", 10.0);
        produtoService.adicionar(produto);

        produtoService.remover("12345");

        assertFalse(produtoRepository.findByCodigo("12345").isPresent());
    }

    @Test
    public void testGetAllProdutos() {
        Produto produto1 = new Produto("12345", "Produto Teste 1", "Descrição 1", 10.0);
        Produto produto2 = new Produto("12346", "Produto Teste 2", "Descrição 2", 20.0);

        produtoService.adicionar(produto1);
        produtoService.adicionar(produto2);

        var produtos = produtoService.getAll();

        assertNotNull(produtos);
        assertEquals(2, produtos.size());
    }
}
