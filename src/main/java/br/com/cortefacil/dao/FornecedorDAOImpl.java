package br.com.cortefacil.dao;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Session;

import br.com.cortefacil.modelo.Fornecedor;

@Stateless
public class FornecedorDAOImpl implements FornecedorDAO {
	private Session sessao;

	public FornecedorDAOImpl(){
		sessao = HibernateUtil.getSessionFactory().getCurrentSession();
	}

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
	
	public void atualizar(Fornecedor fornecedor) {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.clear();
		sessao.beginTransaction();
		fornecedor.getEndereco().setFornecedor(fornecedor);
		sessao.flush();
		sessao.clear();
		sessao.saveOrUpdate(fornecedor);
		sessao.flush();
		sessao.getTransaction().commit();
	}
	
	public void remover(Fornecedor fornecedor) throws Exception{
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		sessao.delete(fornecedor);
		sessao.flush();
		sessao.getTransaction().commit();
	}
	
	public List<Fornecedor> listarTodos() {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		List<Fornecedor> lista = sessao.createCriteria(Fornecedor.class).list();

		sessao.getTransaction().commit();
		//sessao.close();
		return lista;
	}
}
