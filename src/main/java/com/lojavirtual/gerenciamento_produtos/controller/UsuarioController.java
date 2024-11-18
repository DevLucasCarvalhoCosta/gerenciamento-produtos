package com.lojavirtual.gerenciamento_produtos.controller;

import com.lojavirtual.gerenciamento_produtos.model.Usuario;
import com.lojavirtual.gerenciamento_produtos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/adicionar")
    public void adicionar(@RequestBody Usuario usuario) {
        usuarioService.adicionar(usuario);
    }

    @DeleteMapping("/remover")
    public void remover(@RequestBody Usuario usuario) {
        usuarioService.remover(usuario);
    }

    @PutMapping("/atualizar/{login}")
    public void atualizar(@PathVariable String login, @RequestParam String novaSenha) {
        usuarioService.atualizar(login, novaSenha);
    }

    @GetMapping
    public List<Usuario> getAll() {
        return usuarioService.getAll();
    }
}
