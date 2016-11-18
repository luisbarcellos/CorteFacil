package br.com.cortefacil.bean;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.cortefacil.ejb.ClienteEJB;
import br.com.cortefacil.ejb.FornecedorEJB;
import br.com.cortefacil.ejb.ProdutoEJB;
import br.com.cortefacil.modelo.Cliente;
import br.com.cortefacil.modelo.Endereco;
import br.com.cortefacil.modelo.Fornecedor;
import br.com.cortefacil.modelo.Produto;
import br.com.cortefacil.modelo.Servico;

@ManagedBean(name = "FornecedorBean")
@RequestScoped
public class FornecedorBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	public FornecedorEJB auxFornecedorEJB;
	
	@EJB
	public ProdutoEJB auxProdutoEJB;
	
	private List<Fornecedor> listaFornecedor;
	
	private List<Produto> listaProduto;
	
	private Fornecedor fornecedor = new Fornecedor();
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void Fornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public void editar(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			fornecedor.getEndereco().setFornecedor(fornecedor);
			auxFornecedorEJB.salvar(fornecedor);
			fornecedor = new Fornecedor(); // Limpando o formulário.

			mensagem = context.getApplication().evaluateExpressionGet(context, "Fornecedor salvo com sucesso!",
					String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao salvar a fornecedor!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
	}
	
	public void atualizar() {
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			auxFornecedorEJB.atualizar(fornecedor);
			fornecedor = new Fornecedor(); // Limpando o formulário.

			mensagem = context.getApplication().evaluateExpressionGet(context, "Fornecedor atualizado com sucesso!",
					String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao atualizar fornecedor!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
	}
	

	public void remover(Fornecedor fornecedor) {
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			setListaProduto(getListaProduto());
			for(Produto produto: getListaProduto()){
				for(int i=0; i < produto.getListaFornecedor().size() ; i++){
					if(!produto.getListaFornecedor().isEmpty()){
						if(produto.getListaFornecedor().get(i).getIdFornecedor() == fornecedor.getIdFornecedor()){
							produto.getListaFornecedor().remove(i);
							auxProdutoEJB.atualizar(produto);
						}
					}
				}
			}
			auxFornecedorEJB.remover(fornecedor);
			mensagem = context.getApplication().evaluateExpressionGet(context, "Fornecedor excluído com sucesso!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao excluir o fornecedor!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}	
	}

	public List<Fornecedor> getListaFornecedor() {
		return this.listaFornecedor = auxFornecedorEJB.listarTodos();
	}

	public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
		this.listaFornecedor = listaFornecedor;
	}

	public List<Produto> getListaProduto() {
		return this.listaProduto = auxProdutoEJB.listarTodos();
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}
}