package fr.usmb.m2isc.mesure.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.usmb.m2isc.mesure.ejb.MesureEJB;
import fr.usmb.m2isc.mesure.jpa.Mesure;

/**
 * Servlet implementation class AddMesureServlet
 */
@WebServlet("/AddMesureServlet")
public class AddMesureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// injection de la reference de l'ejb
	@EJB
	private MesureEJB ejb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMesureServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recuperation et parsing des parametres de la requete
		String piece = request.getParameter("piece");
		double temp = Double.parseDouble(request.getParameter("temp"));
		// appel de la methode d'ajout sur l'ejb
		Mesure m = ejb.addMesure(piece, temp);		
		// ajout de la mesure dans la requete
		request.setAttribute("mesure",m);
		// transfert a la JSP d'affichage
		request.getRequestDispatcher("/showMesure.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
