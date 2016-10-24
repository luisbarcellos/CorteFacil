package br.com.cortefacil.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cortefacil.bean.BaseBean;
import br.com.cortefacil.dao.ClienteDAO;
import br.com.cortefacil.dao.FornecedorDAO;
import br.com.cortefacil.modelo.Cliente;
import br.com.cortefacil.modelo.Fornecedor;

@Stateless
public class FornecedorEJBImpl extends BaseBean implements FornecedorEJB {
	@EJB
	public FornecedorDAO dao;
	
	@EJB
	public FornecedorEJB auxFornecedorEJB;
	
	public void salvar(Fornecedor fornecedor){
		dao.salvar(fornecedor);
	}
	
	public void atualizar(Fornecedor fornecedor){
		dao.atualizar(fornecedor);
	}

	public void remover(Fornecedor fornecedor) throws Exception{
		dao.remover(fornecedor);
	}

	public List<Fornecedor> listarTodos(){
		return dao.listarTodos();
	}
}
