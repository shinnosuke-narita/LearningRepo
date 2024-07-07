# 巨大な式を分割する

## 複雑な条件は逆を考えてみる
例）Rangeクラスに範囲が重なっているか判定する処理を実装したい
```kotlin
class Range(
  private val begin: Int,
  private val end: Int
) {
  fun overlapsWith(other: Range): Boolean {
    // 重なっている時の条件を網羅すると複雑になってしまう
    // return (begin >= other.begin && begin < other.end))
    //   || (end > other.begin && end <= other.end>)
    //   || (begin <= other.begin && end >= other.end)

    // 重ならない条件
    if (other.end <= begin) return false
    if (other.begin >= end) return false

    // それ以外は重なる
    return true
  }
}
```