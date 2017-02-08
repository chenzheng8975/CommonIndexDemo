# CommonIndexDemo
1作为一名三年Android开发经验的程序员，今天和大家一起实战一款APP的首页功能，这个首页在我们平时接触中还是很常见的，虽然页面简单，但是里面涉及的功能点还是挺多的。代码如有不足的还望各路同仁指点一二。 
页面中使用的开发库： 
整个首页架构使用的是LRecyclerView，包含下拉刷新和自动加载功能

compile 'com.github.jdsjlzx:LRecyclerView:1.3.3'

无限循环轮播图使用的是convenientbanner，效果还是很顺畅的，还可以根据自己的需要修改过渡动画

compile 'com.bigkoo:convenientbanner:2.0.5'

图片加载使用的是glide图片库，里面的方法是自己封装的 
网络请求依赖是okhttp，使用的开源库okgo

compile 'com.lzy.net:okgo:2.1.4'

其他的还是九宫格图

compile 'com.lzy.widget:ninegridview:0.2.0'

自动注解butterknife库等等
