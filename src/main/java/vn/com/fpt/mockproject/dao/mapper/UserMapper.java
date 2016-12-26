package vn.com.fpt.mockproject.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import vn.com.fpt.mockproject.model.User;

public class UserMapper implements RowMapper<User> {
	public static final String BASE_SQL = //
            "Select *"//
                    + " from user ";

	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		String id = rs.getString("Id");
		String groupId = rs.getString("Group_Id");
		String firstName = rs.getString("First_Name");
		String lastName = rs.getString("Last_Name");
		String password = rs.getString("Password");
		return new User(id, groupId, firstName, lastName, password);
	}

}
