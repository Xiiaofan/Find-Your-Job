package com.myweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import com.myweb.dao.News1Dao;
import com.myweb.dao.NewsTypeDao;
 
import com.myweb.domain.News1;

public class News1Servlet extends HttpServlet {

	HttpSession _session;

	HttpServletRequest _request;

	HttpServletResponse _response;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		_session = request.getSession();

		_request = request;

		_response = response;

		String action = "";

		action = request.getParameter("action").toString();

		if (action.equals("addInit")) {

			addNews1Init();

		} else if (action.equals("addxygkInit")) {

			addxygkInit();

		} else if (action.equals("add")) {

			addNews1();

		} else if (action.equals("addxyjj")) {

			addxyxw();

		}  else if (action.equals("list")) {

			listNews1();

		}else if (action.equals("xygklist")) {

			xygklistNews1();

		} else if (action.equals("edit")) {

			editNews1();

		}
		else if (action.equals("editxyxw")) {

			editxyxw();

		}else if (action.equals("editSave")) {

			editSaveNews1();

		} else if (action.equals("editXyxwSave")) {

			editSaveXyxw();

		} else if (action.equals("delete")) {
			deleteNews1();
		}
		else if (action.equals("deletexygk")) {
			deleteXygk();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	public void addNews1Init() throws ServletException, IOException {

		NewsTypeDao dao = new NewsTypeDao();

		try {
			List<Map<String, Object>> list = dao.getNewsTypeList();

			_request.setAttribute("news1TypeList", list);

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		_request.getRequestDispatcher("/admin/news1_Add.jsp").forward(_request,
				_response);

	}
	
	public void addxygkInit() throws ServletException, IOException {
 
		_request.getRequestDispatcher("/admin/xygk_Add.jsp").forward(_request,
				_response);

	}

	 
	public void deleteXygk() throws ServletException, IOException {
		String id = _request.getParameter("id");

		News1Dao dao = new News1Dao();

		try {
			dao.delNews1ById(id);

			_request.setAttribute("alertNote", "1");

		} catch (Exception ex) {

			_request.setAttribute("alertNote", "0");
		}

		_request.getRequestDispatcher("News1Servlet?action=xygklist").forward(
				_request, _response);
	}
	
	public void deleteNews1() throws ServletException, IOException {
		String id = _request.getParameter("id");

		News1Dao dao = new News1Dao();

		try {
			dao.delNews1ById(id);

			_request.setAttribute("alertNote", "1");

		} catch (Exception ex) {

			_request.setAttribute("alertNote", "0");
		}

		_request.getRequestDispatcher("News1Servlet?action=list").forward(
				_request, _response);
	}

	// add
	public void addNews1() throws ServletException, IOException {

		String title = _request.getParameter("title");

		String news1typeid = _request.getParameter("news1typeid");

		String imgpath = _request.getParameter("imgpath");

		String content = _request.getParameter("content");

		News1 news1 = new News1();

		news1.setTitle(title);

		news1.setTypeid(news1typeid);

		news1.setImgpath(imgpath);

		news1.setContent(content);

		news1.setCreateuser("管理员");

		Date date = new Date();

		news1.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(date));

		News1Dao dao = new News1Dao();

		try {
			dao.addNews1(news1);

			_request.setAttribute("alertNote", "1");

		} catch (Exception ex) {

			_request.setAttribute("alertNote", "0");
		}
		_request.getRequestDispatcher("/admin/news1_Add.jsp").forward(_request,
				_response);

	}
	public void addxyxw() throws ServletException, IOException {

		String title = _request.getParameter("title");

		String news1typeid = _request.getParameter("news1typeid");

		String imgpath = _request.getParameter("imgpath");

		String content = _request.getParameter("content");

		News1 news1 = new News1();

		news1.setTitle(title);

		news1.setTypeid(news1typeid);

		news1.setImgpath(imgpath);

		news1.setContent(content);

		news1.setCreateuser("管理员");

		Date date = new Date();

		news1.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(date));

		News1Dao dao = new News1Dao();

		try {
			dao.addNews1(news1);

			_request.setAttribute("alertNote", "1");

		} catch (Exception ex) {

			_request.setAttribute("alertNote", "0");
		}
		_request.getRequestDispatcher("/admin/xygk_Add.jsp").forward(_request,
				_response);

	}

