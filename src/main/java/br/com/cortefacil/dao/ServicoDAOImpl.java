package br.com.cortefacil.dao;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Session;

import br.com.cortefacil.modelo.Servico;

@Stateless
public class ServicoDAOImpl implements ServicoDAO{
	private Session sessao;

	public ServicoDAOImpl() {
		sessao = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	public void salvar(Servico servico) {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		
		sessao.saveOrUpdate(servico);
		sessao.flush();
		sessao.getTransaction().commit();
	}
	
	public void remover(Servico servico) throws Exception{
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		servico.getListaProdutos().removeAll(servico.getListaProdutos());
		sessao.delete(servico);
		sessao.flush();
		sessao.getTransaction().commit();
	}
	
	public List<Servico> listarTodos() {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		List<Servico> lista = sessao.createCriteria(Servico.class).list();

		sessao.getTransaction().commit();
		//sessao.close();
		return lista;
	}
	public void atualizar(Servico servico) {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		sessao.flush();
		sessao.clear();
		sessao.update(servico);
		sessao.flush();
		sessao.getTransaction().commit();
	}
}
