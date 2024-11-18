package com.lojavirtual.gerenciamento_produtos.service;

import com.lojavirtual.gerenciamento_produtos.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private final List<Usuario> usuarios = new ArrayList<>();

    public void adicionar(Usuario usuario) {
        if (usuario.getSenha().length() < 4 || usuario.getSenha().equals(usuario.getLogin())
                || usuario.getSenha().trim().isEmpty() || usuario.getSenha().contains(" ")) {
            throw new IllegalArgumentException("Senha inválida");
        }
        if (usuarios.stream().anyMatch(u -> u.getLogin().equals(usuario.getLogin()))) {
            throw new IllegalArgumentException("Usuário com login já existe");
        }
        usuarios.add(usuario);
    }

    public void remover(Usuario usuario) {
        usuarios.remove(usuario);
    }

    public void atualizar(String login, String novaSenha) {
        Usuario usuario = usuarios.stream().filter(u -> u.getLogin().equals(login)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        usuario.setSenha(novaSenha);
    }

    public List<Usuario> getAll() {
        return new ArrayList<>(usuarios);
    }
}
