package br.com.cortefacil.ejb;

import java.util.List;

import javax.ejb.Local;

import br.com.cortefacil.modelo.Cliente;

@Local
public interface ClienteEJB {
	public void salvar(Cliente cliente);

	public void atualizar(Cliente cliente);

	public void remover(Cliente cliente) throws Exception;

	public List<Cliente> listarTodos();
}
