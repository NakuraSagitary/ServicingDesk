package br.senai.sp.info.not.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.senai.sp.info.not.dao.ItemDAO;
import br.senai.sp.info.not.dao.ProducaoDAO;
import br.senai.sp.info.not.models.Producao;

@Controller
@RequestMapping("/app")
public class ProducaoController {

	@Autowired
	private ItemDAO itemDao;

	@Autowired
	private ProducaoDAO producaoDao;

	@GetMapping({ "", "/" })
	public String abrirListaOcorrencia(Model model) {

		model.addAttribute("itens", itemDao.buscarTodos());
		model.addAttribute("itemProducao", new Producao());

		return "producao/form";
	}

	@PostMapping("/producao/salvar")
	public String salvar(@Valid Producao item, BindingResult brItemProducao, Model model) {

		if (item.getId() == null) {

			item.setPrejuizoEstimado(itemDao.buscar(item.getItem().getId()).getPrejuizoEstimadoPorPeca() * item.getItensReprovados());
			item.setItensProduzidos(item.getItensAprovados() - item.getItensReprovados());
			producaoDao.inserir(item);
		}

		return "redirect:/app";
	}

}
