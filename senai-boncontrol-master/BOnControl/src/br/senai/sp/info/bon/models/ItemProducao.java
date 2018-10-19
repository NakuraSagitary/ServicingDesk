package br.senai.sp.info.bon.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "itens_producao")
public class ItemProducao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(nullable = false, name = "item_id")
	private Item item;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	private int itensAprovados;

	private int itensReprovados;

	// diferenca entre aprovados e reprovados
	private int itensProduzidos;

	private float prejuizoEstimado;

	private String emailGerente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getItensAprovados() {
		return itensAprovados;
	}

	public void setItensAprovados(int itensAprovados) {
		this.itensAprovados = itensAprovados;
	}

	public int getItensReprovados() {
		return itensReprovados;
	}

	public void setItensReprovados(int itensReprovados) {
		this.itensReprovados = itensReprovados;
	}

	public int getItensProduzidos() {
		return itensProduzidos;
	}

	public void setItensProduzidos(int itensProduzidos) {
		this.itensProduzidos = itensProduzidos;
	}

	public float getPrejuizoEstimado() {
		return prejuizoEstimado;
	}

	public void setPrejuizoEstimado(float prejuizoEstimado) {
		this.prejuizoEstimado = prejuizoEstimado;
	}

	public String getEmailGerente() {
		return emailGerente;
	}

	public void setEmailGerente(String emailGerente) {
		this.emailGerente = emailGerente;
	}

}
