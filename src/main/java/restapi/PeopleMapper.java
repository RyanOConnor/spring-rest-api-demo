package restapi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PeopleMapper implements RowMapper<People> {
	public People mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new People(rs.getInt("id"), 
		   	              rs.getString("firstname"), 
					      rs.getString("lastname"));
	}
}