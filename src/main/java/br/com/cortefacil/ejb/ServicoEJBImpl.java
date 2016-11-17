package br.com.cortefacil.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cortefacil.bean.BaseBean;
import br.com.cortefacil.dao.ClienteDAO;
import br.com.cortefacil.dao.ServicoDAO;
import br.com.cortefacil.modelo.Cliente;
import br.com.cortefacil.modelo.Servico;

@Stateless
public class ServicoEJBImpl extends BaseBean implements ServicoEJB {
	@EJB
	public ServicoDAO dao;
	
	@EJB
	public ServicoEJB auxServicoEJB;
	
	public void salvar(Servico servico){
		dao.salvar(servico);
	}
	
	public void atualizar(Servico servico){
		dao.atualizar(servico);
	}

	public void remover(Servico servico) throws Exception{
		dao.remover(servico);
	}

	public List<Servico> listarTodos(){
		return dao.listarTodos();
	}
}
