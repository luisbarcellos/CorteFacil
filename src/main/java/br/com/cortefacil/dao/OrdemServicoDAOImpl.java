package br.com.cortefacil.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.hibernate.Session;

import br.com.cortefacil.modelo.Cliente;
import br.com.cortefacil.modelo.OrdemServico;

@Stateless
public class OrdemServicoDAOImpl implements OrdemServicoDAO{
	private Session sessao;

	public OrdemServicoDAOImpl() {
		sessao = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void salvar(OrdemServico ordemServico) {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		sessao.save(ordemServico);
		sessao.flush();
		sessao.getTransaction().commit();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(OrdemServico ordemServico) throws Exception{
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		ordemServico.getListaServico().removeAll(ordemServico.getListaServico());
		ordemServico.setCliente(new Cliente());
		sessao.delete(ordemServico);
		sessao.flush();
		sessao.getTransaction().commit();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
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

	public void atualizar(OrdemServico ordemServico) {
		if (!sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		sessao.beginTransaction();
		sessao.flush();
		sessao.clear();
		sessao.update(ordemServico);
		sessao.flush();
		sessao.getTransaction().commit();
	}
}
