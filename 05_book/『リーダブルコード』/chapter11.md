# 1度に１つのことを
## タスクは小さくできる
例）投票ボタンのクリック処理の実装<br>
- "" -> 0
- "Up" -> +1
- "Down" -> -1

--投票の一例--<br>
投票は変えられるが、前の投票を考慮して新しくカウントしなければならない
1. "" -> total count = 5
2. "Up" -> total count = 6
3. "Down" -> total count = **4**
---
### NG 実装
下記の2つのタスクを同時に行っている
- 前投票の打ち消し
- 新しい投票の反映
```kotlin
fun onChanged(oldVote:String, newVote: String) {
  var score = getScore()
  if (newVote != oldVote) {
    if (newVote == "Up") {
      score += if (old_vote == "Down") 2 else 1
    } else if (newVote == "Down") {
      score -= if (old_vote == "Up") 2 else 1
    } else {
      score -= if (old_vote == "Up") -1 else 1
    }
  }

  setScore(score)
}
```

### OK 実装
voteの値によってスコアを返す小さな関数を定義するべき
```kotlin
fun getScoreBy(vote: String): Int {
  return when(vote) {
    "Up" -> 1
    "Down" -> -1
    else -> 0
  }
}

fun onChanged(oldVote:String, newVote: String) {
  var score = getScore()
  score -= getScoreBy(oldVote) // 古い値を削除する
  score += getScoreBy(newVote) // 新しい値を追加する

  setScore(score)
}
```
