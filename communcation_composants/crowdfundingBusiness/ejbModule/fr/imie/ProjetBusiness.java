package fr.imie;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;


import crowdfundingPersistence.Don;
import crowdfundingPersistence.Projet;

/**
 * Session Bean implementation class ProjetBusiness
 */
@Stateless
public class ProjetBusiness implements ProjetBusinessRemote, ProjetBusinessLocal {

	@PersistenceContext
	EntityManager entityManager;
	
	/**
     * Default constructor. 
     */
    public ProjetBusiness() {
        // TODO Auto-generated constructor stub
    }
    
    private Projet computeDonValue(Projet projetIn){
		Projet projetOut = projetIn;
		TypedQuery<Don> createNamedQuery = entityManager.createNamedQuery("Don.findFromProjet",Don.class);
		List<Don> dons= createNamedQuery.setParameter("id", projetIn.getId()).getResultList();
		Integer somme = 0;
		for (Don don : dons) {
			somme+=don.getValeur();
		}
		projetOut.setSommeDon(somme);
		return projetOut;
	}

	@Override
	public List<Projet> getAllProjects() {
		List<Projet> list = entityManager.createNamedQuery("Projet.findAll", Projet.class).getResultList();
		List<Projet> listOut = new ArrayList<>();
		for (Projet projet : list) {
			listOut.add(computeDonValue(projet));
		}

		return listOut;
	}

	@Override
	public Projet getOneProject( Integer id) {
		Projet projet = entityManager.find(Projet.class, id);
		return computeDonValue(projet);
	}

	@Override
	public Boolean deleteOneProject(Integer id) {
		Projet projet = entityManager.find(Projet.class, id);
		Boolean out=null;

		if (projet != null) {
			
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Don> query =qb.createQuery(Don.class);
			Root<Don> don = query.from(Don.class);
			Join<Don, Projet> projetDeleted = don.join("projet");
			query.where(qb.equal(projetDeleted.get("id"),id));
			List<Don> result = entityManager.createQuery(query).getResultList();
			for (Don donToDelete : result) {
				entityManager.remove(donToDelete);
			}
			//solution 2 avec NamedQuery de delete
			//TypedQuery<Don> createNamedQuery = (TypedQuery<Don>) entityManager.createNamedQuery("Don.deleteFromProjet");
			//createNamedQuery.setParameter("id", id).executeUpdate();

			entityManager.remove(projet);
			out = true;
		} else {
			out = false;
		}
		return out;
	}

	@Override
	public Projet createOneProject(Projet projet) {
		entityManager.persist(projet);
		return projet;
	}

	@Override
	public Projet updateOneProject(Projet projet,Integer id) {
		Projet projetToUpdate = entityManager.find(Projet.class, id);
		if (projet.getNom() != null && !projet.getNom().isEmpty()) {
			projetToUpdate.setNom(projet.getNom());
		}
		if (projet.getDescription() != null && !projet.getDescription().isEmpty()) {
			projetToUpdate.setDescription(projet.getDescription());
		}
		projetToUpdate.setObjectif(projet.getObjectif());

		return projetToUpdate;

	}

}
