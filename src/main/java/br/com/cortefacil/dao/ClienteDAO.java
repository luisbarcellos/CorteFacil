package br.com.cortefacil.dao;

import java.util.List;
import javax.ejb.Local;
import br.com.cortefacil.modelo.Cliente;

@Local
public interface ClienteDAO {
	public void salvar(Cliente cliente);

	public void atualizar(Cliente cliente);
	
	public void remover(Cliente cliente) throws Exception;

	public List<Cliente> listarTodos();
}