package br.senai.sp.info.bon.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.bon.models.ItemProducao;

public interface ProducaoDAO extends DAO<ItemProducao> {
	@Transactional
	public List<ItemProducao> buscarItens();
}
