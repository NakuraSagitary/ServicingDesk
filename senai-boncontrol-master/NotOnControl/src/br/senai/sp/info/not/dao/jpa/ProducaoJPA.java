package br.senai.sp.info.not.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.senai.sp.info.not.dao.ProducaoDAO;
import br.senai.sp.info.not.models.Producao;

@Repository(value = "producaoDao")
public class ProducaoJPA extends AbstractJPA<Producao> implements ProducaoDAO {

	@Override
	public String getEntityName() {
		return "Producao";
	}

	@Override
	public List<Producao> buscarItens() {

		String hql = "FROM Producao i";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		return query.list();
	}
}
