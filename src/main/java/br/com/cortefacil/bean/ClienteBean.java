package br.com.cortefacil.bean;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.cortefacil.ejb.ClienteEJB;
import br.com.cortefacil.modelo.Cliente;
import br.com.cortefacil.modelo.Endereco;

@ManagedBean(name = "ClienteBean")
@RequestScoped
public class ClienteBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	public ClienteEJB auxClienteEJB;

	private List<Cliente> listaCliente;
	
	private Cliente cliente = new Cliente();
	
	public Cliente getCliente() {
		return cliente;
	}

	public void Cliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void editar(Cliente cliente) {
		this.cliente = cliente;
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			cliente.getEndereco().setCliente(cliente);
			auxClienteEJB.salvar(cliente);
			cliente = new Cliente(); // Limpando o formulário.

			mensagem = context.getApplication().evaluateExpressionGet(context, "Cliente salvo com sucesso!",
					String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao salvar a cliente!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
	}
	
	public void atualizar() {
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			auxClienteEJB.atualizar(cliente);
			cliente = new Cliente(); // Limpando o formulário.

			mensagem = context.getApplication().evaluateExpressionGet(context, "Cliente atualizado com sucesso!",
					String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao atualizar cliente!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
	}
	

	public void remover(Cliente cliente) {
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			auxClienteEJB.remover(cliente);
			mensagem = context.getApplication().evaluateExpressionGet(context, "Cliente excluído com sucesso!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao excluir o cliente!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
		
	}

	public List<Cliente> getListaCliente() {
		return this.listaCliente = auxClienteEJB.listarTodos();
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}
}