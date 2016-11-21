package br.com.cortefacil.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cortefacil.bean.BaseBean;
import br.com.cortefacil.dao.OrdemServicoDAO;
import br.com.cortefacil.modelo.OrdemServico;

@Stateless
public class OrdemServicoEJBImpl extends BaseBean implements OrdemServicoEJB{
	@EJB
	public OrdemServicoDAO dao;
	
	@EJB
	public OrdemServicoEJB auxOrdemServicoEJB;
	
	public void salvar(OrdemServico ordemServico){
		dao.salvar(ordemServico);
	}
	
	public void atualizar(OrdemServico ordemServico){
		dao.atualizar(ordemServico);
	}

	public void remover(OrdemServico ordemServico) throws Exception{
		dao.remover(ordemServico);
	}

	public List<OrdemServico> listarTodos(){
		return dao.listarTodos();
	}
}