package restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class FamilyDAOImpl implements FamilyDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void insertRelationship(Relationship rel) {
		if(rel == null) return;
		String query = "INSERT INTO relationships (p_id1, p_id2) VALUES (?, ?)";
		try {
			jdbcTemplate.update(query, new Object[] { rel.getId1(), rel.getId2() });
			jdbcTemplate.update(query, new Object[] { rel.getId2(), rel.getId1() });
		} catch (DataAccessException ex) {
			// Log exception
		}
	}
	
	public Family getFamilyMembers(int p_id) {
		List<People> familyMembers = null;
		String query = "SELECT * "
				      + "FROM people "
				      + "WHERE id IN ( "
				      + "	SELECT p_id2 "
				      + "	FROM relationships "
				      + "	WHERE p_id1 = ? )";
		try {
			familyMembers = jdbcTemplate.query(query, new PeopleMapper(), new Object[] { p_id });
		} catch (DataAccessException ex) {
			// Log exception
		} 
		return new Family(familyMembers);
	}
}
