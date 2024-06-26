package br.com.serratec.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.serratec.annotation.SalarioMinimo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Vendedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@Email
	private String email;
	//@DecimalMin(value = 1320)
	@SalarioMinimo(valorSalario = 1320)
	private Double salario;
	@Positive
	private Double comissao;
	
	//Relacionamento: Um vendedor possui um ou vários lançamento de vendas.
	@JsonManagedReference
	@OneToMany(mappedBy = "vendedor")
	private List<LancamentoVendas> listaVendas;
	
	
	public Vendedor() {
		super();
	}
	public Vendedor(Long id, String nome, String email, Double salario, Double comissao) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.salario = salario;
		this.comissao = comissao;
	}
	
	
	public List<LancamentoVendas> getListaVendas() {
		return listaVendas;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public Double getComissao() {
		return comissao;
	}
	public void setComissao(Double comissao) {
		this.comissao = comissao;
	}
	
	
}
