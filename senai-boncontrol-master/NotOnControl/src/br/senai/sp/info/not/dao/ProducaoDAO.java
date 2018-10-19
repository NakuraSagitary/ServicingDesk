package br.senai.sp.info.not.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.not.models.Producao;

public interface ProducaoDAO extends DAO<Producao> {
	@Transactional
	public List<Producao> buscarItens();
}
