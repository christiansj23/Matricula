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
@Table(name="Docente",uniqueConstraints = { @UniqueConstraint(columnNames = {  "nombreDocente" }) })
public class Docente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDocente;
	
	@NotEmpty(message = "No puede estar vacío")
	@NotBlank(message = "No puede estar en blanco")
	@Column(name="nombreDocente",length=60,nullable=false)
	private String nombreDocente;
	
	@NotEmpty(message = "No puede estar vacío")
	@NotBlank(message = "No puede estar en blanco")
	@Column(name="apellidoDocente",length=60,nullable=false)
	private String apellidoDocente;
	

	public Docente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Docente(int idDocente, String nombreDocente, 
			String apellidoDocente,  String foto ) {
		super();
		this.idDocente = idDocente;
		this.nombreDocente = nombreDocente;
		this.apellidoDocente = apellidoDocente;
		
		

		
	}

	public int getIdDocente() {
		return idDocente;
	}

	public void setIdDocente(int idDocente) {
		this.idDocente = idDocente;
	}

	public String getNombreDocente() {
		return nombreDocente;
	}

	public void setNombreDocente(String nombreDocente) {
		this.nombreDocente = nombreDocente;
	}

	public String getApellidoDocente() {
		return apellidoDocente;
	}

	public void setApellidoDocente(String apellidoDocente) {
		this.apellidoDocente = apellidoDocente;
	}

	

}