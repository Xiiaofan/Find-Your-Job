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
import com.myweb.dao.BoardDao;
import com.myweb.dao.NewsDao;
import com.myweb.dao.UserDao;
import com.myweb.domain.Admin;
import com.myweb.domain.Board;
import com.myweb.domain.News;
import com.myweb.domain.User;

public class NewsService extends HttpServlet {

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

				if (action.equals("list")) {

					listNews(jsonObj.getString("newstypeid"));

				} else if (action.equals("mylist")) {

					listMyNews(jsonObj.getString("userid"));

				} else if (action.equals("view")) {

					viewNews(jsonObj.getString("id"));

				} else if (action.equals("search")) {

					searchList(jsonObj.getString("title"));

				}else if (action.equals("add")) {

					addNews(jsonObj.getString("news"));

				}
				else if (action.equals("shoucang")) {

					shoucangNews(jsonObj.getString("newsid"),jsonObj.getString("userid"));

				}
				else if (action.equals("wodeshoucang")) {

					wodeshoucangList(jsonObj.getString("userid"));

				}
			}
		} catch (Exception e) {

			System.out.print(e.getMessage());

		}

	}
	
	public void shoucangNews(String newsid,String userid) throws ServletException, IOException {

		String result = "";
	
		try {

			 
			NewsDao newsDao = new NewsDao();

			newsDao.shoucangNews(newsid,userid);

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
	
	public void addNews(String news) throws ServletException, IOException {

		String result = "";
 	
		try {

			News newsObj = JSONObject.parseObject(news, News.class);

			NewsDao newsDao = new NewsDao();

			newsDao.addNews(newsObj);

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

			NewsDao newsDao = new NewsDao();

			List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();

			newsList = newsDao.getNewsListByTitle(title);

			if (newsList != null) {

				String json = JSONArray.toJSONString(newsList);

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

	public void listNews(String newstypeid) throws ServletException,
			IOException {

		String result = "";

		try {

			NewsDao newsDao = new NewsDao();

			List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();

			newsList = newsDao.getNewsListByTypeid(newstypeid);

			if (newsList != null) {

				String json = JSONArray.toJSONString(newsList);

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

	public void wodeshoucangList(String userid) throws ServletException, IOException {

		String result = "";

		try {

			NewsDao newsDao = new NewsDao();

			List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();

			newsList = newsDao.getShoucangList(userid);

			if (newsList != null) {

				String json = JSONArray.toJSONString(newsList);

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
	
	public void listMyNews(String userid) throws ServletException, IOException {

		String result = "";

		try {

			NewsDao newsDao = new NewsDao();

			List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();

			newsList = newsDao.getMyNewsList(userid);

			if (newsList != null) {

				String json = JSONArray.toJSONString(newsList);

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

	
	public void viewNews(String id) throws ServletException, IOException {

		String result = "";

		try {

			NewsDao newDAO = new NewsDao();

			News news = new News();

			news = newDAO.getNewsById(id);

			if (news != null) {
				result = JSONObject.toJSONString(news);
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
