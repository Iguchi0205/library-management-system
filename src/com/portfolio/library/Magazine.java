package com.portfolio.library;

public class Magazine extends LibraryItem {
	
	//Magazine固有のフィールド
	private int issueNumber;//号数
	private String publisher;//出版社
	
	//コンストラクタ
	public Magazine(String id, String title, int issueNumber, String publisher) {
		super(id, title);
		this.issueNumber = issueNumber;
		this.publisher = publisher;
	}
	
	//Magazine固有のゲッター
	public int getIssueNumber() {
		return issueNumber;
	}
	
	public String getPublisher() {
		return publisher;
	}

	@Override
	public int getLendingPeriodDays() {
		return 7; //雑誌の貸出期間は7日間
	}

}
