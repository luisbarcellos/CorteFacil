package br.com.cortefacil.ejb;

import java.util.List;

import javax.ejb.Local;

import br.com.cortefacil.modelo.OrdemServico;

@Local
public interface OrdemServicoEJB {
	public void salvar(OrdemServico ordemServico);

	public void atualizar(OrdemServico ordemServico);

	public void remover(OrdemServico ordemServico) throws Exception;

	public List<OrdemServico> listarTodos();
}
