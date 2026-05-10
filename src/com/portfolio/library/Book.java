package com.portfolio.library;

public class Book extends LibraryItem {
	//Book固有のフィールド
	private String isbn;
	private String author;
	
	//コンストラクタ
	public Book(String id, String title, String isbn, String author) {
		super(id, title);
		this.isbn = isbn;
		this.author =author;
	}
	
	//Book固有のゲッター
	public String getIsbn() {
		return isbn;
	}
	public String getAuthor() {
		return author;
	}
	//抽象メソッドの実装

	@Override
	public int getLendingPeriodDays() {
		return 14;//本の貸出期限は14日間
	}

}
