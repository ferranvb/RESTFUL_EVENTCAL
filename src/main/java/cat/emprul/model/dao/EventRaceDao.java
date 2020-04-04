package cat.emprul.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cat.emprul.model.entity.EventRace;
import cat.emprul.model.exception.ErrorMessage;
import cat.emprul.model.exception.ErrorMessages;
import cat.emprul.model.exception.EventRaceException;
import cat.emprul.model.exception.EventRaceExceptionMapper;

public class EventRaceDao {

	private static final Logger logger = LogManager.getLogger(EventRaceDao.class);

	private static final String SERVERHOST = "jdbc:mysql://localhost:3306/events_run_bd";
	private static final String USER = "root";
	private static final String PASSW = "";

	private List<EventRace> listOfEventRace;

	public EventRaceDao() {
		logger.trace("We've just greeted the user!");
		logger.debug("We've just greeted the user!");
		logger.info("We've just greeted the user!");
		logger.warn("We've just greeted the user!");
		logger.error("We've just greeted the user!");
		logger.fatal("We've just greeted the user!");
	}

	public List<EventRace> getListOfEventRace() {
		listOfEventRace = new ArrayList<EventRace>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			logger.error("Problem in loading MySQL JDBC driver");
		}

		try {
			connection = DriverManager.getConnection(SERVERHOST, USER, PASSW);
			
			
			String sqlSelectQuery = "SELECT id_event,nom,descripcio,lloc,pais,data_inici,data_fi FROM EVENTS";
			
			preparedStatement = connection.prepareStatement(sqlSelectQuery);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				EventRace eventRace = fillEventRacefromResulSet(resultSet);
				listOfEventRace.add(eventRace);
			}
			
			connection.close();
		} catch (SQLException e) {
			logger.error("Error SQL" + e);
			throw new EventRaceException(ErrorMessages.COULD_NOT_CONNECT_DATABASE.getErrorMessage());
			
//			.ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());


		}
		
		
		return listOfEventRace;
	}

	private EventRace fillEventRacefromResulSet(ResultSet rs) throws SQLException {
		EventRace eventRace = new EventRace();
		eventRace.setId_event(rs.getLong("id_event"));
		eventRace.setName(rs.getString("nom"));
		eventRace.setDescription(rs.getString("descripcio"));
		eventRace.setSite(rs.getString("lloc"));
		eventRace.setCountry(rs.getString("pais"));
		eventRace.setDateStart(rs.getDate("data_inici"));
		eventRace.setDateEnd(rs.getDate("data_fi"));
		
		return eventRace;
	}

	public void setListOfEventRace(List<EventRace> listOfEventRace) {
		this.listOfEventRace = listOfEventRace;
	}

}
