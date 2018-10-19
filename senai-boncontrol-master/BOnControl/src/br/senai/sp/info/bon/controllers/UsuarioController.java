package br.senai.sp.info.bon.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import br.senai.sp.info.bon.core.SessionUtils;
import br.senai.sp.info.bon.dao.UsuarioDAO;
import br.senai.sp.info.bon.models.Usuario;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioDAO usuarioDao;

	@Autowired
	private SessionUtils sessionUtils;

	@Autowired
	private ServletContext context;

	@GetMapping(value = { "/", "" })
	public String abrirLogin(Model model) {

		if (!sessionUtils.isUsuarioLogado()) {
			model.addAttribute("usuario", new Usuario());
			return "index";
		} else {
			return "redirect:/app";
		}
	}

	@PostMapping(value = { "/app/adm/usuario/salvar" }, consumes = { "multipart/form-data" })
	public String salvar(@Valid Usuario usuario, BindingResult brUsuario,
			@RequestParam(name = "isAdministrador", required = false) Boolean administrador,
			@RequestPart(name = "foto", required = false) MultipartFile foto, HttpServletRequest request) {

		// Se for um cadastro, valida qualquer campo...
		if (usuario.getId() == null && brUsuario.hasFieldErrors()) {
			return "usuario/form";
		} else if (brUsuario.hasFieldErrors("nome") || brUsuario.hasFieldErrors("sobrenome")
				|| brUsuario.hasFieldErrors("email")) {
			return "usuario/form";
		}

		if (usuario.getId() == null) {
			usuarioDao.inserir(usuario);
		} else {
			Usuario usuarioBuscado = usuarioDao.buscar(usuario.getId());
			BeanUtils.copyProperties(usuario, usuarioBuscado, "id", "senha");
			System.out.println(usuarioBuscado.getSenha());
			System.out.println(usuarioBuscado.getId());
			usuarioDao.alterar(usuarioBuscado);
		}

		return "redirect:/app/adm/usuario";
	}

	@PostMapping({ "/usuario/autenticar" })
	public String autenticar(@Valid Usuario usuario, BindingResult brUsuario) {

		// Se houver erros no usuario, reabre o index
		if (brUsuario.hasFieldErrors("email") || brUsuario.hasFieldErrors("senha")) {
			return "index";
		}

		Usuario usuarioBuscado = usuarioDao.buscarPorEmailESenha(usuario.getLogin(), usuario.getSenha());

		if (usuarioBuscado == null) {
			brUsuario.addError(new FieldError("usuario", "email", "Email ou senha inválidos"));
			return "index";
		}

		// Aplica o usuário na sessão e abre a página inicial
		sessionUtils.setUsuarioLogado(usuarioBuscado);

		return "redirect:/app/";
	}

	@GetMapping({ "/sair" })
	public String logout() {
		sessionUtils.invalidarSessao();

		return "redirect:/";
	}

}
