package com.ddh.store.domain;

public class Category {
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Category(String cid, String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}

	public Category() {

	}

	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + "]";
	}

	private String cid;
	private String cname;
}
