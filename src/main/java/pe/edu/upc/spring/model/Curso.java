package pe.edu.upc.spring.model;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Temporal;


@Entity
@Table(name="Curso",uniqueConstraints = { @UniqueConstraint(columnNames = {  "nombreCurso" }) })
public class Curso implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCurso;
	
	@NotEmpty(message = "No puede estar vacío")
	@NotBlank(message = "No puede estar en blanco")
	@Column(name="nombreCurso",length=60,nullable=false)
	private String nombreCurso;
	
	@NotEmpty(message = "No puede estar vacío")
	@NotBlank(message = "No puede estar en blanco")
	@Column(name="carreraCurso",length=60,nullable=false)
	private String carreraCurso;
	
	private int creditosCurso;
	
	@ManyToOne
	@JoinColumn(name="idDocente", nullable = false)
	private Docente docente;


	public Curso() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Curso(int idCurso, String nombreCurso, 
			String carreraCurso, 
			int creditosCurso,
			Docente docente) {
		super();
		this.idCurso = idCurso;
		this.nombreCurso = nombreCurso;
		this.carreraCurso = carreraCurso;
		this.creditosCurso = creditosCurso;
		this.docente = docente;
		

		
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public String getCarreraCurso() {
		return carreraCurso;
	}

	public void setCarreraCurso(String carreraCurso) {
		this.carreraCurso = carreraCurso;
	}

	public int getCreditosCurso() {
		return creditosCurso;
	}

	public void setCreditosCurso(int creditosCurso) {
		this.creditosCurso = creditosCurso;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}


	

}