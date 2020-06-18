package com.myweb.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.myweb.domain.Admin;
import com.myweb.domain.News;
import com.myweb.utils.DBManager;

public class NewsDao {

	public int addNews(News news) {

		int id = 0;

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "insert into NEWS ( TITLE,TYPEID,IMGPATH,CONTENT,CREATEUSER,CREATETIME) values ( ?, ?, ?, ?, ?, ?)";

		List<Object> params = new ArrayList<Object>();

		params.add(news.getTitle());

		params.add(news.getTypeid());

		params.add(news.getImgpath());

		params.add(news.getContent());

		params.add(news.getCreateuser());

		params.add(news.getCreatetime());

		try {
			boolean flag = db.updateByPreparedStatement(sql, params);

			System.out.println(flag);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return id;
	}

	public void shoucangNews(String newsid, String userid) {

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "insert into shoucang ( newsid,userid) values ( ?, ?)";

		List<Object> params = new ArrayList<Object>();

		params.add(newsid);

		params.add(userid);

		try {
			boolean flag = db.updateByPreparedStatement(sql, params);

			System.out.println(flag);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public boolean updateNews(News news) {

		boolean flag = false;

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "update NEWS  set  TITLE=?,TYPEID=?,IMGPATH=?,CONTENT=? where id="
				+ String.valueOf(news.getId());

		List<Object> params = new ArrayList<Object>();

		params.add(news.getTitle());

		params.add(news.getTypeid());

		params.add(news.getImgpath());

		params.add(news.getContent());

		try {
			flag = db.updateByPreparedStatement(sql, params);

			System.out.println(flag);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
	}

	// Query all announcements
	public List<Map<String, Object>> getNewsListByTypeid(String newstypeid) {

		DBManager db = new DBManager();

		db.getConnection();

		String sql2 = "select T1.ID,T1.TITLE,T1.TYPEID,T2.TYPENAME,T1.IMGPATH,T1.CONTENT,T1.CREATEUSER,T1.CREATETIME,T3.USERNAME  AS CREATEUSERNAME  from NEWS T1,NEWSTYPE T2,USER T3 WHERE T1.CREATEUSER=T3.ID  AND  T1.TYPEID=T2.ID AND T1.TYPEID='"
				+ newstypeid + "'  order by T1.CREATETIME desc";

		List<Map<String, Object>> list = null;

		try {
			list = db.findModeResult(sql2, null);

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return list;

	}

	public List<Map<String, Object>> getNewsListByTitle(String title) {

		DBManager db = new DBManager();

		db.getConnection();

		String sql2 = "select T1.ID,T1.TITLE,T1.TYPEID,T2.TYPENAME,T1.IMGPATH,T1.CONTENT,T1.CREATETIME,T1.CREATEUSER,T3.USERNAME  AS CREATEUSERNAME  from NEWS T1,NEWSTYPE T2,USER T3 WHERE T1.CREATEUSER=T3.ID  AND  T1.TYPEID=T2.ID AND T1.TITLE like'%"
				+ title + "%'   order by T1.CREATETIME desc";

		List<Map<String, Object>> list = null;

		try {
			list = db.findModeResult(sql2, null);

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return list;

	}

	public List<Map<String, Object>> getNewsList() {

		DBManager db = new DBManager();

		db.getConnection();

		String sql2 = "select T1.ID,T1.TITLE,T1.TYPEID,T2.TYPENAME,T1.IMGPATH,T1.CONTENT,T1.CREATETIME,T1.CREATEUSER,T3.USERNAME  AS CREATEUSERNAME from NEWS T1,NEWSTYPE T2,USER T3 WHERE T1.CREATEUSER=T3.ID  AND T1.TYPEID=T2.ID   order by T1.CREATETIME desc";

		List<Map<String, Object>> list = null;

		try {
			list = db.findModeResult(sql2, null);

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return list;

	}

	public List<Map<String, Object>> getShoucangList(String userid) {

		DBManager db = new DBManager();

		db.getConnection();

		String sql2 = "select T1.ID,T1.TITLE,T1.TYPEID,T2.TYPENAME,T1.IMGPATH,T1.CONTENT,T1.CREATETIME,T1.CREATEUSER,T3.USERNAME AS CREATEUSERNAME from NEWS T1,NEWSTYPE T2,USER T3 WHERE T1.CREATEUSER=T3.ID AND T1.TYPEID=T2.ID AND  T1.ID in (select t4.NEWSID from shoucang t4 where t4.userid='"
				+ userid + "')";

		List<Map<String, Object>> list = null;

		try {
			list = db.findModeResult(sql2, null);

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return list;

	}

	public List<Map<String, Object>> getMyNewsList(String userid) {

		DBManager db = new DBManager();

		db.getConnection();

		String sql2 = "select T1.ID,T1.TITLE,T1.TYPEID,T2.TYPENAME,T1.IMGPATH,T1.CONTENT,T1.CREATETIME,T1.CREATEUSER,T3.USERNAME AS CREATEUSERNAME from NEWS T1,NEWSTYPE T2,USER T3 WHERE T1.CREATEUSER=T3.ID AND T1.TYPEID=T2.ID AND  T1.CREATEUSER='"
				+ userid + "'   order by T1.CREATETIME desc";

		List<Map<String, Object>> list = null;

		try {
			list = db.findModeResult(sql2, null);

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return list;

	}

	public List<Map<String, Object>> getXygkNewsList() {

		DBManager db = new DBManager();

		db.getConnection();

		String sql2 = "select T1.ID,T1.TITLE,T1.TYPEID,T2.TYPENAME,T1.IMGPATH,T1.CONTENT,T1.CREATETIME,T1.CREATEUSER from NEWS T1,NEWSTYPE T2 WHERE T1.TYPEID=T2.ID  AND T1.TYPEID='-1'  order by T1.CREATETIME desc";

		List<Map<String, Object>> list = null;

		try {
			list = db.findModeResult(sql2, null);

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return list;

	}

	// delete
	public void delNewsById(String id) {

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "delete from News where id=(?)";

		List<Object> list = new ArrayList<Object>();

		list.add(id);

		try {
			boolean flag = db.updateByPreparedStatement(sql, list);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// check
	public News getNewsById(String id) {

		News news = null;

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "select t1.*,t2.USERNAME AS CREATEUSERNAME  from News t1,user t2 where t1.createuser=t2.id and  t1.id=(?)";

		List<Object> list = new ArrayList<Object>();

		list.add(id);

		try {
			news = (News) db.findSimpleRefResult(sql, list, News.class);

			if (news != null) {
				String sql1 = "select *  from Board where newsid='"
						+ news.getId() + "'";

				List<Map<String, Object>> list1 = null;

				list1 = db.findModeResult(sql1, null);

				if (list1 != null) {
					news.setReplynum(String.valueOf(list1.size()));
				} else {
					news.setReplynum(String.valueOf(0));
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return news;
	}
}
