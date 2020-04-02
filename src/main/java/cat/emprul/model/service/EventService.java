package cat.emprul.model.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cat.emprul.model.dao.EventRaceDao;
import cat.emprul.model.entity.EventRace;

@Path("/eventcal")
@Produces(MediaType.TEXT_PLAIN)
public class EventService {
	
	/**
	 * This method return an plain text to test that service is working
	 * URL : http://localhost:8080/eventcal/rest/eventcal/events/plain
	 * @return
	 */
	@GET
	@Path("events/plain")
	@Produces(MediaType.TEXT_PLAIN)
	public String getEventRaces() {
		
		String response = "OK - service is workin";
		return response;
	}
	
	/**
	 * This method return an XML with a list of events
	 * URL : http://localhost:8080/eventcal/rest/eventcal/events/list
	 * @return
	 */
	@GET
	@Path("events/list")
	@Produces(MediaType.APPLICATION_XML)
	public List<EventRace> getListOfEventRaceXML() {
		EventRaceDao eDao = new EventRaceDao();
		return eDao.getListOfEventRace();
	}
	
	
	/**
	 * This method return an XML with a list of events
	 * URL : http://localhost:8080/eventcal/rest/eventcal/events/listjson
	 * @return
	 */
	@GET
	@Path("events/listjson")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListOfEventRaceJSON() {
		
		try {
			EventRaceDao eDao = new EventRaceDao();
		
			GenericEntity<List<EventRace>> entity  = new GenericEntity<List<EventRace>>(eDao.getListOfEventRace()) {};

			return Response.ok(entity).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
	}
	
	
}
