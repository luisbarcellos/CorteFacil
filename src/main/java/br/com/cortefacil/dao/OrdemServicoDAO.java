package br.com.cortefacil.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.cortefacil.modelo.OrdemServico;

public class OrdemServicoDAO {
	private Session sessao;

	public OrdemServicoDAO() {
		sessao = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	public void salvar(OrdemServico ordemServico) {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.clear();
		sessao.beginTransaction();
		
		sessao.saveOrUpdate(ordemServico);
		sessao.flush();
		sessao.getTransaction().commit();
	}
	
	public void remover(OrdemServico ordemServico) throws Exception{
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		sessao.delete(ordemServico);
		sessao.flush();
		sessao.getTransaction().commit();
	}
	
	public List<OrdemServico> listarTodos() {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		List<OrdemServico> lista = sessao.createCriteria(OrdemServico.class).list();

		sessao.getTransaction().commit();
		//sessao.close();
		return lista;
	}
}
