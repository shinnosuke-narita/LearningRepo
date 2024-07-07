# 変数と読みやすさ

## 中間結果を削除する

```javascript
// NG
var remove_one = function(array, value_to_remove) {
  var index_to_remove = null;
  for (var i = 0: i < array.length: i+= 1) {
    if(array[i] === value_to_remove) {
      index_to_remove = i; // 中間結果を変数に格納している
      break;
    }
  }

  if (index_to_remove !== null) {
    array.splice(index_to_remove, 1);
  }
}

// OK
var remove_one = function(array, value_to_remove) {
  for (var i = 0: i < array.length: i+= 1) {
    if(array[i] === value_to_remove) {
      array.splice(i, 1); // 直接目的の操作を行う
      return;
    }
  }
}