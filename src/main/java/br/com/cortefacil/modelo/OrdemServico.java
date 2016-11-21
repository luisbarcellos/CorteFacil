package br.com.cortefacil.modelo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.exolab.castor.types.DateTime;

@Entity
@Table(name="ordemservico")
public class OrdemServico implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Integer idOrdemServico;
	
	@Column(name = "data")
	private Date data;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idcliente")
	private Cliente cliente = new Cliente();
	
	@Column(name = "valor")
	private double valor;
	
	@Column(name = "descricao")
	private String descricao;
	
	@ManyToMany
	 @JoinTable(name="itens_ordemservico",joinColumns={@JoinColumn(name="idordemservico")}, 
    inverseJoinColumns={@JoinColumn(name="idservico")})
	private List<Servico> listaServico = new ArrayList<Servico>();
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<Servico> getListaServico() {
		return listaServico;
	}
	public void setServicos(List<Servico> listaServico) {
		this.setListaServico(listaServico);
	}
	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public void setListaServico(List<Servico> listaServico) {
		this.listaServico = listaServico;
	}
	public Date getDataMinima(){
		return new Date();
	}
	public void adicionaValor(double valor){
		this.valor = this.valor + valor;
	}
}
