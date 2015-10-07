下面是一段首页的页面定义文件，接下来我们通过这段定义来了解一下 Enterprise Connect 的页面自定义。
```
<page name="home" parent="commons/header">
	<position name="mainColumn">
		<fragment refer="topicFragment" view="Recent" cache="300">
			<title>最新讨论</title>
			<preferences>
				<limit>10</limit>
			</preferences>
		</fragment>
		<fragment refer="blogPostFragment" view="Recent" cache="300">
			<title>最新博文</title>
			<preferences>
				<limit>10</limit>
			</preferences>
		</fragment>
		<fragment refer="activityFragment" view="List" cache="60">
			<title>最新动态</title>
			<preferences>
				<limit>10</limit>
				<activityTypes>Profile,BlogPost,Topic,Reply</activityTypes>
			</preferences>
		</fragment>
	</position>
	<position name="rightColumn" width="300px" layout="fill">
		<fragment refer="aboutFragment" view="About" cache="300">
			<title>公告牌</title>
			<content><![CDATA[
			<ul>
				<li>官方QQ群开放了：616995</li>
				<li>本站@<a href="http://t.sina.com.cn/opensourceforce">新浪微博</a>，欢迎Follow</li>
			</ul>
			]]></content>
		</fragment>
		<fragment refer="activityFragment" view="Form">
			<title>话题</title>
			<preferences>
				<activityType>site-chat</activityType>
			</preferences>
		</fragment>
		<fragment refer="activityFragment" view="List">
			<title>最新话题</title>
			<preferences>
				<activityTypes>site-chat</activityTypes>
			</preferences>
		</fragment>
	</position>
</page>
```
页面定义文件是基于位置的定义，在上面的定义中定义了两个位置，mainColumn 和 rightColumn，当然 page 元素中还有一个 parent 元素所指定的父定义，注意：子定义可以覆盖父定义。

fragment 元素是一个组件的定义，组件由 Spring 容器管理。refer 表示引用 Spring 容器中的 ID，view 则是指调用 XXXFragment 中的 doXXXView() 方法，cache 用来指定当前这个 Fragment 是否缓存，及缓存时间。fragment 元素里的子元素可以理解为当前 fragment 的上下文配置。