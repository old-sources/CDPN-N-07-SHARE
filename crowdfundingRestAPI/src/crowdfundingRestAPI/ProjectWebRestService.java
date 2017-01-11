package crowdfundingRestAPI;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import crowdfundingPersistence.Don;
import crowdfundingPersistence.Projet;

@Path("projet")
@Produces({MediaType.APPLICATION_JSON})
@Stateless
public class ProjectWebRestService {
	@PersistenceContext
	EntityManager entityManager;
	
	@GET
	public Response getAllProjects(){
		List<Projet> list = entityManager.createNamedQuery("Projet.findAll").getResultList();
		return Response.ok(list).build();
	}

}
