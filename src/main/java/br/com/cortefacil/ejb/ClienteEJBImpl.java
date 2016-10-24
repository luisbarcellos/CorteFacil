package br.com.cortefacil.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cortefacil.bean.BaseBean;
import br.com.cortefacil.dao.ClienteDAO;
import br.com.cortefacil.modelo.Cliente;

@Stateless
public class ClienteEJBImpl extends BaseBean implements ClienteEJB {
	@EJB
	public ClienteDAO dao;
	
	@EJB
	public ClienteEJB auxClienteEJB;
	
	public void salvar(Cliente cliente){
		dao.salvar(cliente);
	}
	
	public void atualizar(Cliente cliente){
		dao.atualizar(cliente);
	}

	public void remover(Cliente cliente) throws Exception{
		dao.remover(cliente);
	}

	public List<Cliente> listarTodos(){
		return dao.listarTodos();
	}
}
