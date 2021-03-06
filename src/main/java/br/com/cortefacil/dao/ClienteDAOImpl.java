package br.com.cortefacil.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.hibernate.Session;

import br.com.cortefacil.modelo.Cliente;

@Stateless
public class ClienteDAOImpl implements ClienteDAO {
private Session sessao;
	//@PersistenceContext(unitName = "PostgreSQL")
	//private EntityManager em;
	    
	public ClienteDAOImpl() {
		sessao = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	public void salvar(Cliente cliente) {
		//em.merge(cliente);
		
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		cliente.getEndereco().setCliente(cliente);
		sessao.save(cliente);
		sessao.flush();
		sessao.getTransaction().commit();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
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
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(Cliente cliente) throws Exception{
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		sessao.delete(cliente);
		sessao.flush();
		sessao.getTransaction().commit();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
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
