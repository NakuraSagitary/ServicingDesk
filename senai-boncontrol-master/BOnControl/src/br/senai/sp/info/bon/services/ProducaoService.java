package br.senai.sp.info.bon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import br.senai.sp.info.bon.dao.ProducaoDAO;
import br.senai.sp.info.bon.exceptions.ValidacaoException;
import br.senai.sp.info.bon.models.ItemProducao;

@Service
public class ProducaoService {

	@Autowired
	private ProducaoDAO itemProducaoDao;

	public ItemProducao cadastrar(ItemProducao itemProducao, BindingResult bindingResult) throws ValidacaoException {

		// Verifica os possíveis erros nos campos da ocorrencia de ocorrência
		if (bindingResult.hasErrors()) {
			throw new ValidacaoException();
		}

		// Realiza o cadastro
		itemProducaoDao.inserir(itemProducao);

		return itemProducao;
	}

}
