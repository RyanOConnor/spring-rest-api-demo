package restapi;

public interface FamilyDAO {
	
	public void insertRelationship(Relationship rel);
	public Family getFamilyMembers(int p_id);
}
