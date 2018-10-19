package br.senai.sp.info.not.dao;

import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.not.models.Usuario;

public interface UsuarioDAO extends DAO<Usuario> {

	@Transactional
	public Usuario buscarPorEmailESenha(String email, String senha);

	@Transactional
	public Usuario buscarPorEmail(String email);

}
