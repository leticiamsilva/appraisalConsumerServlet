package gerenciador;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import appraisal.Appraisal;
import appraisal.proccess.strategies.Strategy;
import appraisal.proccess.strategies.impl.RegressionStrategy;
import appraisal.proccess.strategies.impl.SelectionStrategy;
import appraisal.writer.ResultWriter;
import entities.dataset.Dataset;
import entities.dataset.Tuple;
import entities.results.RegressionResult;

public class ManipulacaoAppraisal {

	// NOVO
	private String caminhoResult;
	public String getBaseSuja() {
		return caminhoResult;
	}

	private ArrayList<Strategy> strategies;

	ManipulacaoAppraisal(String caminhoBaseSuja) {
		this.caminhoResult = caminhoBaseSuja;
		this.strategies = new ArrayList<Strategy>();
	}
	
	public void criarEstrategias(String paEstrategias) 
	{
		String []vaEstrategias = paEstrategias.split(";");
		
		for (String e : vaEstrategias) 
		{
			e = e.toLowerCase();
		}
				
		popularStrategies(vaEstrategias);		
	}
 
	public void popularStrategies(String[] estrategias) {

		System.out.println("popularestrategia");
		for (String e : estrategias) 
		{			
			if (e.equals("regression")) 
				this.strategies.add(new RegressionStrategy());
			
			else if (e.equals("selection.regression")) 
				this.strategies.add(new SelectionStrategy());			
			
			//outras estratégias
		}

	}

	public void criarAppraisalERodar(String originalDB, String regressionDB, String regressColumn) {
		System.out.println("criarerodar");
		Appraisal appr = new Appraisal("iniciando");
		
		//new ResultWriter(this.baseSuja,regressionDB);

		appr.testeWorkflow(originalDB, regressionDB, regressColumn, this.caminhoResult ,this.strategies);
		//o atributo 'Saida' do Appraisal foi criado para imprimir no console alguma informação para debug
		System.out.println(appr.getSaida());
		System.out.println("passou");
		
	}
}
