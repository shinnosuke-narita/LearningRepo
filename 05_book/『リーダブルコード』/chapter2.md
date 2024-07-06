# 名前に情報を詰め込む

## 抽象的な名前を避ける
### NG: <span style="color: orange">getPage</span>
どこから取得するのか分からない

例えば、インターネットから取得するなら
**fetchPage**, **downloadPage**の方ががより良い

### NG: <span style="color: orange">result</span>

resultには情報が無い!

返り値の正しい情報を表すようにすべき

### NG: <span style="color: orange">i, j, k</span>

より良くするには下記のようにする。添え字の間違いを見つけやすくなる
``` c++
for (int club_i = 0; club_i < club.size(); club_i++) {
  for (int member_i = 0; member_i < member.size(); member_i++) {
    for (int user_i = 0; user_i < user.size(); user_i++) {
    }
  }
}
```

### NG: <span style="color: orange">--run_locally</span>

**用途**
- コマンドのオプション
- デバック用のログを出力するようになる

**駄目な点**
- ローカル環境で使用することしかわからない
- 誤った用途で利用してしまう

**改善**

--extra_logging

## 名前に情報を追加する

### 値の単位
ミリ秒の開始時間を表すなら:

**start -> start_ms**

### フォーマット
16進数を利用したIDなら
**id -> hex_id**

### 重要な属性を追加する
パスワードは暗号化することが必要なので、
暗号化前のパスワードには**plaintext_password**などと命名すると良い

## 名前の長さを決める
### 省略形の使いどころ
- string -> str <font style="color: green">**O**</font>
- document -> doc <font style="color: green">**O**</font>
- backend -> BE  <font style="color: red">**X**</font>

