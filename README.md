# 一个适配古诗词的简单ViewGroup组件
###介绍：书法字体，竖直排布，从右至左.
####This is a text vertical group demo !
###1.字体颜色
```Java
  public void setContentColor(int color) {
        this.groupColor = color;
    }
```
###2.字体大小
```Java
  public void setContentSize(int size) {
        this.groupSize = size;
    }
```
###3.内容和句式
####sentence：1.五言绝句；2.七言律诗；3.以逗号分隔的词曲；4.无格式内容(默认)
```Java
 public void setContent(String text, int sentence) {
        if (StringUtils.isEmpty(text) || sentence < SENTENCE_FIVE || sentence > SENTENCE_NORMAL) {
            return;
        }
        setSentence(text, sentence);
    }
```
###4.也可以在xml布局文件中进行设置属性
```Java
<com.example.xandone.textverticaldemo.view.TextVerticalGroup
        android:id="@+id/g2"
        android:layout_width="wrap_content"
        android:layout_height="300dp"
        android:layout_alignParentRight="true"
        android:layout_toRightOf="@+id/g1"
        app:group_color="#f00"
        app:group_content="绿蚁新醅酒，红泥小火炉。晚来天欲雪，能饮一杯无。"
        app:group_sentence="1"
        app:group_size="24sp" />
```

##效果图
![](https://github.com/xandone/TextVerticalDemo/blob/master/demo20160907.png)

1[](https://github.com/xandone/TextVerticalDemo/blob/master/demo2.png)
