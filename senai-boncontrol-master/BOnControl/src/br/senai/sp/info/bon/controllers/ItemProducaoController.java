package br.senai.sp.info.bon.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.senai.sp.info.bon.dao.ItemDAO;
import br.senai.sp.info.bon.dao.ProducaoDAO;
import br.senai.sp.info.bon.models.ItemProducao;

@Controller
@RequestMapping("/app")
public class ItemProducaoController {

	@Autowired
	private ItemDAO itemDao;

	@Autowired
	private ProducaoDAO itemProducaoDao;

	@GetMapping({ "", "/" })
	public String abrirListaOcorrencia(Model model) {

		model.addAttribute("itens", itemDao.buscarTodos());
		model.addAttribute("itemProducao", new ItemProducao());

		return "producao/form";
	}

	@PostMapping("/producao/salvar")
	public String salvar(@Valid ItemProducao item, BindingResult brItemProducao, Model model) {
		// Checa os erros de ocorrência

		if (item.getId() == null) {

			item.setPrejuizoEstimado(itemDao.buscar(item.getItem().getId()).getPrejuizoEstimadoPorPeca() * item.getItensReprovados());
			item.setItensProduzidos(item.getItensAprovados() - item.getItensReprovados());
			itemProducaoDao.inserir(item);
		}

		return "redirect:/app";
	}

}
