package br.senai.sp.info.not.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "itens")
public class Item {

	// Codigo;Nome_Peca;Prejuizo_Estimado_Por_Peca

	@Id
	@Column(name = "Codigo")
	private Long id;

	@Column(name = "Nome_Peca")
	private String nomePeca;

	@Column(name = "Prejuizo_Estimado_Por_Peca")
	private float prejuizoEstimadoPorPeca;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomePeca() {
		return nomePeca;
	}

	public void setNomePeca(String nomePeca) {
		this.nomePeca = nomePeca;
	}

	public float getPrejuizoEstimadoPorPeca() {
		return prejuizoEstimadoPorPeca;
	}

	public void setPrejuizoEstimadoPorPeca(float prejuizoEstimadoPorPeca) {
		this.prejuizoEstimadoPorPeca = prejuizoEstimadoPorPeca;
	}

}
