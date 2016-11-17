package br.com.cortefacil.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="produto")
public class Produto {
	@Id
    @GeneratedValue
	private Integer idProduto;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "valor")
	private double valor;
	
	@Column(name = "quantidade")
	private Integer quantidade;
	
	@ManyToMany(cascade = CascadeType.ALL)
	 @JoinTable(name="produto_fornecedor",joinColumns={@JoinColumn(name="idproduto")}, 
     inverseJoinColumns={@JoinColumn(name="idfornecedor")})
	private List<Fornecedor> listaFornecedor = new ArrayList<Fornecedor>();
	
	@ManyToMany(mappedBy = "listaProdutos")
	private List<Servico> listaServico;
	
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public List<Fornecedor> getListaFornecedor() {
		return listaFornecedor;
	}
	public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
		this.listaFornecedor = listaFornecedor;
	}
	/*public List<Servico> getListaServico() {
		return listaServico;
	}
	public void setListaServico(List<Servico> listaServico) {
		this.listaServico = listaServico;
	}*/	
}
