package com.mef.formationjee.core.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@NamedQueries({
	@NamedQuery(name=Formation.FORMATION_FIND_ALL, query="select f from Formation f")
})
@Entity
@Table(name="FORMATION")
public class Formation {

	public static final String FORMATION_FIND_ALL = "Formation.findAll";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; //Clé primaire
	
	@NotEmpty
	@Length(min = 4, max = 20)
	@Column(name = "THEMATIQUE", length = 50, nullable = false, unique = true)
	private String theme; //thÃ¨me de la formation
	
	@Min(value = 100)
	@Column(name = "PRIX", nullable = false)
	private Integer prix;
	
	@NotNull
	@Future
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DATE_DEBUT", nullable = false)
	private Date dateDebut;
	
	@Column(name = "TYPE_FORMATION", nullable = false)
	private String type;

	public Formation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Integer getPrix() {
		return prix;
	}

	public void setPrix(Integer prix) {
		this.prix = prix;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
