package br.com.cortefacil.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.cortefacil.modelo.Servico;

public class ServicoDAO {
	private Session sessao;

	public ServicoDAO() {
		sessao = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	public void salvar(Servico servico) {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.clear();
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
}
