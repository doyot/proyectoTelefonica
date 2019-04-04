package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modelo.Estudiante;
import modelo.Facultad;
import modelo.LocalDateDeserializer;
import modelo.LocalDateSerializer;
import modelo.Modelo;

/**
 * Servlet implementation class AJAXMostrarEstudiantes
 */
@WebServlet("/AJAXMostrarEstudiantes")
public class AJAXMostrarEstudiantes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AJAXMostrarEstudiantes() {
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
		
		Modelo m = new Modelo();
		List<Estudiante> estudiantes = m.getEstudiantes();
		
		String idFacultad = request.getParameter("dato");
		int facultad = Integer.parseInt(idFacultad);
		
		List<Estudiante> estudiantesPorFacultad = estudiantes.stream()
				.filter(e -> e.getIdFacultad() == facultad ).collect(Collectors.toList());
		
		response.setContentType("application/json;charset=UTF-8");
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		
		// Lo que se recibe en formato de JSON hay que deserializarlo
		// (Convertir a un objeto de java)

		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        //gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        //gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());

        Gson gson = gsonBuilder.setPrettyPrinting().create();
        
		PrintWriter out;
		out = response.getWriter();
		out.print(gson.toJson(estudiantesPorFacultad));
		
	}

}
