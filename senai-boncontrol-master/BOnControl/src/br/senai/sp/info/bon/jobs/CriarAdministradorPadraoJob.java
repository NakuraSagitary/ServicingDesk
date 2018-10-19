package br.senai.sp.info.bon.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.senai.sp.info.bon.dao.UsuarioDAO;
import br.senai.sp.info.bon.models.Usuario;

@Component
public class CriarAdministradorPadraoJob implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshed) {
		Usuario adm = new Usuario();
		adm.setId(1L);
		adm.setLogin("admin@email.com");
		adm.setSenha("admin");
		
		if(usuarioDao.buscarPorEmailESenha(adm.getLogin(), adm.getSenha()) == null) {
			System.out.println(adm.getSenha());
			usuarioDao.inserir(adm);
		}
	}

}