	// edit
	public void editNews1() throws ServletException, IOException {

		String id = _request.getParameter("id");

		News1Dao dao = new News1Dao();

		News1 news1 = null;

		try {
			news1 = dao.getNews1ById(id);

			_request.setAttribute("news1", news1);

			NewsTypeDao dao1 = new NewsTypeDao();

			List<Map<String, Object>> list = dao1.getNewsTypeList();

			_request.setAttribute("news1TypeList", list);

		} catch (Exception ex) {

		}

		_request.getRequestDispatcher("/admin/news1_Edit.jsp").forward(_request,
				_response);
	}
	
	public void editxyxw() throws ServletException, IOException {

		String id = _request.getParameter("id");

		News1Dao dao = new News1Dao();

		News1 news1 = null;

		try {
			news1 = dao.getNews1ById(id);

			_request.setAttribute("news1", news1);

			NewsTypeDao dao1 = new NewsTypeDao();

			List<Map<String, Object>> list = dao1.getNewsTypeList();

			_request.setAttribute("news1TypeList", list);

		} catch (Exception ex) {

		}

		_request.getRequestDispatcher("/admin/xygk_Edit.jsp").forward(_request,
				_response);
	}
	 
	
	 
	public void editSaveXyxw() throws ServletException, IOException {

		String title = _request.getParameter("title");

		String news1typeid = _request.getParameter("news1typeid");

		String imgpath = _request.getParameter("imgpath");

		String content = _request.getParameter("content");

		String id = _request.getParameter("id");

		News1Dao dao = new News1Dao();

		News1 news1 = null;

		try {
			news1 = dao.getNews1ById(id);

			news1.setTitle(title);

			news1.setTypeid(news1typeid);

			news1.setImgpath(imgpath);

			news1.setContent(content);

			boolean flag = dao.updateNews1(news1);

			_request.setAttribute("news1", news1);

			NewsTypeDao dao1 = new NewsTypeDao();

			List<Map<String, Object>> list = dao1.getNewsTypeList();

			_request.setAttribute("news1TypeList", list);

			if (flag) {
				_request.setAttribute("alertNote", "1");
			} else {
				_request.setAttribute("alertNote", "0");
			}
		} catch (Exception ex) {
			_request.setAttribute("alertNote", "0");
		}

		_request.getRequestDispatcher("/admin/xygk_Edit.jsp").forward(_request,
				_response);
	}


	// save
	public void editSaveNews1() throws ServletException, IOException {

		String title = _request.getParameter("title");

		String news1typeid = _request.getParameter("news1typeid");

		String imgpath = _request.getParameter("imgpath");

		String content = _request.getParameter("content");

		String id = _request.getParameter("id");

		News1Dao dao = new News1Dao();

		News1 news1 = null;

		try {
			news1 = dao.getNews1ById(id);

			news1.setTitle(title);

			news1.setTypeid(news1typeid);

			news1.setImgpath(imgpath);

			news1.setContent(content);

			boolean flag = dao.updateNews1(news1);

			_request.setAttribute("news1", news1);

			NewsTypeDao dao1 = new NewsTypeDao();

			List<Map<String, Object>> list = dao1.getNewsTypeList();

			_request.setAttribute("news1TypeList", list);

			if (flag) {
				_request.setAttribute("alertNote", "1");
			} else {
				_request.setAttribute("alertNote", "0");
			}
		} catch (Exception ex) {
			_request.setAttribute("alertNote", "0");
		}

		_request.getRequestDispatcher("/admin/news1_Edit.jsp").forward(_request,
				_response);
	}

	// list
	public void listNews1() throws ServletException, IOException {

		News1Dao dao = new News1Dao();

		try {

			List<Map<String, Object>> list = dao.getNews1List();

			_request.setAttribute("news1List", list);

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		_request.getRequestDispatcher("/admin/news1_List.jsp").forward(_request,
				_response);

	}
	 
	public void xygklistNews1() throws ServletException, IOException {

		News1Dao dao = new News1Dao();

		try {

			List<Map<String, Object>> list = dao.getXygkNews1List();

			_request.setAttribute("news1List", list);

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		_request.getRequestDispatcher("/admin/xygk_List.jsp").forward(_request,
				_response);

	}
}
