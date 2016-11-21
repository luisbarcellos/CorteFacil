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

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="servico")
public class Servico {
	@Id
    @GeneratedValue
	private Integer idServico;
	
	@ManyToMany
	 @JoinTable(name="servico_produto",joinColumns={@JoinColumn(name="idservico")}, 
    inverseJoinColumns={@JoinColumn(name="idproduto")})
	private List<Produto> listaProdutos = new ArrayList<Produto>();
	
	@ManyToMany(mappedBy = "listaServico")
	private List<OrdemServico> listaOrdemServico;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "valor")
	private double valor;
	
	public Integer getIdServico() {
		return idServico;
	}
	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
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
	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}
	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	
	public void adicionaValor(double valor){
		this.valor = this.valor + valor;
	}
	public List<OrdemServico> getListaOrdemServico() {
		return listaOrdemServico;
	}
	public void setListaOrdemServico(List<OrdemServico> listaOrdemServico) {
		this.listaOrdemServico = listaOrdemServico;
	}
}
