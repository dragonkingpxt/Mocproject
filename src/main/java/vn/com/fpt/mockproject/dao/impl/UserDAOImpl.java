package vn.com.fpt.mockproject.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.fpt.mockproject.dao.UserDAO;
import vn.com.fpt.mockproject.dao.mapper.UserMapper;
import vn.com.fpt.mockproject.model.User;

@Service
@Repository
@Transactional
public class UserDAOImpl extends JdbcDaoSupport implements UserDAO {

	@Autowired
	public UserDAOImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public User getUser(String userId) {
		// TODO Auto-generated method stub
		try{
		String sql = "SELECT * " + "FROM user " + "WHERE Id = ? ";
		Object[] params = new Object[] {userId};
		UserMapper mapper = new UserMapper();
		User user = this.getJdbcTemplate().queryForObject(sql, params, mapper);
		return user;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Khoong ton tai user: "+userId);
			return null;
		}
	}

	public List<User> getAllUser(int start, int end) {
		// TODO Auto-generated method stub
		String sql = UserMapper.BASE_SQL;
		UserMapper mapper = new UserMapper();
		Object[] params = new Object[] {};
		List<User> list = this.getJdbcTemplate().query(sql, params, mapper);
		return list;
	}

	public void insertUser(User user) {
		// TODO Auto-generated method stub
		try{
		String sql = "INSERT INTO user " + "VALUES(?, ?, ?, ?, ?)";
		Object[] params = new Object[] { user.getId(), user.getGroupId(), user.getFirstName(), user.getLastName(),
				user.getPassword() };
		this.getJdbcTemplate().update(sql, params);}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Khon the insert UserDAOImpl");
		}
	}

	public int getSize() {
		// TODO Auto-generated method stub
		String sql = "SELECT count(id) " 
				   + "FROM user ";
		Object[] params = new Object[] {};
		int size = this.getJdbcTemplate().queryForObject(sql, params, Integer.class);
		return size;
	}

	public String getUserGroup(String userId) {
		// TODO Auto-generated method stub
		String sql = "SELECT Group_Id " 
				   + "FROM user "
				   + "WHERE id = ? ";
		Object[] params = new Object[] {userId};
		String size = this.getJdbcTemplate().queryForObject(sql, params, String.class);
		return size;
	}

}
