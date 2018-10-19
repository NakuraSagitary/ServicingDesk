package br.senai.sp.info.not.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.senai.sp.info.not.core.SessionUtils;
import br.senai.sp.info.not.dao.UsuarioDAO;
import br.senai.sp.info.not.models.Usuario;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioDAO usuarioDao;

	@Autowired
	private SessionUtils sessionUtils;

	@GetMapping(value = { "/", "" })
	public String abrirLogin(Model model) {

		model.addAttribute("usuario", new Usuario());
		return "index";

	}

	@PostMapping({ "/usuario/autenticar" })
	public String autenticar(@Valid Usuario usuario, BindingResult brUsuario, RedirectAttributes ra, Model model) {

		// busco pelo e-mail, caso exista verifico e-mail e senha
		Usuario usuarioPorEmail = usuarioDao.buscarPorEmail(usuario.getLogin());
		if (usuarioPorEmail != null) {
			Usuario usuarioBuscado = usuarioDao.buscarPorEmailESenha(usuario.getLogin(), usuario.getSenha());
			if (usuarioBuscado == null) {
				brUsuario.addError(new FieldError("usuario", "login", "Email inválido"));
				return "index";
			}
		}

		else {
//			usuarioDao.inserir(usuario);
			model.addAttribute("modal", "Deseja realmente criar um novo usuário");
			return "index";
		}

		// Aplica o usuário na sessão e abre a página inicial
		sessionUtils.setUsuarioLogado(usuario);

		return "redirect:/app/";

	}

	@PostMapping("/usuario/salvar")
	public String salvar(@Valid Usuario usuario, BindingResult brUsuario, RedirectAttributes ra, Model model) {
		
		System.out.println("entrou aqui");
		
		usuarioDao.inserir(usuario);
		// Aplica o usuário na sessão e abre a página inicial
		sessionUtils.setUsuarioLogado(usuario);

		return "redirect:/app/";

	}

	@GetMapping({ "/sair" })
	public String logout() {
		sessionUtils.invalidarSessao();

		return "redirect:/";
	}

}