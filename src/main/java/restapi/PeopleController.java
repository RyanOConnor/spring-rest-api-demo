package restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeopleController {
	
	@Autowired
	private PeopleDAO peopleDao;
	
	@RequestMapping("/createPerson")
	public void createPeopleObject(String firstName, String lastName) {
		if(firstName == null || lastName == null) return;
		peopleDao.insert(new People(firstName, lastName));
	}
	
	@RequestMapping("/getPerson")
	public People getPeopleObject(@RequestParam(value="id", defaultValue="-1")int id) {
		if(id == -1) return null;
		return peopleDao.findById(id);
	}
	
	@RequestMapping("/modifyFirstName")
	public void modifyFirstName(@RequestParam(value="id", defaultValue="-1")int id, String firstName) {
		if(id == -1) return;
		peopleDao.changeFirstName(id, firstName);
	}
	
	@RequestMapping("/modifyLastName")
	public void modifyLastName(@RequestParam(value="id", defaultValue="-1")int id, String lastName) {
		if(id == -1) return;
		peopleDao.changeLastName(id, lastName);
	}
	
	@RequestMapping("/deletePerson")
	public void deletePeopleObject(@RequestParam(value="id", defaultValue="-1")int id) {
		if(id == -1) return;
		peopleDao.deleteById(id);
	}
}
