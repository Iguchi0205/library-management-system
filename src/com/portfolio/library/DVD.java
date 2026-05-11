package com.portfolio.library;

public class DVD extends LibraryItem {
	private String director;
	private int durationMinutes;
	

	//コンストラクタ
	public DVD(String id, String title, String director, int durationMinutes) {
		super(id, title);
		this.director = director;//監督
		this.durationMinutes = durationMinutes;//上映時間
	}
	
	//DVD固有のゲッター
	public String getDirector() {
		return director;
	}
	public int getDurationMinutes() {
		return durationMinutes;
	}

	@Override
	public int getLendingPeriodDays() {
		return 5; //DVDの貸出期間は5日間
	}

}
