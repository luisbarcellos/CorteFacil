package br.com.cortefacil.ejb;

import java.util.List;

import javax.ejb.Local;

import br.com.cortefacil.modelo.Cliente;
import br.com.cortefacil.modelo.Servico;

@Local
public interface ServicoEJB {
	public void salvar(Servico servico);

	public void atualizar(Servico servico);

	public void remover(Servico servico) throws Exception;

	public List<Servico> listarTodos();
}
