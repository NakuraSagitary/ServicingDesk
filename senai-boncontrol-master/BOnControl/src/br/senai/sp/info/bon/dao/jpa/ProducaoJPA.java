package br.senai.sp.info.bon.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.senai.sp.info.bon.dao.ProducaoDAO;
import br.senai.sp.info.bon.models.ItemProducao;

@Repository(value = "producaoDao")
public class ProducaoJPA extends AbstractJPA<ItemProducao> implements ProducaoDAO {

	@Override
	public String getEntityName() {
		return "ItensProducao";
	}

	@Override
	public List<ItemProducao> buscarItens() {

		String hql = "FROM ItemProducao i";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		return query.list();
	}
}
