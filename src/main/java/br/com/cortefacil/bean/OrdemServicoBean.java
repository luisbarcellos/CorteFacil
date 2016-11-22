package br.com.cortefacil.bean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.cortefacil.ejb.ClienteEJB;
import br.com.cortefacil.ejb.OrdemServicoEJB;
import br.com.cortefacil.ejb.ServicoEJB;
import br.com.cortefacil.modelo.Cliente;
import br.com.cortefacil.modelo.Endereco;
import br.com.cortefacil.modelo.Fornecedor;
import br.com.cortefacil.modelo.OrdemServico;
import br.com.cortefacil.modelo.Servico;

@ManagedBean(name = "OrdemServicoBean")
@ViewScoped
public class OrdemServicoBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	public OrdemServicoEJB auxOrdemServicoEJB;
	
	@EJB
	private ClienteEJB auxClienteEJB;
	
	@EJB
	private ServicoEJB auxServicoEJB;
	
	private Cliente cliente = new Cliente();
	
	private Servico servico = new Servico();
	
	private List<Cliente> listaCliente;
	
	private List<OrdemServico> listaOrdemServico;
	
	private OrdemServico ordemServico = new OrdemServico();
	
	private List<Servico> listaServico;
	
	@PostConstruct
	public void init(){
		listaCliente = auxClienteEJB.listarTodos();
		listaServico = auxServicoEJB.listarTodos();
	}
	
	public void consultaServicos(OrdemServico ordemServico){
		this.ordemServico = ordemServico;
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			getOrdemServico().setCliente(getCliente());
			if(getCliente().getIdCliente() == null){
				mensagem = context.getApplication().evaluateExpressionGet(context, "Adicione um cliente!",
						String.class);
				enviarMensagem(FacesMessage.SEVERITY_WARN, mensagem);
			} else if(getOrdemServico().getListaServico().isEmpty()){
				mensagem = context.getApplication().evaluateExpressionGet(context, "Adicione no mínimo um serviço!",
						String.class);
				enviarMensagem(FacesMessage.SEVERITY_WARN, mensagem);
			}else{
				auxOrdemServicoEJB.salvar(getOrdemServico());
				setOrdemServico(new OrdemServico()); // Limpando o formulário.
				setListaServico(auxServicoEJB.listarTodos());
				mensagem = context.getApplication().evaluateExpressionGet(context, "Ordem de serviço salva com sucesso!",
						String.class);
				enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);				
			}
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao salvar a ordem de serviço!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
	}
	
	public void adicionarClienteAoCadastrar(Cliente cliente){
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			if(this.cliente.getIdCliente() == null){
				setCliente(cliente);
				mensagem = context.getApplication().evaluateExpressionGet(context, "Cliente adicionado com sucesso!",
						String.class);
			}else{
				setCliente(cliente);
				mensagem = context.getApplication().evaluateExpressionGet(context, "Cliente substituído com sucesso!",
						String.class);
			}
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao adicionar fornecedor!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
	}
	
	public void adicionarServicoAoCadastrar(Servico servico){
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {			
			getOrdemServico().getListaServico().add(servico);
			getOrdemServico().adicionaValor(servico.getValor());
			getListaServico().remove(servico);
			mensagem = context.getApplication().evaluateExpressionGet(context, "Serviço adicionado com sucesso!",
					String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao adicionar serviço!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
	}
	
	public void gerarRelatorioTodasOrdens(){
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {			
			auxOrdemServicoEJB.gerarRelatorioTodasOrdens();
			mensagem = context.getApplication().evaluateExpressionGet(context, "Relatório gerado com sucesso!",
					String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao gerar relatório!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
	}
	/*public void atualizar() {
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
	}*/
	

	public void remover(OrdemServico ordemServico) {
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			auxOrdemServicoEJB.remover(ordemServico);
			mensagem = context.getApplication().evaluateExpressionGet(context, "Ordem de serviço excluída com sucesso!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
		} catch (Exception e) {
			mensagem = context.getApplication().evaluateExpressionGet(context, "Erro ao excluir a ordem de serviço!", String.class);
			enviarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
		}
		
	}

	public List<OrdemServico> getListaOrdemServico() {
		return this.listaOrdemServico= auxOrdemServicoEJB.listarTodos();
	}

	public void setListaOrdemServico(List<OrdemServico> listaOrdemServico) {
		this.listaOrdemServico = listaOrdemServico;
	}

	public ClienteEJB getAuxClienteEJB() {
		return auxClienteEJB;
	}

	public void setAuxClienteEJB(ClienteEJB auxClienteEJB) {
		this.auxClienteEJB = auxClienteEJB;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Servico> getListaServico() {
		return listaServico;
	}

	public void setListaServico(List<Servico> listaServico) {
		this.listaServico = listaServico;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public List<Cliente> getListaCliente() {
		return this.listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public ServicoEJB getAuxServicoEJB() {
		return auxServicoEJB;
	}

	public void setAuxServicoEJB(ServicoEJB auxServicoEJB) {
		this.auxServicoEJB = auxServicoEJB;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
}