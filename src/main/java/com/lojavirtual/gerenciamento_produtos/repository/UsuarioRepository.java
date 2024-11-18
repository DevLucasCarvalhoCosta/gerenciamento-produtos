package com.lojavirtual.gerenciamento_produtos.repository;

import com.lojavirtual.gerenciamento_produtos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
