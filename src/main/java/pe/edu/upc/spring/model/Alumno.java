package pe.edu.upc.spring.model;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Temporal;


@Entity
@Table(name="Alumno",uniqueConstraints = { @UniqueConstraint(columnNames = {  "nombreAlumno" }) })
public class Alumno implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAlumno;
	
	@NotEmpty(message = "No puede estar vac?")
	@NotBlank(message = "No puede estar en blanco")
	@Column(name="nombreAlumno",length=60,nullable=false)
	private String nombreAlumno;
	
	@NotEmpty(message = "No puede estar vac?")
	@NotBlank(message = "No puede estar en blanco")
	@Column(name="apellidoAlumno",length=60,nullable=false)
	private String apellidoAlumno;
	
	@Column(name="cicloAlumno",length=60,nullable=false)
	private String cicloAlumno;
	
	String Foto;


	public Alumno() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Alumno(int idAlumno, String nombreAlumno, 
			String apellidoAlumno, 
			String cicloAlumno , String foto ) {
		super();
		this.idAlumno = idAlumno;
		this.nombreAlumno = nombreAlumno;
		this.apellidoAlumno = apellidoAlumno;
		this.cicloAlumno = cicloAlumno;
		Foto = foto;
		

		
	}

	public int getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}

	public String getNombreAlumno() {
		return nombreAlumno;
	}

	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}

	public String getApellidoAlumno() {
		return apellidoAlumno;
	}

	public void setApellidoAlumno(String apellidoAlumno) {
		this.apellidoAlumno = apellidoAlumno;
	}

	public String getCicloAlumno() {
		return cicloAlumno;
	}

	public void setCicloAlumno(String cicloAlumno) {
		this.cicloAlumno = cicloAlumno;
	}
	public String getFoto() {
		return Foto;
	}

	public void setFoto(String foto) {
		Foto = foto;
	}

}