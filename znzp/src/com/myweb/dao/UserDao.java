package com.myweb.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.myweb.domain.Admin;
import com.myweb.domain.User;
import com.myweb.utils.DBManager;

public class UserDao {
	// add user
	public int addUser(User user) {

		int id = 0;

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "insert into User (LOGINNAME,LOGINPSW,USERNAME,USERTYPE,EMAIL,TEL) values (?, ?, ?, ?, ?, ?)";

		List<Object> params = new ArrayList<Object>();

		params.add(user.getLoginname());

		params.add(user.getLoginpsw());

		params.add(user.getUsername());

		params.add(user.getUsertype());

		params.add(user.getEmail());

		params.add(user.getTel());

		try {
			boolean flag = db.updateByPreparedStatement(sql, params);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return id;
	}

	 
	public List<Map<String, Object>> getUserList() {

		DBManager db = new DBManager();

		db.getConnection();

		String sql2 = "select * from USER  order by CREATETIME desc";

		List<Map<String, Object>> list = null;

		try {
			list = db.findModeResult(sql2, null);

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return list;

	}

	 
	public void delUserById(String id) {

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "delete from USER where id=(?)";

		List<Object> list = new ArrayList<Object>();

		list.add(id);

		try {
			boolean flag = db.updateByPreparedStatement(sql, list);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public User getUserById(String id) {

		User user = null;

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "select *  from user where id=(?)";

		List<Object> list = new ArrayList<Object>();

		list.add(id);

		try {
			user = (User) db.findSimpleRefResult(sql, list, User.class);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return user;
	}

	 
	public User getUserByLoginNameAndPassword(String loginname, String loginpsw,String usertype) {

		User user = null;

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "select * from USER where loginname=(?) and loginpsw=(?) and usertype=(?)";

		List<Object> list = new ArrayList<Object>();

		list.add(loginname);

		list.add(loginpsw);
		
		list.add(usertype);

		try {

			Map<String, Object> m = db.findSimpleResult(sql, list);

			if (m.size() > 0) {

				user = new User();

				user.setId(Integer.parseInt(m.get("ID").toString()));

				user.setLoginname(m.get("LOGINNAME").toString());

				user.setUsername(m.get("USERNAME").toString());
				
				user.setUsertype(m.get("USERTYPE").toString());

				System.out.println(m.get("LOGINNAME").toString());
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return user;
	}

	public boolean updateUser(User user) {

		boolean flag = false;

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "update user  set  loginpsw=?,username=?,email=?,tel=?  where id="
				+ String.valueOf(user.getId());

		List<Object> params = new ArrayList<Object>();

		params.add(user.getLoginpsw());

		params.add(user.getUsername());
	 
		params.add(user.getEmail());

		params.add(user.getTel());

		try {
			flag = db.updateByPreparedStatement(sql, params);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
	}
}
