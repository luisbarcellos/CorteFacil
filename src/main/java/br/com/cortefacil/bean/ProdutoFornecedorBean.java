package br.com.cortefacil.bean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.cortefacil.ejb.FornecedorEJB;
import br.com.cortefacil.ejb.ProdutoEJB;
import br.com.cortefacil.modelo.Fornecedor;
import br.com.cortefacil.modelo.Produto;

@ManagedBean(name = "ProdutoFornecedorBean")
@SessionScoped
public class ProdutoFornecedorBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	public ProdutoEJB auxProdutoEJB;
	
	@EJB
	public FornecedorEJB auxFornecedorEJB;
	
	private List<Produto> listaProduto;
	
	private List<Fornecedor> listaFornecedor;
	
	private Produto produto = new Produto();
	
	@PostConstruct
	public void init(){
		listaFornecedor = auxFornecedorEJB.listarTodos();
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void Produto(Produto produto) {
		this.produto = produto;
	}
	
	public void editarFornecedorProduto(Produto produto){
		this.produto = produto;
		setListaFornecedor(auxFornecedorEJB.listarTodos());
		for(Fornecedor fornecedor : produto.getListaFornecedor()){
			for(int i=0; i < getListaFornecedor().size() ; i++){
				if(getListaFornecedor().get(i).getIdFornecedor() == fornecedor.getIdFornecedor()){
					getListaFornecedor().remove(i);
				}
			}
		}
	}
	
	public void adicionarFornecedorAoProduto(Fornecedor fornecedor){
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			getProduto().getListaFornecedor().add(fornecedor);
			getListaFornecedor().remove(fornecedor);
			auxProdutoEJB.atualizar(produto);
			mensagem = context.getApplication().evaluateExpressionGet(context, "Fornecedor adicionado com sucesso!",
					String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao adicionar fornecedor!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
	}
	
	public void removerFornecedorDoProduto(Fornecedor fornecedor){
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			this.produto.getListaFornecedor().remove(fornecedor);
			getListaFornecedor().add(fornecedor);
			auxProdutoEJB.atualizar(produto);
			mensagem = context.getApplication().evaluateExpressionGet(context, "Produto removido com sucesso!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao remover o produto!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
	}

	public List<Produto> getListaProduto() {
		return this.listaProduto = auxProdutoEJB.listarTodos();
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}
	
	public List<Fornecedor> getListaFornecedor() {
		return this.listaFornecedor;
	}
	public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
		this.listaFornecedor = listaFornecedor;
	}	
}