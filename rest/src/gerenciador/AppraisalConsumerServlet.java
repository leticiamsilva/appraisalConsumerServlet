package gerenciador;

import javax.servlet.http.HttpServlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/appraisalConsumer")
public class AppraisalConsumerServlet extends HttpServlet {
	public static ManipulacaoAppraisal manAppraisalAVG;

	/*
	 * @Override public void service(HttpServletRequest req, HttpServletResponse
	 * resp) throws IOException { PrintWriter out = resp.getWriter();
	 * out.println("<html>"); out.println("teste"); out.println("</html>"); }
	 */


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//
		///1. Cria a Instancia para Manipular o Appraisal
		//
		///OBS: O Taverna não aceita barras no envio do Get, entao precisam ser substitúidas pelo underscore
		String caminhoResultadoBaseARegredir = req.getParameter("baseARegredir").replace("Ãª", "ê");		
		caminhoResultadoBaseARegredir = caminhoResultadoBaseARegredir.replace("_", "\\\\");
		
		manAppraisalAVG = new ManipulacaoAppraisal(caminhoResultadoBaseARegredir);
		
		//
		///2. Recupera as estratérgias desejadas
		//
		String estrategias = req.getParameter("estrategias");
		manAppraisalAVG.criarEstrategias(estrategias);


		//
		///3. Roda a imputação
		//		
		String originalDB = req.getParameter("baseOriginal");
		String regressionDB = req.getParameter("baseSuja");	
		String regressColumn = req.getParameter("colunaARegredir");
		manAppraisalAVG.criarAppraisalERodar(originalDB, regressionDB, regressColumn);
		
		
		PrintWriter out = resp.getWriter();
		
	
		out.println("  ");
		out.println(manAppraisalAVG.getBaseSuja());
		out.println("  ");
		out.print(regressionDB);
	}

}
