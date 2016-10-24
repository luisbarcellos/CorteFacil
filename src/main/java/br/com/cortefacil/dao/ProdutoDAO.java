package br.com.cortefacil.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.cortefacil.modelo.Produto;

@Local
public interface ProdutoDAO {
	public void salvar(Produto produto);

	public void atualizar(Produto produto);
	
	public void remover(Produto produto) throws Exception;

	public List<Produto> listarTodos();
}