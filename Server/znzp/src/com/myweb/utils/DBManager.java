package com.myweb.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DBManager {

	/**
	 * @param args
	 */
	static String driver;
	static String url;
	static String username;
	static String password;
	private Connection connection;
	private PreparedStatement pstmt;
	private ResultSet resultSet;

	public DBManager() {
		InputStream in = DBManager.class.getClassLoader().getResourceAsStream(
				"db.properties");

		Properties pro = new Properties();

		try {
			pro.load(in);

		} catch (IOException e) {

			e.printStackTrace();
		}

		driver = pro.getProperty("driver");

		url = pro.getProperty("url");

		username = pro.getProperty("username");

		password = pro.getProperty("password");

		try {
			Class.forName(driver);

			System.out.println("数据库连接成功！");

		} catch (Exception e) {

		}
	}

	/**
	 * connect with mysql
	 * 
	 * @return
	 */
	public Connection getConnection() {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * max ID
	 * 
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public int getMaxId(String tableName) {
		Statement state = null;
		ResultSet rs = null;
		int maxId = 0;
		try {
			state = connection.createStatement();
			String sql = "select max(id) maxId from " + tableName;
			rs = state.executeQuery(sql);
			
			if (rs.next()) {
				maxId = rs.getInt("maxId");
			}
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

		return ++maxId;
	}

	/**
	 * add delete edit
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean updateByPreparedStatement(String sql, List<Object> params)
			throws SQLException {
		boolean flag = false;
		int result = -1;
		pstmt = connection.prepareStatement(sql);
		int index = 1;
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		result = pstmt.executeUpdate();
		flag = result > 0 ? true : false;
		return flag;
	}

	/**
	 * check
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> findSimpleResult(String sql, List<Object> params)
			throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();// return result
		ResultSetMetaData metaData = resultSet.getMetaData();
		int col_len = metaData.getColumnCount();
		while (resultSet.next()) {
			for (int i = 0; i < col_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
		}
		return map;
	}

	/**
	 * check
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findModeResult(String sql,
			List<Object> params) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}

		return list;
	}

	/**
	 * Query single record by reflection mechanism
	 * 
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public <T> T findSimpleRefResult(String sql, List<Object> params,
			Class<T> cls) throws Exception {
		T resultObject = null;
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			// Create an instance through reflection mechanism
			resultObject = cls.newInstance();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				int type = metaData.getColumnType(i + 1);
				if (cols_value == null) {
					if (type == Types.INTEGER) {
						cols_value = 0;
					} else {
						cols_value = "";
					}
				}
				Field field = cls.getDeclaredField(cols_name.toLowerCase());
				field.setAccessible(true); // Open access to JavaBeans
				if (type == Types.TIMESTAMP) {
					 field.set(resultObject, String.valueOf(cols_value));
				} else {
				   field.set(resultObject, cols_value);
				}
			}
		}
		return resultObject;

	}

	/**
	 * Query multiple records through reflection mechanism
	 * 
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findMoreRefResult(String sql, List<Object> params,
			Class<T> cls) throws Exception {
		List<T> list = new ArrayList<T>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			
			T resultObject = cls.newInstance();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				Field field = cls.getDeclaredField(cols_name);
				field.setAccessible(true); 
				field.set(resultObject, cols_value);
			}
			list.add(resultObject);
		}
		return list;
	}

	/**
	 * Release database connection
	 */
	public void releaseConn() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		// JdbcUtils jdbcUtils = new JdbcUtils();
		// jdbcUtils.getConnection();

		/******************* add *********************/
		/*
		 * String sql =
		 * "insert into userinfo (username, pswd) values (?, ?), (?, ?), (?, ?)"
		 * ; List<Object> params = new ArrayList<Object>(); params.add("小明");
		 * params.add("123xiaoming"); params.add("Tom"); params.add("zhangsan");
		 * params.add("Jury"); params.add("lisi000"); try { boolean flag =
		 * jdbcUtils.updateByPreparedStatement(sql, params);
		 * System.out.println(flag); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		/******************* delete *********************/
		// 
		/*
		 * String sql = "delete from userinfo where username = ?"; List<Object>
		 * params = new ArrayList<Object>(); params.add("小明"); boolean flag =
		 * jdbcUtils.updateByPreparedStatement(sql, params);
		 */

		/******************* edit *********************/
		// 
		/*
		 * String sql = "update userinfo set pswd = ? where username = ? ";
		 * List<Object> params = new ArrayList<Object>();
		 * params.add("lisi88888"); params.add("李四"); boolean flag =
		 * jdbcUtils.updateByPreparedStatement(sql, params);
		 * System.out.println(flag);
		 */

		/******************* check *********************/
		// 
		/*
		 * String sql2 = "select * from userinfo "; List<Map<String, Object>>
		 * list = jdbcUtils.findModeResult(sql2, null);
		 * System.out.println(list);
		 */

		// 
		/*
		 * String sql = "select * from userinfo where username = ? ";
		 * List<Object> params = new ArrayList<Object>(); params.add("李四");
		 * UserInfo userInfo; try { userInfo =
		 * jdbcUtils.findSimpleRefResult(sql, params, UserInfo.class);
		 * System.out.print(userInfo); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } Class clcs =
		 * UserInfo.class; try { Object obj = clcs.newInstance(); Method f =
		 * clcs.getDeclaredMethod("setUsername", String.class); f.invoke(obj,
		 * "yan123"); Method f2 = clcs.getDeclaredMethod("getUsername", null);
		 * Object name = f2.invoke(obj, null); System.out.println("反射得到的名字 = " +
		 * name);
		 * 
		 * } catch (InstantiationException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } catch (IllegalAccessException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (NoSuchMethodException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (SecurityException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch
		 * (IllegalArgumentException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (InvocationTargetException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

	}
}
