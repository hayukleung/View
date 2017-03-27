# 自定义View系列知识点及API索引

> [欢迎关注我的简书博客](http://www.jianshu.com/users/2471a8f0e7a5/latest_articles)

## UsingView

### AppbarLayout
### CollapsingToolbarLayout
### CoordinatorLayout

## 自定义View

### BezierCurveView
![BezierCurveView](./img/BezierCurveView.gif)
- Bezier Curve
- DashPathEffect
- onTouchEvent

### BezierInfiniteLoadingView
![BezierInfiniteLoadingView](./img/BezierInfiniteLoadingView.gif)
- Bezier Curve

### CLInfiniteLoadingView
![CLInfiniteLoadingView](./img/CLInfiniteLoadingView.gif)
- Xfermode ATOP

### **CollapsibleView**
- base on RecyclerView

### GroupingTagView
标签

### **HandleView**
![HandleView](./img/HandleView.gif)

![Android坐标](./img/Coordinator.png)

- OnTouchListener

### IQiYiInfiniteLoadingView
![IQiYiInfiniteLoadingView](./img/IQiYiInfiniteLoadingView.gif)
- canvas.save
- canvas.restore

### MDLikeLinkTextView
also see [LinkString](https://gist.github.com/hayukleung/91e6ff978523c736a711f49524709b7a)
- Regex
- Pattern

### PatternView

### **ShyaringanView**
![ShyaringanView](./img/ShyaringanView.gif)
- canvas.save
- canvas.restore

### SonarInfiniteLoadingView
![SonarInfiniteLoadingView](./img/SonarInfiniteLoadingView.gif)

### RadarInfiniteLoadingView
![RadarInfiniteLoadingView](./img/RadarInfiniteLoadingView.gif)

### StampView
![StampView](./img/StampView.jpg)
- Xfermode

### ViewGroup
#### AutoSizingFlowLayout

标签分组算法

- 等数分组 - 顺序分组
```
 * ** ***** ****** ****** ******** 
 * ** ***** ****** ****** ******** 
 * ** ***** ****** ****** ******** 
 * *** ***** ****** ******* ******** 
 * **** ***** ****** ******* ******** 
 ** **** ***** ****** ******* 
 ** **** ***** ****** ******** 
```

- 等数分组 - 顺序逆序交替分组
```
 * ******** ** ******* ** ****** **** 
 * ******** ** ******* *** ****** **** 
 * ******** ** ******* *** ****** **** 
 * ******** ** ******* *** ****** **** 
 ** ******** ** ******* *** ***** 
 ** ******* ** ****** *** **** 
```

- 等数分组 - 根据所在行剩余容量顺序分组
```
 * *** **** ****** ******* ******** 
 * *** **** ****** ******* ******** 
 ** ** **** ****** ******* ******** 
 ** ** **** ****** ******* ******** 
 ** ** ***** ***** ******* ******* 
 * *** **** ****** ****** 
 * ** ***** ***** ******* 
```

- 等数分组 - 根据所在行剩余容量顺序逆序交替分组
```
 * ******** *** ****** *** ****** 
 * ******** *** ****** *** ****** 
 * ******** ** ******* **** ***** **** 
 * ******** ** ******* **** ***** **** 
 * ******** ** ******* **** ***** ***** 
 ** ******** ** ****** *** ****** ***** 
```

- 不等数分组 - 根据所在行剩余容量逆序分组
```
 ******** ******* ***** **** ** * 
 ******** ******* ***** ***** ** 
 ******** ******* ***** ***** ** 
 ******** ****** ****** **** *** 
 ******** ******* ****** *** *** 
 ******** ******* ***** **** ** * * 
 ******** ****** ****** **** ** * * 
```

- 不等数分组 - 根据所在行剩余容量顺序逆序交替分组
```
 ******** * ******* *** ***** **** 
 ******** ******* ****** *** **** 
 * * * ******** ****** ****** ***** 
 * ******** ** ******* *** ***** **** 
 * ******** ******* ****** **** **** 
 ******** * ** ** *** *** *** ***** **** 
```

#### **FlowLayout**

#### UVGView
show how to extend a ViewGroup

## Xfermode示例

### PorterDuffXfermode
[原文链接](http://blog.csdn.net/allen315410/article/details/45077165)

![PorterDuffXfermodeView](./img/PorterDuffXfermodeView.jpg)

![PorterDuffXfermode.png](./img/PorterDuffXfermode-Form.png)

### ColorFilter
[原文链接](http://www.cnblogs.com/tianzhijiexian/p/4297104.html)

- ColorMatrixColorFilter
- LightingColorFilter
- PorterDuffColorFilter