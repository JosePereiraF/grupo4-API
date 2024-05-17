package br.com.serratec.dtos;

import br.com.serratec.entities.Vendedor;

public class VendedorDTO {
//    private Long id;
    private String nome;
    private String email;
    private Double salario;
    private Double comissao;
    
    public VendedorDTO() {
    }
    
    public VendedorDTO(String nome, String email, Double salario, Double comissao) {
        this.nome = nome;
        this.email = email;
        this.salario = salario;
        this.comissao = comissao;
    }
    
    public VendedorDTO(Vendedor vendedor) {
        nome = getNome();
        email = getEmail();
        salario = getSalario();
        comissao = getComissao();
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
