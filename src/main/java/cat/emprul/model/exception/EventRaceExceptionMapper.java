package cat.emprul.model.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import cat.emprul.model.exception.ErrorMessage;

@Provider
public class EventRaceExceptionMapper implements ExceptionMapper<EventRaceException> {
 
	@Override
	public Response toResponse(EventRaceException exception) {
//		ErrorMessage errorMessage = new ErrorMessage(
//                exception.getMessage(),
//                ErrorMessages.COULD_NOT_CONNECT_DATABASE.name()
//        );
//        return Response.status(Response.Status.BAD_REQUEST).
//                entity(errorMessage).
//                build();
		
        return Response.status(Response.Status.BAD_REQUEST).build();
	    
		
		
	}
    
}