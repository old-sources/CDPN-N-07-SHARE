package crowdfundingRestAPI;

import java.util.Properties;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import crowdfundingPersistence.Don;
import fr.imie.DonBusinessRemote;

@Path("don")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Stateless
public class DonWebRestService {
	private DonBusinessRemote builldRemoteEJB() {
		Properties jndiProps = new Properties();
		jndiProps.put(Context.URL_PKG_PREFIXES,"org.jboss.ejb.client.naming");
		jndiProps.put(Context.PROVIDER_URL,"remote://localhost:4447");
		// username
		jndiProps.put(Context.SECURITY_PRINCIPAL, "ejbUser");
		// password
		jndiProps.put(Context.SECURITY_CREDENTIALS, "ejbUser");
		// create a context passing these properties
		Context ctx;
		DonBusinessRemote bean=null;
		try {
			ctx = new InitialContext(jndiProps);
			// lookup the bean beanRemoteInterface
			bean = (DonBusinessRemote) ctx.lookup("java:global/crowdfundingBusinessDeployement/crowdfundingBusiness/DonBusiness!fr.imie.DonBusinessRemote");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	@GET
	@Path("/projet_id/{id}")
	public Response getOneProject(@PathParam("id") Integer id) {
		return Response.ok(builldRemoteEJB().getOneDon(id)).build();
	}


	@POST
	public Response createOneProject(Don don) {
		return Response.ok(builldRemoteEJB().createOneDon(don)).build();
	}

	

}
