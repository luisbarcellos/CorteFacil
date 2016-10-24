package br.com.cortefacil.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cortefacil.bean.BaseBean;
import br.com.cortefacil.dao.ProdutoDAO;
import br.com.cortefacil.modelo.Produto;

@Stateless
public class ProdutoEJBImpl extends BaseBean implements ProdutoEJB {
	@EJB
	public ProdutoDAO dao;
	
	@EJB
	public ProdutoEJB auxProdutoEJB;
	
	public void salvar(Produto produto){
		dao.salvar(produto);
	}
	
	public void atualizar(Produto produto){
		dao.atualizar(produto);
	}

	public void remover(Produto produto) throws Exception{
		dao.remover(produto);
	}

	public List<Produto> listarTodos(){
		return dao.listarTodos();
	}
}
