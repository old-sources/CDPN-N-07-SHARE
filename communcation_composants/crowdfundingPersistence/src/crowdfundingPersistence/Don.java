package crowdfundingPersistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "don" database table.
 * 
 */
@Entity
@Table(name="don")
@NamedQuery(name="Don.findAll", query="SELECT d FROM Don d")
public class Don implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;


	@Column(name="valeur")
	private int valeur;

	//uni-directional many-to-one association to Personne
	@ManyToOne
	@JoinColumn(referencedColumnName="id", name="personne_id")
	private Personne personne;

	//bi-directional many-to-one association to Projet
	@ManyToOne
	@JoinColumn(referencedColumnName="id",name="projet_id")
	private Projet projet;

	public Don() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getValeur() {
		return this.valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public Personne getPersonne() {
		return this.personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public Projet getProjet() {
		return this.projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

}