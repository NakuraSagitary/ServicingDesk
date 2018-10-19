package br.senai.sp.info.not.dao.jpa;

import org.springframework.stereotype.Repository;

import br.senai.sp.info.not.dao.ItemDAO;
import br.senai.sp.info.not.models.Item;

@Repository("itemDao")
public class ItemJPA extends AbstractJPA<Item> implements ItemDAO {
	
	@Override
	public String getEntityName() {
		return "Item";
	}
	
}
