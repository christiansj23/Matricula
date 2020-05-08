package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name="Matricula",uniqueConstraints = { @UniqueConstraint(columnNames = {  "idMatricula" }) })
public class Matricula implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMatricula;
	
	
	@Past(message = "No puedes seleccionar un dia que todabia no existe")
	@Temporal(TemporalType.DATE)
	@Column(name="fechaMatricula",length=60,nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaMatricula;
	
	@Column(name="semestre",length=60,nullable=false)
	private String semestre;
	
	
	@ManyToOne
	@JoinColumn(name="idAlumno", nullable = false)
	private Alumno alumno;
	
	@ManyToOne
	@JoinColumn(name="idDocente", nullable = false)
	private Docente docente;
	
	@ManyToOne
	@JoinColumn(name="idCurso", nullable = false)
	private Curso curso;


	public Matricula() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Matricula(int idMatricula, Date fechaMatricula,String semestre, Alumno alumno, Docente docente, Curso curso) {
		super();
		this.idMatricula = idMatricula;
		this.fechaMatricula = fechaMatricula;
		this.semestre = semestre;
		this.alumno = alumno;
		this.docente = docente;
		this.curso = curso;
		
	}


	public int getIdMatricula() {
		return idMatricula;
	}


	public void setIdMatricula(int idMatricula) {
		this.idMatricula = idMatricula;
	}


	public Date getFechaMatricula() {
		return fechaMatricula;
	}


	public void setFechaMatricula(Date fechaMatricula) {
		this.fechaMatricula = fechaMatricula;
	}


	public String getSemestre() {
		return semestre;
	}


	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}


	public Alumno getAlumno() {
		return alumno;
	}


	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}


	public Docente getDocente() {
		return docente;
	}


	public void setDocente(Docente docente) {
		this.docente = docente;
	}


	public Curso getCurso() {
		return curso;
	}


	public void setCurso(Curso curso) {
		this.curso = curso;
	}


	
	
	


}