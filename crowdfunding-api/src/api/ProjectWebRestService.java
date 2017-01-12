package api;

import java.util.List;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import persistence.Don;
import persistence.Projet;

@Path("projet")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Stateless
public class ProjectWebRestService {
	@PersistenceContext
	EntityManager entityManager;

	@GET
	public Response getAllProjects() {
		List<Projet> list = entityManager.createNamedQuery("Projet.findAll", Projet.class).getResultList();
		return Response.ok(list).build();
	}

	@GET
	@Path("/{id}")
	public Response getOneProject(@PathParam("id") Integer id) {
		Projet projet = entityManager.find(Projet.class, id);
		return Response.ok(projet).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteOneProject(@PathParam("id") Integer id) {
		Projet projet = entityManager.find(Projet.class, id);
		ResponseBuilder responseBuilder;
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
			responseBuilder = Response.status(204);
		} else {
			responseBuilder = Response.status(404);
		}
		return responseBuilder.build();
	}

	@POST
	public Response createOneProject(Projet projet) {
		entityManager.persist(projet);
		return Response.ok(projet).build();
	}

	@PUT
	@Path("/{id}")
	public Response updateOneProject(Projet projet, @PathParam("id") Integer id) {
		Projet projetToUpdate = entityManager.find(Projet.class, id);
		if (projet.getNom() != null && !projet.getNom().isEmpty()) {
			projetToUpdate.setNom(projet.getNom());
		}
		if (projet.getDescription() != null && !projet.getDescription().isEmpty()) {
			projetToUpdate.setDescription(projet.getDescription());
		}
		projetToUpdate.setObjectif(projet.getObjectif());

		return Response.ok(projetToUpdate).build();

	}

}
