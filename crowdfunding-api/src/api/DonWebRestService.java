package api;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import persistence.Don;
import persistence.Projet;

@Path("don")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Stateless
public class DonWebRestService {
	@PersistenceContext
	EntityManager entityManager;

	

	@GET
	@Path("/projet_id/{id}")
	public Response getOneProject(@PathParam("id") Integer id) {
		TypedQuery<Don> createNamedQuery = entityManager.createNamedQuery("Don.findFromProjet",Don.class);
		List<Don> dons= createNamedQuery.setParameter("id", id).getResultList();
		return Response.ok(dons).build();
	}


	@POST
	public Response createOneProject(Don don) {
		entityManager.persist(don);
		return Response.ok(don).build();
	}

	

}
