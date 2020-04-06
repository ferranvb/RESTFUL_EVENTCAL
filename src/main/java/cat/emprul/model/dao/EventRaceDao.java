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

	//Constructors
	private List<EventRace> listOfEventRace;

	public EventRaceDao() {
		logger.trace("We've just greeted the user!");
		logger.debug("We've just greeted the user!");
		logger.info("We've just greeted the user!");
		logger.warn("We've just greeted the user!");
		logger.error("We've just greeted the user!");
		logger.fatal("We've just greeted the user!");
	}

	//Methods
	/**
	 * Method to get all the EventRace from database
	 * @return
	 */
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
		}
		
		return listOfEventRace;
	}
	
	/**
	 * Method to get an EventRace from id_event from database
	 * @return
	 */
	public EventRace getEventRacefromId(Long id_event) {
		EventRace eventRace = null;

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
			
			
			String sqlSelectQuery = "SELECT id_event,nom,descripcio,lloc,pais,data_inici,data_fi FROM EVENTS " +
						"WHERE id_event = ?";
			
			preparedStatement = connection.prepareStatement(sqlSelectQuery);

			preparedStatement.setLong(1, id_event);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				eventRace = fillEventRacefromResulSet(resultSet);
			}
			
			connection.close();
		} catch (SQLException e) {
			logger.error("Error SQL" + e);
			throw new EventRaceException(ErrorMessages.COULD_NOT_CONNECT_DATABASE.getErrorMessage());
		}
		
		return eventRace;
	}
	
	
	/**
	 * Method to fill an object EventRace from tthe resulset
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
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

	
	public void insertEvenRace(EventRace eventRace) {
		
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
			
			
			String sqlInsertQuery = "INSERT INTO events (nom,descripcio,lloc,pais,data_inici,data_fi) VALUES (?,?,?,?,?,?)";
				
			preparedStatement = connection.prepareStatement(sqlInsertQuery);
			
			preparedStatement.setString(1, eventRace.getName());
			preparedStatement.setString(2, eventRace.getDescription());
			preparedStatement.setString(3, eventRace.getSite());
			preparedStatement.setString(4, eventRace.getCountry());
			preparedStatement.setDate(5,  new java.sql.Date(eventRace.getDateStart().getTime()));
			preparedStatement.setDate(6, new java.sql.Date(eventRace.getDateEnd().getTime()));
			
			preparedStatement.executeUpdate();

			connection.close();
		} catch (SQLException e) {
			logger.error("Error SQL" + e);
			throw new EventRaceException(ErrorMessages.COULD_NOT_CONNECT_DATABASE.getErrorMessage());
			
		}
	}
	
	public void updatetEvenRace(EventRace eventRace) {
		
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
			
			
			String sqlInsertQuery = "UPDATE events SET " +
					"nom = ? ,descripcio = ? ,lloc = ? ,pais = ? ,data_inici = ? ,data_fi = ? " +
					"WHERE id_event = ?";
				
			preparedStatement = connection.prepareStatement(sqlInsertQuery);
			
			preparedStatement.setString(1, eventRace.getName());
			preparedStatement.setString(2, eventRace.getDescription());
			preparedStatement.setString(3, eventRace.getSite());
			preparedStatement.setString(4, eventRace.getCountry());
			preparedStatement.setDate(5, new java.sql.Date(eventRace.getDateStart().getTime()));
			preparedStatement.setDate(6, new java.sql.Date(eventRace.getDateEnd().getTime()));
			
			preparedStatement.setLong(7, eventRace.getId_event());
			preparedStatement.executeUpdate();
			
			connection.close();
		} catch (SQLException e) {
			logger.error("Error SQL" + e);
			throw new EventRaceException(ErrorMessages.COULD_NOT_CONNECT_DATABASE.getErrorMessage());
			
		}
	}
	
	/**
	 * Metode per comprovar si ja evistei una entrada amb el mateix nom
	 */
	public boolean testRepeatedEventRace(String name) {
		boolean repeated = false;
		
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
			
			
			String sqlSelectQuery = "SELECT nom FROM EVENTS " +
						"WHERE nom = ?";
			
			preparedStatement = connection.prepareStatement(sqlSelectQuery);

			preparedStatement.setString(1, name);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				repeated = true;
			}
			
			connection.close();
		} catch (SQLException e) {
			logger.error("Error SQL" + e);
			throw new EventRaceException(ErrorMessages.COULD_NOT_CONNECT_DATABASE.getErrorMessage());
		}
		
		return repeated;
	}
	
	
	
}
