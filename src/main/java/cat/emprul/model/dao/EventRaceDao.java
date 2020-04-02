package cat.emprul.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cat.emprul.model.entity.EventRace;

public class EventRaceDao {

	private List<EventRace> listOfEventRace;

	public List<EventRace> getListOfEventRace() {
		listOfEventRace = new ArrayList<EventRace>();
		EventRace eR1 = new EventRace(1L,"Trencacims","Trencacims de Paul",
				"Paüls","Catalunya",new Date(),new Date());
		EventRace eR2 = new EventRace(1L,"Ultra Pirineu","Ultra Pirineu Gabà",
				"Gabà","Catalunya",new Date(),new Date());
		
		listOfEventRace.add(eR1);
		listOfEventRace.add(eR2);
		return listOfEventRace;
	}

	public void setListOfEventRace(List<EventRace> listOfEventRace) {
		this.listOfEventRace = listOfEventRace;
	}
	
	
	
}
