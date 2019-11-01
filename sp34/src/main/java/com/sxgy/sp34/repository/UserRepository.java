package com.sxgy.sp34.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sxgy.sp34.bean.User;

@Repository
public class UserRepository {
	@Resource
	private JdbcTemplate jdbcTemplate;
	public int insertUser() {
		String sql="insert into user(login_name,username,password) values "
				+"(?,?,?),(?,?,?),(?,?,?);";
		Object[] args=new Object[] {"swk","孙悟空","123","zbj","猪八戒","456","tsz","唐三藏","789"};
		return jdbcTemplate.update(sql, args);
	}
	public User selectByUsername(String username) {
		String sql="select * from user where name like ?";
		RowMapper<User> rowMapper=new BeanPropertyRowMapper<>(User.class);
		User user=jdbcTemplate.queryForObject(sql, new Object[] {username},rowMapper);
		return user;
	}
	public User selectById(int id) {
		String sql="select * from user where id=?";
		RowMapper<User> rowMapper=new BeanPropertyRowMapper<>(User.class);
		User user=jdbcTemplate.queryForObject(sql, new Object[] {id},rowMapper);
		return user;
	}
	public List<User> findAll(){
		String sql="select * from user";
		RowMapper<User> rowMapper=new BeanPropertyRowMapper<>(User.class);
		return jdbcTemplate.query(sql,rowMapper);
	}
	public void delete(final Integer id) {
		String sql="delete from user where id=?";
		jdbcTemplate.update(sql,new Object[] {id});
	}
	public void update(final User user) {
		String sql="update user set username=?,password=? where id=?";
		jdbcTemplate.update(sql,new Object[] {user.getUsername(),user.getPassword(),user.getId()});
	}
	public User insertGetKey(User user) {
		String sql="insert into user(username,login_name,password) values (?,?,?)";
		KeyHolder holder=new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getLoginName());
				ps.setString(3, user.getPassword());
				return ps;
			}
		},holder);
		int userid=holder.getKey().intValue();
		user.setId(userid);
		return user;
	}
}
