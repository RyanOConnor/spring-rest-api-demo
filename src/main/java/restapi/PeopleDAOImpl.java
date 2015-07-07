package restapi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Component
public class PeopleDAOImpl implements PeopleDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void insert(People person) {
		if(person == null) return;
		
		String query = "INSERT INTO people (ID, FirstName, LastName) VALUES (?, ?, ?)";
		
		try {
			jdbcTemplate.update(query, new Object[] { person.getId(),
												  person.getFirstName(), 
												  person.getLastName() });
		} catch (DataAccessException ex) {
			// Log exception
		}
	}
	
	public People findById(int id) {
		People person = null;
		String query = "SELECT * FROM people WHERE ID = ?";
		List<People> people = null;
		
		try {
			people = jdbcTemplate.query(query, new PeopleMapper(), new Object[] { id });
		} catch (DataAccessException ex) {
			// Log exception
		} finally {
			if(people != null && !people.isEmpty())
				person = (People) people.get(0);
		}
		
		return person;
	}
	
	public void changeFirstName(int id, String firstName) {
		String query = "UPDATE people SET FirstName = ? WHERE ID = ?";
		try {
			jdbcTemplate.update(query, new Object[] { firstName, id });
		} catch(DataAccessException ex) {
			// Log exception
		}
	}
	
	public void changeLastName(int id, String lastName) {
		String query = "UPDATE people SET LastName = ? WHERE ID = ?";
		try {
			jdbcTemplate.update(query, new Object[] { lastName, id });
		} catch(DataAccessException ex) {
			// Log exception
		}
	}
		
	public void deleteById(int id) {
		String query = "DELETE FROM people WHERE ID = ?";
		jdbcTemplate.update(query, new Object[] { id });
	}
	
	class PeopleMapper implements RowMapper<People> {
		
		public People mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new People(rs.getInt("id"), 
			   	              rs.getString("firstname"), 
						      rs.getString("lastname"));
		}
	}
}
