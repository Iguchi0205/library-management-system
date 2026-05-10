package com.portfolio.library;

public class HelloWorld {

	public static void main(String[] args) {
		//本を一冊作る
		Book book1 = new Book(
				"B001",
				"吾輩は猫である",
				"978-4-10-101001-0",
				"夏目漱石"
				);
		
		//本の情報を表示
		System.out.println("＝＝＝本の情報＝＝＝");
		System.out.println("ID:" + book1.getId());
		System.out.println("タイトル：" + book1.getTitle());
		System.out.println("ISBN:" + book1.getIsbn());
		System.out.println("著者" + book1.getAuthor());
		System.out.println("状態" + book1.getStatus());
		System.out.println("貸出期間" + book1.getLendingPeriodDays() + "日間");
		
		//貸し出してみる
		System.out.println("\n===貸出処理＝＝＝");
		book1.lend();
		System.out.println("貸出後の状態：　" + book1.getStatus());
		
		//返却してみる
		System.out.println("\n===返却処理＝＝＝");
		book1.returnItem();
		System.out.println("返却後の状態：　" + book1.getStatus());
	}

}
