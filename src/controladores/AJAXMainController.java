package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSeparatorUI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modelo.Estudiante;
import modelo.LocalDateDeserializer;
import modelo.LocalDateSerializer;
import modelo.Modelo;

/**
 * Servlet implementation class AJAXMainController
 */
@WebServlet("/AJAXMainController")
public class AJAXMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AJAXMainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		response.setContentType("application/json;charset=UTF-8");
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		
		// Lo que se recibe en formato de JSON hay que deserializarlo
		// (Convertir a un objeto de java)

		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        //gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        //gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());

        Gson gson = gsonBuilder.setPrettyPrinting().create();
        String estudianteAltaJSON = request.getParameter("estudianteAltaJSON");
        
        //System.out.println(gson.fromJson(estudianteAltaJSON, Estudiante.class));
		
		Estudiante estudiante = gson
				.fromJson(estudianteAltaJSON, Estudiante.class);
		System.out.println(estudiante.getFechaAlta());
		
		// Insertar el estudiante en la tabla de estudiantes
		Modelo m = new Modelo();
		int total = m.setEstudiante(estudiante);
		
		PrintWriter out;
		out = response.getWriter();
		if (total>0) 
			out.print("Estudiante dado de alta correctamente!!!");
		else
			out.print("Error al dar de alta el estudiante");
		
	}

}
