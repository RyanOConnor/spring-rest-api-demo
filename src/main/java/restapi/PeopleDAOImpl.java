package restapi;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

@Component
public class PeopleDAOImpl implements PeopleDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void insert(People person) {
		if(person == null) return;
		String query = "INSERT INTO people (firstname, lastname) VALUES (?, ?)";
		try {
			jdbcTemplate.update(query, new Object[] { person.getFirstName(), person.getLastName() });
		} catch (DataAccessException ex) {
			// Log exception
		}
	}
	
	public People findById(int id) {
		People person = null;
		String query = "SELECT * FROM people WHERE id = ?";
		List<People> people = null;
		
		try {
			people = jdbcTemplate.query(query, new PeopleMapper(), new Object[] { id });
		} catch (DataAccessException ex) {
			// Log exception
		}
		
		if(people != null && !people.isEmpty())
			person = (People) people.get(0);
		
		return person;
	}
		
	public void changeFirstName(int id, String firstName) {
		String query = "UPDATE people SET FirstName = ? WHERE id = ?";
		try {
			jdbcTemplate.update(query, new Object[] { firstName, id });
		} catch(DataAccessException ex) {
			// Log exception
		}
	}
	
	public void changeLastName(int id, String lastName) {
		String query = "UPDATE people SET LastName = ? WHERE id = ?";
		try {
			jdbcTemplate.update(query, new Object[] { lastName, id });
		} catch(DataAccessException ex) {
			// Log exception
		}
	}
		
	public void deleteById(int id) {
		try{
			String deleteRel = "DELETE FROM relationships WHERE p_id1 = ? OR p_id2 = ?";
			jdbcTemplate.update(deleteRel, new Object[] { id, id });
			String deletePeople = "DELETE FROM people WHERE id = ?";
			jdbcTemplate.update(deletePeople, new Object[] { id });
		} catch (DataAccessException ex) {
			// Log exception
		}
	}
}
