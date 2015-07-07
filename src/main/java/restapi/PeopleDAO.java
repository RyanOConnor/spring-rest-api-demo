package restapi;

public interface PeopleDAO {
	
	public void insert(People person);
	public People findById(int id);
	public void changeFirstName(int id, String firstName);
	public void changeLastName(int id, String lastName);
	public void deleteById(int id);
}
