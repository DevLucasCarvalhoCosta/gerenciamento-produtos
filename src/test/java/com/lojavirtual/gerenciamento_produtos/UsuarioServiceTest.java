package com.lojavirtual.gerenciamento_produtos;

import com.lojavirtual.gerenciamento_produtos.model.Usuario;
import com.lojavirtual.gerenciamento_produtos.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceTest {

    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService();
    }

    @Test
    void deveAdicionarUsuarioValido() {
        Usuario usuario = new Usuario("usuario1", "senha123");

        usuarioService.adicionar(usuario);

        List<Usuario> usuarios = usuarioService.getAll();
        assertEquals(1, usuarios.size());
        assertEquals("usuario1", usuarios.get(0).getLogin());
    }

    @Test
    void naoDeveAdicionarUsuarioComSenhaInvalida() {
        Usuario usuarioComSenhaCurta = new Usuario("usuario2", "123");
        Usuario usuarioComSenhaIgualAoLogin = new Usuario("usuario3", "usuario3");
        Usuario usuarioComSenhaVazia = new Usuario("usuario4", " ");
        Usuario usuarioComEspacos = new Usuario("usuario5", "senha com espacos");

        assertThrows(IllegalArgumentException.class, () -> usuarioService.adicionar(usuarioComSenhaCurta));
        assertThrows(IllegalArgumentException.class, () -> usuarioService.adicionar(usuarioComSenhaIgualAoLogin));
        assertThrows(IllegalArgumentException.class, () -> usuarioService.adicionar(usuarioComSenhaVazia));
        assertThrows(IllegalArgumentException.class, () -> usuarioService.adicionar(usuarioComEspacos));
    }

    @Test
    void naoDeveAdicionarUsuarioComLoginDuplicado() {
        Usuario usuario1 = new Usuario("usuario6", "senha123");
        Usuario usuario2 = new Usuario("usuario6", "senha456");

        usuarioService.adicionar(usuario1);
        assertThrows(IllegalArgumentException.class, () -> usuarioService.adicionar(usuario2));
    }

    @Test
    void deveRemoverUsuario() {
        Usuario usuario = new Usuario("usuario7", "senha123");

        usuarioService.adicionar(usuario);
        usuarioService.remover(usuario);

        List<Usuario> usuarios = usuarioService.getAll();
        assertTrue(usuarios.isEmpty());
    }

    @Test
    void deveAtualizarSenhaDoUsuario() {
        Usuario usuario = new Usuario("usuario8", "senha123");

        usuarioService.adicionar(usuario);
        usuarioService.atualizar("usuario8", "novaSenha123");

        List<Usuario> usuarios = usuarioService.getAll();
        assertEquals("novaSenha123", usuarios.get(0).getSenha());
    }

    @Test
    void deveLancarExcecaoSeUsuarioNaoExistirParaAtualizar() {
        assertThrows(IllegalArgumentException.class, () -> usuarioService.atualizar("usuario9", "novaSenha"));
    }

    @Test
    void deveRetornarListaDeUsuarios() {
        Usuario usuario1 = new Usuario("usuario10", "senha123");
        Usuario usuario2 = new Usuario("usuario11", "senha456");

        usuarioService.adicionar(usuario1);
        usuarioService.adicionar(usuario2);

        List<Usuario> usuarios = usuarioService.getAll();
        assertEquals(2, usuarios.size());
    }
}
