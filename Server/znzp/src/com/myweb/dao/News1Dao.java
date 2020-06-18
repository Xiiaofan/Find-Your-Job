package com.myweb.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.myweb.domain.Admin;
import com.myweb.domain.News1;
import com.myweb.utils.DBManager;

public class News1Dao {
	 
	public int addNews1(News1 news1) {

		int id = 0;

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "insert into NEWS1 (TITLE,TYPEID,XINCHOU,IMGPATH,CONTENT,CREATEUSER,CREATETIME) values (?, ?, ?, ?, ?, ?, ?)";

		List<Object> params = new ArrayList<Object>();
 
		params.add(news1.getTitle());

		params.add(news1.getTypeid());
		
		params.add(news1.getXinchou());

		params.add(news1.getImgpath());

		params.add(news1.getContent());

		params.add(news1.getCreateuser());

		params.add(news1.getCreatetime());

		try {
			boolean flag = db.updateByPreparedStatement(sql, params);

			System.out.println(flag);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return id;
	}

	 
	public boolean updateNews1(News1 news1) {

		boolean flag = false;

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "update NEWS1  set  TITLE=?,TYPEID=?,XINCHOU=?,IMGPATH=?,CONTENT=? where id="
				+ String.valueOf(news1.getId());

		List<Object> params = new ArrayList<Object>();

		params.add(news1.getTitle());

		params.add(news1.getTypeid());
		
		params.add(news1.getXinchou());

		params.add(news1.getImgpath());

		params.add(news1.getContent());

		try {
			flag = db.updateByPreparedStatement(sql, params);

			System.out.println(flag);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
	}

	 
	public List<Map<String, Object>> getNews1ListByTypeid(String newstypeid) {

		DBManager db = new DBManager();

		db.getConnection();

		String sql2 = "select T1.ID,T1.TITLE,T1.TYPEID,T2.TYPENAME,T1.XINCHOU,T1.IMGPATH,T1.CONTENT,T1.CREATEUSER,T1.CREATETIME,T3.USERNAME AS CREATEUSERNAME from NEWS1 T1,NEWSTYPE T2,USER T3 WHERE T1.CREATEUSER=T3.ID  and T1.TYPEID=T2.ID AND T1.TYPEID='"
				+ newstypeid + "'  order by T1.CREATETIME desc";

		List<Map<String, Object>> list = null;

		try {
			list = db.findModeResult(sql2, null);

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return list;

	}

	public List<Map<String, Object>> getNews1ListByTitle(String title) {

		DBManager db = new DBManager();

		db.getConnection();

		String sql2 = "select T1.ID,T1.TITLE,T1.TYPEID,T2.TYPENAME,T1.XINCHOU,T1.IMGPATH,T1.CONTENT,T1.CREATETIME,T1.CREATEUSER,T3.USERNAME AS CREATEUSERNAME from NEWS1 T1,NEWSTYPE T2,USER T3 WHERE T1.CREATEUSER=T3.ID  and T1.TYPEID=T2.ID AND T1.TITLE like'%"
				+ title + "%'   order by T1.CREATETIME desc";

		List<Map<String, Object>> list = null;

		try {
			list = db.findModeResult(sql2, null);

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return list;

	}
	
 
	public List<Map<String, Object>> getNews1List() {

		DBManager db = new DBManager();

		db.getConnection();

		String sql2 = "select T1.ID,T1.TITLE,T1.TYPEID,T2.TYPENAME,T1.XINCHOU,T1.IMGPATH,T1.CONTENT,T1.CREATETIME,T1.CREATEUSER,T3.USERNAME from NEWS1 T1,NEWSTYPE T2,USER T3 WHERE T1.CREATEUSER=T3.ID AND T1.TYPEID=T2.ID   order by T1.CREATETIME desc";

		List<Map<String, Object>> list = null;

		try {
			list = db.findModeResult(sql2, null);

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return list;

	}
	
	public List<Map<String, Object>> getXygkNews1List() {

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
	

	public List<Map<String, Object>> getMyNews1List(String userid) {

		DBManager db = new DBManager();

		db.getConnection();

		String sql2 = "select T1.ID,T1.TITLE,T1.TYPEID,T2.TYPENAME,T1.XINCHOU,T1.IMGPATH,T1.CONTENT,T1.CREATETIME,T1.CREATEUSER,T3.USERNAME AS CREATEUSERNAME from NEWS1 T1,NEWSTYPE T2,USER T3 WHERE T1.CREATEUSER=T3.ID AND T1.TYPEID=T2.ID AND  T1.CREATEUSER='"
				+ userid + "'   order by T1.CREATETIME desc";

		List<Map<String, Object>> list = null;

		try {
			list = db.findModeResult(sql2, null);

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return list;

	}


	 
	public void delNews1ById(String id) {

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "delete from News1 where id=(?)";

		List<Object> list = new ArrayList<Object>();

		list.add(id);

		try {
			boolean flag = db.updateByPreparedStatement(sql, list);

		 

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	 
	public News1 getNews1ById(String id) {

		News1 news1 = null;

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "select t1.*,t2.USERNAME AS CREATEUSERNAME  from News1 t1,user t2 where t1.createuser=t2.id and  t1.id=(?)";

		List<Object> list = new ArrayList<Object>();

		list.add(id);

		try {
			news1 = (News1) db.findSimpleRefResult(sql, list, News1.class);

			if (news1 != null) {
				String sql1 = "select *  from Board where news1id='"
						+ news1.getId() + "'";

				List<Map<String, Object>> list1 = null;

				list1 = db.findModeResult(sql1, null);

				if (list1 != null) {
					news1.setReplynum(String.valueOf(list1.size()));
				} else {
					news1.setReplynum(String.valueOf(0));
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return news1;
	}
}
