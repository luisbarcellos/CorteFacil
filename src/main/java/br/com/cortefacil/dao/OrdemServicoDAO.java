package br.com.cortefacil.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.cortefacil.modelo.OrdemServico;

@Local
public interface OrdemServicoDAO {
	public void salvar(OrdemServico ordemServico);

	public void atualizar(OrdemServico ordemServico);
	
	public void remover(OrdemServico ordemServico) throws Exception;

	public List<OrdemServico> listarTodos();
}