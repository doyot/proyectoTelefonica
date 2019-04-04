package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataBaseConnection {
	private String user;
	private String password;
	private Connection conn;
	
	public DataBaseConnection(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}
	
	public Connection getConnectionDB() throws ClassNotFoundException {
		conn = null;
		
		String urlConnection = "jdbc:mysql://localhost:3306/dieta?" 
				+ "useUnicode=true&useJDBCCompliantTimezoneShift=true&"
                + "useLegacyDatetimeCode=false&serverTimezone=UTC";
		
		Properties info;
		
		info = new Properties();
		info.put("user", this.user);
		info.put("password", this.password);
		
		try {
			// Registrar el Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(urlConnection, info);
			System.out.println("Conexión establecida con la base de datos");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}

	public ResultSet getFacultades() throws SQLException {
		ResultSet rs = null;
		String query = "SELECT * FROM momentoComida";
		Statement stmt;
		
		try {
			conn = getConnectionDB();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	// Método que inserta el estudiante en la base de Datos
	
	public int insertarEstudianteBD(Estudiante e) throws SQLException {
		int totalRegistros = 0;
		ResultSet rs = null;
		String query = "INSERT INTO `dieta`.`alimentos` "
				+ "(`idMomentoComida`, `nombre`, `fechaIngerir`, `descripcion`, `cantidad`) "
				+ "VALUES (?, ?, ?, ?, ?)";
		PreparedStatement stmt = null;
		
		try {
			conn = getConnectionDB();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, e.getIdFacultad());
			stmt.setString(2, e.getNombre());
			stmt.setDate(3, Date.valueOf(e.getFechaAlta()));
			stmt.setString(4, e.getDescripcion());
			stmt.setString(5, e.getCantidad());
			totalRegistros = stmt.executeUpdate();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return totalRegistros;
	}
	
	// Recupera la lista de Estudiantes completamente
	public ResultSet getEstudiantes() throws SQLException {
		ResultSet rs = null;
		String query = "SELECT * FROM alimentos";
		Statement stmt;
		
		try {
			conn = getConnectionDB();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
}
