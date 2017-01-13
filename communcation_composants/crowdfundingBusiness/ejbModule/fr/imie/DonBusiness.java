package fr.imie;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import crowdfundingPersistence.Don;

/**
 * Session Bean implementation class DonBusiness
 */
@Stateless
@Remote(DonBusinessRemote.class)
public class DonBusiness implements DonBusinessRemote, DonBusinessLocal {

	@PersistenceContext
	EntityManager entityManager;
	
	/**
     * Default constructor. 
     */
    public DonBusiness() {
        // TODO Auto-generated constructor stub
    }

	@Override
    public List<Don> getOneDon(@PathParam("id") Integer id) {
		TypedQuery<Don> createNamedQuery = entityManager.createNamedQuery("Don.findFromProjet",Don.class);
		List<Don> dons= createNamedQuery.setParameter("id", id).getResultList();
		return dons;
	}


	@Override
	public Don createOneDon(Don don) {
		entityManager.persist(don);
		return don;
	}

}
