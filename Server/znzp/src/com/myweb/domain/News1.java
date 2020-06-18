package com.myweb.domain;

public class News1 {
	private static final long serialVersionUID = 1L;

	private int id;
	private String title;
	private String typeid;
	private String typename;
	private String xinchou;
	private String imgpath;
	private String content;
	private String replynum;
	private String createuser;
	private String createusername;
	public String getCreateusername() {
		return createusername;
	}

	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}

	private String createtime;

	public int getId() {
		return id;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getTypename() {
		return typename;
	}

	public String getXinchou() {
		return xinchou;
	}

	public void setXinchou(String xinchou) {
		this.xinchou = xinchou;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getReplynum() {
		return replynum;
	}

	public void setReplynum(String replynum) {
		this.replynum = replynum;
	}
}
