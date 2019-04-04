package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Modelo
 */
@Stateless
@LocalBean
public class Modelo {
	private List<Facultad> facultades;
	private Estudiante estudiante;
	private List<Estudiante> estudiantes;

	/**
     * Default constructor. 
     */
    public Modelo() {
        
    }
    
	public List<Facultad> getFacultades() {
		DataBaseConnection db;
		
		db = new DataBaseConnection("root", "Temp2019$$");
		
		
		this.facultades = new ArrayList<>();
		try {
			ResultSet rs = db.getFacultades();
			while(rs.next()) {
				this.facultades
					.add(new Facultad(rs.getInt("id"),
									  rs.getString("nombre")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return facultades;
	}
	
	public void setFacultades(List<Facultad> facultades) {
		this.facultades = facultades;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public int setEstudiante(Estudiante estudiante) {
		DataBaseConnection db;
		db = new DataBaseConnection("root", "Temp2019$$");
		
		int totalRegistrosInsertados = 0;
		try {
			totalRegistrosInsertados = db.insertarEstudianteBD(estudiante);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return totalRegistrosInsertados;
	}

	public List<Estudiante> getEstudiantes() {
		
		DataBaseConnection db;
		
		db = new DataBaseConnection("root", "Temp2019$$");
		
		
		this.estudiantes = new ArrayList<>();
		try {
			ResultSet rs = db.getEstudiantes();
			while(rs.next()) {
				this.estudiantes
					.add(new Estudiante(
										rs.getInt("id"), 
										rs.getString("nombre"), 
										rs.getDate("fechaIngerir").toLocalDate(), 
										rs.getInt("idMomentoComida"),
										rs.getString("descripcion"),
										rs.getString("cantidad")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return estudiantes;
	}
	
	
}
