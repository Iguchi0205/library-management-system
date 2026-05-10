package com.portfolio.library;

public abstract class LibraryItem {
	protected String id;
	protected String title;
	protected ItemStatus status;
	
	public LibraryItem(String id, String title) {
		this.id = id;
		this.title = title;
		this.status = ItemStatus.AVAILABLE;
	}
	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public ItemStatus getStatus() {
		return status;
	}
	
	
	//ビジネスメソッド
	public void lend() {
		this.status = ItemStatus.LENT_OUT;
	}
	
	public void returnItem() {
		this.status = ItemStatus.AVAILABLE;
	}
	
	//抽象メソッド（子クラスで実装必須）
	public abstract int getLendingPeriodDays();
	
	

}
