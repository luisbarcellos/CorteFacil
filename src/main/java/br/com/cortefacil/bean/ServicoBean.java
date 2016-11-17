package br.com.cortefacil.bean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.cortefacil.ejb.FornecedorEJB;
import br.com.cortefacil.ejb.ProdutoEJB;
import br.com.cortefacil.ejb.ServicoEJB;
import br.com.cortefacil.modelo.Fornecedor;
import br.com.cortefacil.modelo.Produto;
import br.com.cortefacil.modelo.Servico;

@ManagedBean(name = "ServicoBean")
@SessionScoped
public class ServicoBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	public ServicoEJB auxServicoEJB;
	
	@EJB
	public ProdutoEJB auxProdutoEJB;
	
	private List<Servico> listaServico;
	
	private List<Produto> listaProduto;
	
	private Servico servico = new Servico();
	
	@PostConstruct
	public void init(){
		listaProduto = auxProdutoEJB.listarTodos();
	}
	
	public void editar(Servico servico) {
		this.setServico(servico);
	}
	
	public void editarProdutoServico(Servico servico){
		this.servico = servico;
		listaProduto = auxProdutoEJB.listarTodos();
		for(Produto produto: servico.getListaProdutos()){
			for(int i=0; i <= listaProduto.size() ; i++){
				if(listaProduto.get(i).getIdProduto() == produto.getIdProduto()){
					listaProduto.remove(i);
				}
			}
		}
	}
	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			auxServicoEJB.salvar(getServico());
			setServico(new Servico()); // Limpando o formulário.
			setListaProduto(auxProdutoEJB.listarTodos()); //Carregando a lista de produtos
			
			mensagem = context.getApplication().evaluateExpressionGet(context, "Serviço salvo com sucesso!",
					String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao salvar a serviço!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
	}
	
	public void atualizar() {
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			auxServicoEJB.atualizar(getServico());
			setServico(new Servico()); // Limpando o formulário.

			mensagem = context.getApplication().evaluateExpressionGet(context, "Servico atualizado com sucesso!",
					String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao atualizar servico!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
	}
	
	public void adicionarProdutoAoCadastrar(Produto produto){
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			getServico().getListaProdutos().add(produto);
			servico.adicionaValor(produto.getValor());
			getListaProduto().remove(produto);
			mensagem = context.getApplication().evaluateExpressionGet(context, "Produto adicionado com sucesso!",
					String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao adicionar produto!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
	}
	
	public void adicionarProdutoAoServico(Produto produto){
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			getServico().getListaProdutos().add(produto);
			getListaProduto().remove(produto);
			auxServicoEJB.atualizar(getServico());
			mensagem = context.getApplication().evaluateExpressionGet(context, "Servico adicionado com sucesso!",
					String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao adicionar servico!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
	}

	public void remover(Servico servico) {
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			auxServicoEJB.remover(servico);
			mensagem = context.getApplication().evaluateExpressionGet(context, "Servico excluído com sucesso!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao excluir o servico!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
	}
	
	public void removerProdutoDoServico(Produto produto){
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			this.getServico().getListaProdutos().remove(produto);
			getListaProduto().add(produto);
			auxServicoEJB.atualizar(getServico());
			mensagem = context.getApplication().evaluateExpressionGet(context, "Produto removido com sucesso!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao remover o produto!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public List<Produto> getListaProduto() {
		return listaProduto;
	}
	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public List<Servico> getListaServico() {
		return this.listaServico = auxServicoEJB.listarTodos();
	}
}