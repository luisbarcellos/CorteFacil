package br.com.cortefacil.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.hibernate.Session;

import br.com.cortefacil.modelo.Fornecedor;

@Stateless
public class FornecedorDAOImpl implements FornecedorDAO {
	private Session sessao;
	
	public FornecedorDAOImpl() {
		sessao = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void salvar(Fornecedor fornecedor) {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}

		sessao.beginTransaction();
		fornecedor.getEndereco().setFornecedor(fornecedor);
		sessao.save(fornecedor);
		sessao.flush();
		sessao.getTransaction().commit();
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void atualizar(Fornecedor fornecedor) {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		fornecedor.getEndereco().setFornecedor(fornecedor);
		sessao.flush();
		sessao.clear();
		sessao.update(fornecedor);
		sessao.getTransaction().commit();
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(Fornecedor fornecedor) throws Exception {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		sessao.delete(fornecedor);
		sessao.flush();
		sessao.getTransaction().commit();
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Fornecedor> listarTodos() {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		List<Fornecedor> lista = sessao.createCriteria(Fornecedor.class).list();

		sessao.getTransaction().commit();
		return lista;
	}
}
