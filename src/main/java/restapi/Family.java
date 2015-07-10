package restapi;

import java.util.List;

public class Family {
	
	private List<People> members;
	
	public Family(List<People> members) {
		this.members = members;
	}
	
	public void addFamilyMember(People person) {
		members.add(person);
	}
	
	public List<People> getFamilyMembers() {
		return members;
	}
}
