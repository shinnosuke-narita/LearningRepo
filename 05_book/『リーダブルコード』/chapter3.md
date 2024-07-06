# 誤解されない名前

> [!NOTE]より良いコードとは、誤解されないコードであること
誤解されないコードを書かないためには、積極的に誤解されうるパターンを考えること

## filter
```
database.all_objects.filter("year <= 2011")
```
上記コードの返り値は次の２つが考えられる
- "year <= 2011"のオブジェクト
- "year <= 2011"以外のオブジェクト

前者なら**select**、後者なら**exclude**にするべき

## Clip
``` python
# textの最後を切り落として、「...」をつける
def clip(text, length):
...
```

### 改善 clip
- 最後からlength文字削除する(-> **remove**)
- 最大length文字切り詰める(-> **truncate**)

### 改善 length
文字数なら**length -> max_chars**

## limit
例）ショッピングカートには10点しか入れられない

```kotlin
// 境界値が分かりにくい
const val CART_TOO_BIG_LIMIT = 10

// 改善
const val MAX_ITEMS_IN_CART = 10
```

## 範囲の指定
```kotlin
// 印字するのは[start~stop] or [startとstop]?
fun integer_range(start: Int, stop: Int)


// 改善 最後を含む
fun integer_range(first: Int, last: Int)

// 改善 最後を含まない
fun integer_range(begin: Int, end: Int)
```