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
import br.com.cortefacil.ejb.ServicoEJB;
import br.com.cortefacil.modelo.Fornecedor;
import br.com.cortefacil.modelo.Produto;
import br.com.cortefacil.modelo.Servico;

@ManagedBean(name = "ProdutoBean")
@ViewScoped
public class ProdutoBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	public ProdutoEJB auxProdutoEJB;
	
	@EJB
	public FornecedorEJB auxFornecedorEJB;
	
	@EJB
	public ServicoEJB auxServicoEJB;
	
	private List<Produto> listaProduto;
	
	private List<Fornecedor> listaFornecedor;
	
	private List<Servico> listaServico;
	
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
	
	public void editar(Produto produto) {
		this.produto = produto;
	}
	
	public void editarFornecedorProduto(Produto produto){
		this.produto = produto;
		setListaFornecedor(auxFornecedorEJB.listarTodos());
		for(Fornecedor fornecedor : produto.getListaFornecedor()){
			for(int i=0; i <= getListaFornecedor().size() ; i++){
				if(getListaFornecedor().get(i).getIdFornecedor() == fornecedor.getIdFornecedor()){
					getListaFornecedor().remove(i);
				}
			}
		}
	}
	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			auxProdutoEJB.salvar(produto);
			produto = new Produto(); // Limpando o formulário.
			setListaFornecedor(auxFornecedorEJB.listarTodos()); //Carregando a lista de fornecedores
			
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
	
	public void adicionarFornecedorAoCadastrar(Fornecedor fornecedor){
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			produto.getListaFornecedor().add(fornecedor);
			getListaFornecedor().remove(fornecedor);
			mensagem = context.getApplication().evaluateExpressionGet(context, "Fornecedor adicionado com sucesso!",
					String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao adicionar fornecedor!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
	}
	
	public void adicionarFornecedorAoProduto(Fornecedor fornecedor){
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			produto.getListaFornecedor().add(fornecedor);
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

	public void remover(Produto produto) {
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			setListaServico(getListaServico());
			for(Servico servico: getListaServico()){
				for(int i=0; i < servico.getListaProdutos().size() ; i++){
					if(!servico.getListaProdutos().isEmpty()){
						if(servico.getListaProdutos().get(i).getIdProduto() == produto.getIdProduto()){
							servico.getListaProdutos().remove(i);
							auxServicoEJB.atualizar(servico);
						}
					}
				}
			}
			auxProdutoEJB.remover(produto);
			mensagem = context.getApplication().evaluateExpressionGet(context, "Produto excluído com sucesso!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao excluir o produto!", String.class);
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

	public List<Servico> getListaServico() {
		return this.listaServico = auxServicoEJB.listarTodos();
	}

	public void setListaServico(List<Servico> listaServico) {
		this.listaServico = listaServico;
	}	
}