# 図書館蔵書管理システム 設計書

## 概要

本システムは、図書館の蔵書貸出業務をJavaで実装した学習用ポートフォリオである。
コマンドライン（CLI）から操作し、書籍の登録・利用者の登録・貸出・返却を管理する。

## クラス図

````mermaid
classDiagram
    class LibraryItem {
        <<abstract>>
        #id: String
        #title: String
        #status: ItemStatus
        +lend()
        +returnItem()
        +getLendingPeriodDays()* int
    }
    
    class Book {
        -isbn: String
        -author: String
        +getLendingPeriodDays() int
    }
    
    class User {
        -userId: String
        -name: String
        -userType: UserType
    }
    
    class Lending {
        -lendingId: String
        -user: User
        -item: LibraryItem
        -lendDate: LocalDate
        -dueDate: LocalDate
        -returnedDate: LocalDate
    }
    
    class ItemStatus {
        <<enumeration>>
        AVAILABLE
        LENT_OUT
    }
    
    class UserType {
        <<enumeration>>
        GENERAL
        STUDENT
        SENIOR
    }
    
    LibraryItem <|-- Book : 継承
    Lending o-- User : 借りた人
    Lending o-- LibraryItem : 借りた資料
    LibraryItem ..> ItemStatus : 使用
    User ..> UserType : 使用
````

## クラスの責務

### LibraryItem（資料：抽象クラス）
- 図書館で扱う「資料」全般の親クラス
- 全ての資料が共通で持つ項目（ID、タイトル、貸出状態）と振る舞い（貸出・返却処理）を定義
- 抽象メソッド `getLendingPeriodDays()` により、資料種別ごとに異なる貸出期間を子クラスで実装させる

### Book（本）
- LibraryItemを継承する具体クラス
- 本固有の項目（ISBN、著者）を保持する
- 貸出期間は14日間（getLendingPeriodDays()で実装）

### User（利用者）
- 図書館の利用者1人を表すクラス
- 利用者ID・氏名・利用者区分を保持する

### Lending（貸出記録）
- 「誰が、どの資料を、いつ借りて、いつ返したか」を記録するクラス
- UserとLibraryItem（種別問わず）を集約関係で参照する
- 返却日（returnedDate）が null の場合は「貸出中」、値がある場合は「返却済み」と判定する

### ItemStatus（列挙型）
- 資料の貸出状態を表す
- AVAILABLE（貸出可能）/ LENT_OUT（貸出中）

### UserType（列挙型）
- 利用者の区分を表す
- GENERAL（一般）/ STUDENT（学生）/ SENIOR（高齢者）
- 区分により貸出可能冊数や貸出期間を変える設計とする

## 設計上の判断

### 抽象クラスによる継承構造の採用
資料種別（本・雑誌・DVD）に共通する項目（ID、タイトル、状態）と処理（貸出・返却）を
LibraryItem 抽象クラスに集約し、各種別はこれを継承する設計とした。
理由：
- 共通コードの重複を排除（DRY原則）
- 種別固有の振る舞い（貸出期間など）を抽象メソッドで強制的に実装させ、実装漏れを防止
- 将来的な資料種別の追加に対して、親クラスを変更せずに対応可能

### enumの採用
状態や区分のように「決まった値しか取らない」項目は、String型ではなくenum型で表現した。
理由：
- コードが自己説明的になる（`BookStatus.LENT_OUT` は意味が明確）
- タイポをコンパイル時に検出できる
- 状態が増えた場合の拡張に対応しやすい

### 派生情報を持たない設計
Lendingクラスでは、`returnedDate` の有無で「貸出中／返却済」を判定する。
別途 `status` フィールドを持つと、`returnedDate` との不整合が発生するリスクがあるため、
判定ロジックに統一した。

### 個人情報の最小化
Userクラスのフィールドは「ID・氏名・区分」の3つに絞った。
住所・電話番号・メールアドレス等は今回のシステムでは不要と判断。