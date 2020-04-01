package cat.emprul.model.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.spi.container.servlet.ServletContainer;

@Path("/eventcal")
@Produces(MediaType.TEXT_PLAIN)
public class EventService {
	
	@GET
	@Path("events")
	@Produces(MediaType.TEXT_PLAIN)
	public String getEventRaces() {
		
		String response = "Data 1";
		
		return response;
		
	}
}
