package modelo;

import java.time.LocalDate;

public class Estudiante {
	private int id;
	private String nombre;
	private LocalDate fechaAlta;
	private int idFacultad;
	private String descripcion;
	private String cantidad;
	
	public Estudiante(int id, String nombre, LocalDate fechaAlta, int idFacultad, String descripcion, String cantidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaAlta = fechaAlta;
		this.idFacultad = idFacultad;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public int getIdFacultad() {
		return idFacultad;
	}

	public void setIdFacultad(int idFacultad) {
		this.idFacultad = idFacultad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	

	
}
