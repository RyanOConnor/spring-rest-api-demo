package restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FamilyController {

	@Autowired
	private FamilyDAO familyDao;

	@RequestMapping("/createRelationship")
	public void createRelationship(@RequestParam(value="p_id1", defaultValue="-1") int p_id1, 
								   @RequestParam(value="p_id2", defaultValue="-1") int p_id2) {
		if(p_id1 == -1 || p_id2 == -1) return;
		familyDao.insertRelationship(new Relationship(p_id1, p_id2));
	}
	
	@RequestMapping("/getFamilyMembers")
	public Family getFamilyMembers(@RequestParam(value="p_id", defaultValue="-1") int p_id) {
		if(p_id == -1) return null;
		return familyDao.getFamilyMembers(p_id);
	}
}
