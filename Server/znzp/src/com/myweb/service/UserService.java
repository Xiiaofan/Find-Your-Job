package com.myweb.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
 
import com.myweb.dao.UserDao;
 
import com.myweb.domain.User;

public class UserService extends HttpServlet {
	HttpSession _session;

	HttpServletRequest _request;

	HttpServletResponse _response;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		_session = request.getSession();

		_request = request;

		_response = response;

		String action = "";

		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(ServletInputStream) _request.getInputStream(), "utf-8"));

			StringBuffer sb = new StringBuffer("");

			String temp;

			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}

			br.close();

		
			if (!sb.toString().equals("")) {

				JSONObject jsonObj = JSONObject.parseObject(sb.toString());

				action = jsonObj.getString("action");

				if (action.equals("reg")) {

					userReg(jsonObj.getString("user"));

				} else if (action.equals("init")) {

					viewUser(jsonObj.getString("userid"));

				} else if (action.equals("update")) {

					updateUser(jsonObj.getString("user"));

				} else if (action.equals("login")) {

					login(jsonObj.getString("user"));

				}
			}
		} catch (Exception e) {

			System.out.print(e.getMessage());
		}
	}

	public void login(String user) throws ServletException, IOException {

		String result = "";

		try {

			User userObj = JSONObject.parseObject(user, User.class);

			UserDao userDAO = new UserDao();

			User user1 = new User();

			user1 = userDAO.getUserByLoginNameAndPassword(userObj
					.getLoginname(), userObj.getLoginpsw(),userObj.getUsertype());

			if (user1 != null) {

				result = JSONObject.toJSONString(user1);

			}

		} catch (Exception e) {

			System.out.print(e.getMessage());

		} finally {
			
			_response.setCharacterEncoding("UTF-8");

			_response.setHeader("content-type", "text/html;charset=UTF-8");

			
			PrintWriter pw = _response.getWriter();

			pw.write(result);

			pw.flush();

			pw.close();
		}
	}

	// check user information
	public void viewUser(String userid) throws ServletException, IOException {

		String result = "";

		try {

			UserDao userDAO = new UserDao();

			User user = new User();

			user = userDAO.getUserById(userid);

			if (user != null) {

				result = JSONObject.toJSONString(user);

			}

		} catch (Exception e) {

			System.out.print(e.getMessage());

		} finally {
			
			_response.setCharacterEncoding("UTF-8");

			_response.setHeader("content-type", "text/html;charset=UTF-8");

		
			PrintWriter pw = _response.getWriter();

			pw.write(result);

			pw.flush();

			pw.close();
		}
	}

	// modify
	public void updateUser(String user) throws ServletException, IOException {

		String result = "";

		try {

			User userObj = JSONObject.parseObject(user, User.class);

			UserDao userDAO = new UserDao();

			userDAO.updateUser(userObj);

			result = "ok";

		} catch (Exception e) {

			System.out.print(e.getMessage());

		} finally {
			
			_response.setCharacterEncoding("UTF-8");

			_response.setHeader("content-type", "text/html;charset=UTF-8");

		
			PrintWriter pw = _response.getWriter();

			pw.write(result);

			pw.flush();

			pw.close();
		}
	}

	// user register
	public void userReg(String user) throws ServletException, IOException {

		String result = "";

		try {

			User userObj = JSONObject.parseObject(user, User.class);

			UserDao userDAO = new UserDao();

			int rel = userDAO.addUser(userObj);

			if (rel > -1) {

				result = "ok";

			}

		} catch (Exception e) {

			System.out.print(e.getMessage());

		} finally {
			
			_response.setCharacterEncoding("UTF-8");

			_response.setHeader("content-type", "text/html;charset=UTF-8");

		
			PrintWriter pw = _response.getWriter();

			pw.write(result);

			pw.flush();

			pw.close();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
