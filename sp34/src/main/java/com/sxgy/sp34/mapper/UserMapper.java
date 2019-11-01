package com.sxgy.sp34.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sxgy.sp34.bean.User;

@Mapper
public interface UserMapper {
	@Insert("insert into user(login_name,username,password) values " + "(#{loginName},#{username},#{password})")
	public int insert(User user);

	@Insert("insert into user(login_name,username,password) values " + "(#{loginName},#{username},#{password})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	public void insertGetKey(User user);

	@Select("select * from user where username=#{username}")
	@ResultMap("userResult")
	public User selectByUsername(@Param("username") String username);

	@Select("select * from user")
	@Results(id = "userResult", value = { @Result(id = true, column = "id", property = "id"),
			@Result(column = "login_name", property = "loginName"), @Result(column = "username", property = "username"),
			@Result(column = "password", property = "password") })
	public List<User> findAll();
	
	@Delete("delete from user where id=#{id}")
	public void delete(final Integer id);
	
	@Select("select * from user where id=#{id}")
	@ResultMap("userResult")
	public User findById(int id) ;
	
	@Update("update user set login_name=#{loginName},username=#{username} where id=#{id}")
	public void update(final User user);
}
