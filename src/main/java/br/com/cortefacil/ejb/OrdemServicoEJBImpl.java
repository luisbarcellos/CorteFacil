package br.com.cortefacil.ejb;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cortefacil.bean.BaseBean;
import br.com.cortefacil.dao.OrdemServicoDAO;
import br.com.cortefacil.modelo.OrdemServico;
import net.sf.jasperreports.engine.JRException;

@Stateless
public class OrdemServicoEJBImpl extends BaseBean implements OrdemServicoEJB {
	@EJB
	public OrdemServicoDAO dao;

	@EJB
	public OrdemServicoEJB auxOrdemServicoEJB;

	public void salvar(OrdemServico ordemServico) {
		dao.salvar(ordemServico);
	}

	public void atualizar(OrdemServico ordemServico) {
		dao.atualizar(ordemServico);
	}

	public void remover(OrdemServico ordemServico) throws Exception {
		dao.remover(ordemServico);
	}

	public List<OrdemServico> listarTodos() {
		return dao.listarTodos();
	}

	public void gerarRelatorioTodasOrdens() throws JRException, IOException {
		imprimirJavaIO(auxOrdemServicoEJB.listarTodos());
	}

	public void imprimirJavaIO(List<OrdemServico> lista) throws JRException, IOException {
		FileWriter arq;

			FileInputStream fis = (FileInputStream) getClass().getResourceAsStream("/caminho.properties");
			Properties props = new Properties();

			props.load(fis);
			fis.close();
			System.out.println("CAMINHO: " + props.getProperty("caminho"));
			arq = new FileWriter(props.getProperty("caminho") + "Relatorio_Ordem_Servicos.csv");
			PrintWriter gravarArq = new PrintWriter(arq);
			
			//Montando o cabeçalho
			gravarArq.printf("Id;");
			gravarArq.printf("Data;");
			gravarArq.printf("Valor;");
			gravarArq.printf("Descrição;");

			gravarArq.printf("\n");
			for (OrdemServico ordemServico : lista) {
				if (ordemServico.getIdOrdemServico() != null) {
					gravarArq.printf(ordemServico.getIdOrdemServico() + ";");
					gravarArq.printf(ordemServico.getData() + ";");
					gravarArq.printf(ordemServico.getValor() + ";");
					gravarArq.printf(ordemServico.getDescricao() + ";");

					gravarArq.printf("\n");
				}
			}
			arq.close();
	}
}