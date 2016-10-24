package br.com.cortefacil.dao;

import java.util.List;
import javax.ejb.Local;
import br.com.cortefacil.modelo.Fornecedor;

@Local
public interface FornecedorDAO {
	public void salvar(Fornecedor fornecedor);

	public void atualizar(Fornecedor fornecedor);
	
	public void remover(Fornecedor fornecedor) throws Exception;

	public List<Fornecedor> listarTodos();
}