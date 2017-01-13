package crowdfundingRestAPI;

import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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

import crowdfundingPersistence.Projet;
import fr.imie.ProjetBusinessRemote;

@Path("projet")
@Produces({ MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Stateless
public class ProjectWebRestService {
	@EJB
	WebSocketAlert webSocketAlert;
	

	private ProjetBusinessRemote buildRemoteEJB() {
		Properties jndiProps = new Properties();
		jndiProps.put(Context.URL_PKG_PREFIXES,"org.jboss.ejb.client.naming");
		jndiProps.put(Context.PROVIDER_URL,"remote://172.16.28.117:8080");
		// username
		jndiProps.put(Context.SECURITY_PRINCIPAL, "ejbUser");
		// password
		jndiProps.put(Context.SECURITY_CREDENTIALS, "ejbUser");
		// create a context passing these properties
		Context ctx;
		ProjetBusinessRemote bean=null;
		try {
			ctx = new InitialContext(jndiProps);
			// lookup the bean beanRemoteInterface
			bean = (ProjetBusinessRemote) ctx.lookup("java:global/crowdfundingBusinessDeployement/crowdfundingBusiness/ProjetBusiness!fr.imie.ProjetBusinessRemote");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}
	

	
	@GET
	public Response getAllProjects() {	
		ProjetBusinessRemote bean = buildRemoteEJB();
		GenericEntity<List<Projet>> entity = new GenericEntity<List<Projet>>(buildRemoteEJB().getAllProjects()){};
		return Response.ok(entity).build();
	}

	

	@GET
	@Path("/{id}")
	public Response getOneProject(@PathParam("id") Integer id) {
		return Response.ok(buildRemoteEJB().getOneProject(id)).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteOneProject(@PathParam("id") Integer id) {
		ResponseBuilder responseBuilder;
		if (buildRemoteEJB().deleteOneProject(id)) {
			responseBuilder = Response.status(204);
		} else {
			responseBuilder = Response.status(404);
		}
		return responseBuilder.build();
	}

	@POST
	public Response createOneProject(Projet projet) {
		Projet created = buildRemoteEJB().createOneProject(projet);
		webSocketAlert.ping();
		return Response.ok(created).build();
	}

	@PUT
	@Path("/{id}")
	public Response updateOneProject(Projet projet, @PathParam("id") Integer id) {
		return Response.ok(buildRemoteEJB().updateOneProject(projet, id)).build();
	}

}
