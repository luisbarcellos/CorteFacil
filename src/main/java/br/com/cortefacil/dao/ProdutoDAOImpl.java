package br.com.cortefacil.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

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
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void salvar(Produto produto) {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		sessao.flush();
		sessao.clear();
		sessao.save(produto);
		sessao.getTransaction().commit();
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(Produto produto) throws Exception{
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		produto.getListaFornecedor().removeAll(produto.getListaFornecedor());
		sessao.flush();
		sessao.delete(produto);
		sessao.getTransaction().commit();
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Produto> listarTodos() {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		List<Produto> lista = sessao.createCriteria(Produto.class).list();

		sessao.getTransaction().commit();
		return lista;
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void atualizar(Produto produto) {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		sessao.flush();
		sessao.clear();
		sessao.update(produto);
		sessao.getTransaction().commit();
	}
}
