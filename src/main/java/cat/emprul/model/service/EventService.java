package cat.emprul.model.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	@Path("events/ok")
	@Produces(MediaType.TEXT_PLAIN)
	public String getServiceOK() {
		
		String response = "OK - service is workin";
		return response;
	}
	
	/**
	 * This method return an XML with a list of events
	 * URL : http://localhost:8080/eventcal/rest/eventcal/events/list
	 * @return
	 */
	@GET
	@Path("events/listxml")
	@Produces(MediaType.APPLICATION_XML)
	public List<EventRace> getListOfEventRaceXML() {
		EventRaceDao eDao = new EventRaceDao();
		return eDao.getListOfEventRace();
	}
	
	
	/**
	 * This method return an JSON with a list of events
	 * URL : http://localhost:8080/eventcal/rest/eventcal/events/listjson
	 * @return
	 */
	@GET
	@Path("events/listjson")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListOfEventRaceJSON() {
		
		EventRaceDao eDao = new EventRaceDao();
		GenericEntity<List<EventRace>> entity  = new GenericEntity<List<EventRace>>(eDao.getListOfEventRace()) {};
		return Response.ok(entity).build();
		
	}
	
	/**
	 * This method return an JSON event with parameter {id_event} as @param id_event
	 * URL : http://localhost:8080/eventcal/rest/eventcal/event/{id_event}
	 * @param id_event
	 * @return
	 */
	@GET
	@Path("event/{id_event}")
	@Produces(MediaType.APPLICATION_JSON)
	public EventRace getEventRacefromId(@PathParam("id_event") Long id_event) {
		EventRaceDao eDao = new EventRaceDao();
		return eDao.getEventRacefromId(id_event);
	}
	
	/**
	 * This method save an event
	 * URL : http://localhost:8080/eventcal/rest/eventcal/events/post
	 * Sample: {"country":"Catalunya","dateEnd":"1601933724","dateStart":"1601791200","description":"Cursa de la Ultra Pirineu","name":"Ultra Pirineu","site":"Bagà"}
	 * @return
	 */
	@POST
	@Path("events/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertEvenRace(EventRace eventRace) {
		String result ="";
		
		EventRaceDao eDao = new EventRaceDao();
		if ( eDao.testRepeatedEventRace(eventRace.getName())) {
			result = "EventRace repeated";
		} else {
			eDao.insertEvenRace(eventRace);
			result = "EventRace saved : " + eventRace;
		}
		
		return Response.status(201).entity(result).build();
		
	}

	/**
	 * This method update an event
	 * URL : http://localhost:8080/eventcal/rest/eventcal/events/put
	 * Sample: {"country":"Catalunya","dateEnd":"1601933724","dateStart":"1601791200","description":"Cursa de la Ultra Pirineu","name":"Ultra Pirineu","site":"Bagà"}
	 * @return
	 */
	@PUT
	@Path("events/put")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatetEvenRace(EventRace eventRace) {
		
		EventRaceDao eDao = new EventRaceDao();
		eDao.updatetEvenRace(eventRace);
		
		String result = "EventRace updated : " + eventRace;
		return Response.status(201).entity(result).build();
		
	}
	
}
