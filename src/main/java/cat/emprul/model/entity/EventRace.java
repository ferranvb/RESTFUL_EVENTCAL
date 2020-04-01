package cat.emprul.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * This is a simple class to model table EVENTS 
 * @author ferran
 *
 */
public class EventRace  implements Serializable {
	
	private Long id_event; 
	private String name;
	private String description;
	private String site;
	private String country;
	private Date dateStart;
	private Date dateEnd;
	
	//Constructors
	public EventRace() {
		
	}
	
	public EventRace(Long id_event, String name, String description, String site, String country, Date dateStart,
			Date dateEnd) {
		super();
		this.id_event = id_event;
		this.name = name;
		this.description = description;
		this.site = site;
		this.country = country;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}


	//Getters & Setters
	public Long getId_event() {
		return id_event;
	}

	public void setId_event(Long id_event) {
		this.id_event = id_event;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

}
