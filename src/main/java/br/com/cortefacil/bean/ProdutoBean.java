package br.com.cortefacil.bean;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.cortefacil.ejb.ProdutoEJB;
import br.com.cortefacil.ejb.ProdutoEJB;
import br.com.cortefacil.modelo.Produto;
import br.com.cortefacil.modelo.Endereco;
import br.com.cortefacil.modelo.Produto;

@ManagedBean(name = "ProdutoBean")
@RequestScoped
public class ProdutoBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	public ProdutoEJB auxProdutoEJB;

	private List<Produto> listaProduto;
	
	private Produto produto = new Produto();
	
	public Produto getProduto() {
		return produto;
	}

	public void Produto(Produto produto) {
		this.produto = produto;
	}
	
	public void editar(Produto produto) {
		this.produto = produto;
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			auxProdutoEJB.salvar(produto);
			produto = new Produto(); // Limpando o formulário.

			mensagem = context.getApplication().evaluateExpressionGet(context, "Produto salvo com sucesso!",
					String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao salvar a produto!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
	}
	
	public void atualizar() {
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			auxProdutoEJB.atualizar(produto);
			produto = new Produto(); // Limpando o formulário.

			mensagem = context.getApplication().evaluateExpressionGet(context, "Produto atualizado com sucesso!",
					String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao atualizar produto!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
	}
	

	public void remover(Produto produto) {
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			auxProdutoEJB.remover(produto);
			mensagem = context.getApplication().evaluateExpressionGet(context, "Produto excluído com sucesso!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao excluir o produto!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
		
	}

	public List<Produto> getListaProduto() {
		return this.listaProduto = auxProdutoEJB.listarTodos();
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}
}