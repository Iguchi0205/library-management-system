package com.portfolio.library;

public class HelloWorld {

	public static void main(String[] args) {
		  // 3種類の資料を作成
        Book book = new Book(
            "B001",
            "吾輩は猫である",
            "978-4-10-101001-0",
            "夏目漱石"
        );
        
        Magazine magazine = new Magazine(
            "M001",
            "週刊プログラミング",
            42,
            "技術評論社"
        );
        
        DVD dvd = new DVD(
            "D001",
            "となりのトトロ",
            "宮崎駿",
            86
        );
        
        // 3種類を一つの配列にまとめる（ここがポリモーフィズム！）
        LibraryItem[] items = { book, magazine, dvd };
        
        // 一括で情報表示
        System.out.println("===== 蔵書一覧 =====");
        for (LibraryItem item : items) {
            System.out.println("【" + item.getId() + "】" 
                + item.getTitle() 
                + "（貸出期間: " + item.getLendingPeriodDays() + "日間）"
                + " - 状態: " + item.getStatus());
        }
        
        // 一括で貸出処理
        System.out.println("\n===== 一括貸出処理 =====");
        for (LibraryItem item : items) {
            item.lend();
            System.out.println(item.getTitle() + " を貸し出しました");
        }
        
        // 貸出後の状態確認
        System.out.println("\n===== 貸出後の状態 =====");
        for (LibraryItem item : items) {
            System.out.println(item.getTitle() + ": " + item.getStatus());
        }
    }
	}


