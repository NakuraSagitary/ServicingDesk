package br.senai.sp.info.bon.dao.jpa;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.senai.sp.info.bon.dao.UsuarioDAO;
import br.senai.sp.info.bon.models.Usuario;

@Repository("usuarioDao")
public class UsuarioJPA extends AbstractJPA<Usuario> implements UsuarioDAO {

	@Override
	public Usuario buscarPorEmailESenha(String email, String senha) {
		List<Usuario> usuarios = buscarPorCampos(new HashMap<String, Object>() {
			{
				put("login", email);
				put("senha", senha);
			}
		});

		if (usuarios.isEmpty()) {
			return null;
		} else {
			return usuarios.get(0);
		}
	}

	@Override
	public String getEntityName() {
		return "Usuario";
	}

}
