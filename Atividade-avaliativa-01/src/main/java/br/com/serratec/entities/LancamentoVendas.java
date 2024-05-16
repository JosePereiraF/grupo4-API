package br.com.serratec.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class LancamentoVendas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoVenda;
	private LocalDate dataVenda;
	private Double valorVenda;
	//Relacionamento: Um vendedor possui um ou vários lançamento de vendas.
	@ManyToOne
	@JoinColumn(name = "id_vendedor")
	private Vendedor vendedor;
	
	
	
	public LancamentoVendas(Long codigoVenda, LocalDate dataVenda, Double valorVenda, Vendedor vendenddor) {
		super();
		this.codigoVenda = codigoVenda;
		this.dataVenda = dataVenda;
		this.valorVenda = valorVenda;
		this.vendedor = vendenddor;
	}
	public LancamentoVendas() {
		super();
	}
	
	public Long getCodigoVenda() {
		return codigoVenda;
	}
	public void setCodigoVenda(Long codigoVenda) {
		this.codigoVenda = codigoVenda;
	}
	public LocalDate getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}
	public Double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}
	public Vendedor getVendenddor() {
		return vendedor;
	}
	public void setVendenddor(Vendedor vendenddor) {
		this.vendedor = vendenddor;
	}
	
	
}
