package br.com.cortefacil.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.cortefacil.modelo.Servico;

@Local
public interface ServicoDAO {
	public void salvar(Servico servico);
	
	public void remover(Servico servico) throws Exception;
	
	public List<Servico> listarTodos();

	public void atualizar(Servico servico);
}
