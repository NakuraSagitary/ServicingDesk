package br.senai.sp.info.bon.dao.jpa;

import org.springframework.stereotype.Repository;

import br.senai.sp.info.bon.dao.ItemDAO;
import br.senai.sp.info.bon.models.Item;

@Repository("itemDao")
public class ItemJPA extends AbstractJPA<Item> implements ItemDAO {
	@Override
	public String getEntityName() {
		return "Item";
	}
}
