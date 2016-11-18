package br.com.cortefacil.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.hibernate.Session;

import br.com.cortefacil.modelo.Servico;

@Stateless
public class ServicoDAOImpl implements ServicoDAO {
	private Session sessao;

	public ServicoDAOImpl() {
		sessao = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void salvar(Servico servico) {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		sessao.save(servico);
		sessao.getTransaction().commit();
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(Servico servico) throws Exception {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		servico.getListaProdutos().removeAll(servico.getListaProdutos());
		sessao.delete(servico);
		sessao.flush();
		sessao.clear();
		sessao.getTransaction().commit();
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Servico> listarTodos() {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		List<Servico> lista = sessao.createCriteria(Servico.class).list();

		sessao.getTransaction().commit();
		return lista;
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void atualizar(Servico servico) {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		sessao.flush();
		sessao.clear();
		sessao.update(servico);
		sessao.getTransaction().commit();
	}
}
