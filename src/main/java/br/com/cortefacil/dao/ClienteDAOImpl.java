package br.com.cortefacil.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.cortefacil.modelo.Cliente;

@Stateless
public class ClienteDAOImpl implements ClienteDAO {
	private Session sessao;

	public ClienteDAOImpl() {
		sessao = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	public void salvar(Cliente cliente) {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		cliente.getEndereco().setCliente(cliente);
		sessao.save(cliente);
		sessao.flush();
		sessao.getTransaction().commit();
	}
	
	public void atualizar(Cliente cliente) {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		cliente.getEndereco().setCliente(cliente);
		sessao.flush();
		sessao.clear();
		sessao.update(cliente);
		sessao.flush();
		sessao.getTransaction().commit();
	}
	
	public void remover(Cliente cliente) throws Exception{
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		sessao.delete(cliente);
		sessao.flush();
		sessao.getTransaction().commit();
	}
	
	public List<Cliente> listarTodos() {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		List<Cliente> lista = sessao.createCriteria(Cliente.class).list();

		sessao.getTransaction().commit();
		//sessao.close();
		return lista;
	}
}
