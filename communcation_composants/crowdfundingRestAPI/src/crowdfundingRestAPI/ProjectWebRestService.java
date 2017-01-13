package crowdfundingRestAPI;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import crowdfundingPersistence.Don;
import crowdfundingPersistence.Projet;
import fr.imie.ProjetBusinessLocal;

@Path("projet")
@Produces({ MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Stateless
public class ProjectWebRestService {
	@PersistenceContext
	EntityManager entityManager;
	
	@EJB
	ProjetBusinessLocal projetBusiness;

	@GET
	public Response getAllProjects() {
		GenericEntity<List<Projet>> entity = new GenericEntity<List<Projet>>(projetBusiness.getAllProjects()){};
		return Response.ok(entity).build();
	}

	@GET
	@Path("/{id}")
	public Response getOneProject(@PathParam("id") Integer id) {
		
		return Response.ok(projetBusiness.getOneProject(id)).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteOneProject(@PathParam("id") Integer id) {

		ResponseBuilder responseBuilder;
		if (projetBusiness.deleteOneProject(id)) {
			responseBuilder = Response.status(204);
		} else {
			responseBuilder = Response.status(404);
		}
		return responseBuilder.build();
	}

	@POST
	public Response createOneProject(Projet projet) {
		return Response.ok(projetBusiness.createOneProject(projet)).build();
	}

	@PUT
	@Path("/{id}")
	public Response updateOneProject(Projet projet, @PathParam("id") Integer id) {
		return Response.ok(projetBusiness.updateOneProject(projet, id)).build();

	}

}
