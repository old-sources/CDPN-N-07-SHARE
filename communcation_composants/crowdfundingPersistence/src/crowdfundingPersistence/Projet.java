package crowdfundingPersistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the "projet" database table.
 * 
 */
@Entity
@Table(name="projet")
@NamedQuery(name="Projet.findAll", query="SELECT p FROM Projet p")
@XmlRootElement
public class Projet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="description")
	private String description;

	@Column(name="nom")
	private String nom;

	@Column(name="objectif")
	private int objectif;
	
	@Transient
	private int sommeDon;


	
	public Projet() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getObjectif() {
		return this.objectif;
	}

	public void setObjectif(int objectif) {
		this.objectif = objectif;
	}

	public int getSommeDon() {
		return sommeDon;
	}

	public void setSommeDon(int sommeDon) {
		this.sommeDon = sommeDon;
	}
	
	

}