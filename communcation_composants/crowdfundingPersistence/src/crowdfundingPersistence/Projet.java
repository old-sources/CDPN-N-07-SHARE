package crowdfundingPersistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "projet" database table.
 * 
 */
@Entity
@Table(name="projet")
@NamedQuery(name="Projet.findAll", query="SELECT p FROM Projet p")
public class Projet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="country")
	private String country;

	@Column(name="description")
	private String description;

	@Column(name="duration")
	private int duration;

	@Column(name="film_name")
	private String filmName;

	@Column(name="kind")
	private String kind;

	@Column(name="nom")
	private String nom;

	@Column(name="objectif")
	private int objectif;

	@Column(name="project_type")
	private String projectType;

	@Column(name="scale")
	private int scale;

	@Column(name="vehicle")
	private String vehicle;

	//bi-directional many-to-one association to Don
	@OneToMany(mappedBy="projet")
	private List<Don> dons;

	public Projet() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getFilmName() {
		return this.filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
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

	public String getProjectType() {
		return this.projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public int getScale() {
		return this.scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public List<Don> getDons() {
		return this.dons;
	}

	public void setDons(List<Don> dons) {
		this.dons = dons;
	}

	public Don addDon(Don don) {
		getDons().add(don);
		don.setProjet(this);

		return don;
	}

	public Don removeDon(Don don) {
		getDons().remove(don);
		don.setProjet(null);

		return don;
	}

}