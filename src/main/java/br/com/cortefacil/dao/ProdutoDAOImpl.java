package br.com.cortefacil.dao;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Session;

import com.fasterxml.jackson.databind.ser.impl.FailingSerializer;

import br.com.cortefacil.modelo.Fornecedor;
import br.com.cortefacil.modelo.Produto;

@Stateless
public class ProdutoDAOImpl implements ProdutoDAO{
	private Session sessao;

	public ProdutoDAOImpl() {
		sessao = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	public void salvar(Produto produto) {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		Fornecedor f = new Fornecedor();
		f.setNome("Teste");
		f.setTelefone("212121212");
		f.setEmail("teste@teste.com");
		f.getEndereco().setNumero(10);
		f.getEndereco().setBairro("teste");
		f.getEndereco().setCep("91540220");
		f.getEndereco().setCidade("Teste");
		f.getEndereco().setRua("Teste");
		//produto.getListaFornecedor().add(f);
		
		sessao.saveOrUpdate(produto);
		sessao.flush();
		sessao.getTransaction().commit();
	}
	
	public void remover(Produto produto) throws Exception{
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		sessao.delete(produto);
		sessao.flush();
		sessao.getTransaction().commit();
	}
	
	public List<Produto> listarTodos() {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		List<Produto> lista = sessao.createCriteria(Produto.class).list();

		sessao.getTransaction().commit();
		//sessao.close();
		return lista;
	}

	public void atualizar(Produto produto) {
		/*if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.clear();
		sessao.beginTransaction();
		fornecedor.getEndereco().setFornecedor(fornecedor);
		sessao.flush();
		sessao.clear();
		sessao.saveOrUpdate(fornecedor);
		sessao.flush();
		sessao.getTransaction().commit();*/	
	}
}
