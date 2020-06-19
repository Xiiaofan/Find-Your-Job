package com.myweb.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myweb.dao.AdminDao;
import com.myweb.dao.News1Dao;
import com.myweb.dao.NewsDao;
import com.myweb.dao.UserDao;
import com.myweb.domain.Admin;
import com.myweb.domain.News;
import com.myweb.domain.News1;
import com.myweb.domain.User;

public class News1Service extends HttpServlet {

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
			/* load data */
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

				if (action.equals("list")) {

					listNews1(jsonObj.getString("newstypeid"));

				} else if (action.equals("mylist")) {

					listMyNews1(jsonObj.getString("userid"));

				} else if (action.equals("view")) {

					viewNews1(jsonObj.getString("id"));

				} else if (action.equals("search")) {

					searchList(jsonObj.getString("title"));

				}
				else if (action.equals("add")) {

					addNews1(jsonObj.getString("news1"));

				}
			}
		} catch (Exception e) {

			System.out.print(e.getMessage());

		}

	}
	

	public void listMyNews1(String userid) throws ServletException, IOException {

		String result = "";

		try {

			News1Dao newsDao = new News1Dao();

			List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();

			newsList = newsDao.getMyNews1List(userid);

			if (newsList != null) {

				String json = JSONArray.toJSONString(newsList);

				result = json.toString();

			}
		} catch (Exception e) {

			System.out.print(e.getMessage());

		} finally {
			/* return data */
			_response.setCharacterEncoding("UTF-8");

			_response.setHeader("content-type", "text/html;charset=UTF-8");

			PrintWriter pw = _response.getWriter();

			pw.write(result);

			pw.flush();

			pw.close();
		}

	}
	
	public void addNews1(String news1) throws ServletException, IOException {

		String result = "";

		 	
		try {

			News1 newsObj = JSONObject.parseObject(news1, News1.class);

			News1Dao newsDao = new News1Dao();

			newsDao.addNews1(newsObj);

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


	public void searchList(String title) throws ServletException, IOException {

		String result = "";

		try {

			News1Dao news1Dao = new News1Dao();

			List<Map<String, Object>> news1List = new ArrayList<Map<String, Object>>();

			news1List = news1Dao.getNews1ListByTitle(title);

			if (news1List != null) {

				String json = JSONArray.toJSONString(news1List);

				result = json.toString();

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

	 
	public void listNews1(String newstypeid) throws ServletException,
			IOException {

		String result = "";

		try {

			News1Dao news1Dao = new News1Dao();

			List<Map<String, Object>> news1List = new ArrayList<Map<String, Object>>();

			news1List = news1Dao.getNews1ListByTypeid(newstypeid);

			if (news1List != null) {

				String json = JSONArray.toJSONString(news1List);

				result = json.toString();

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

	// check
	public void viewNews1(String id) throws ServletException, IOException {

		String result = "";// return

		try {

			News1Dao newDAO = new News1Dao();

			News1 news1 = new News1();

			news1 = newDAO.getNews1ById(id);

			if (news1 != null) {
				result = JSONObject.toJSONString(news1);
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

}
