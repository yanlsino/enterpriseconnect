下面我们通过一个简单的例子来介绍一下基于 Enterprise Connect 的开发
```
@Component
public class AboutFragment {

	public String doAboutView(@Pref String pref1, 
			@Param String param1, FragmentContext context) {
		context.putRequestData("test", "test");
		return "commons/about";
	}
}
```
首先你需要创建一个 Java 类，例如上面的 AboutFragment，类名推荐 XXXFragment，用 Component Annotation 标注为 Spring 容器管理的组件。

```
注意：用 @Component 标注的Bean 在 Spring 容器中的 ID 为类名的首字母小写。
例如： AboutFragment 在 Spring 容器中的 ID 为 aboutFragment。
```

在 doAboutView() 方法中，用 @Pref 标注参数会根据参数名从 Fragment 定义的上下文中获取，FragmentContext 是一个比较特殊的对象，也会直接被注入。具体参看 @code CustomMethodParameterResolver 这个类。类似的还有 @Param 这个 Annotation，通过该 Annotation，可以注入 request parammeter 中的值。

```
注意: Fragment 中的 doAboutView() 必须是 doXXXView 格式，
     当 doAboutView 方法结束之后，必须返回一个逻辑视图名。
```

接下来在 /WEB-INF/views/ 创建一个 commons/about 页面，该页面的后缀可以是 .jsp .ftl .vm 等Spring MVC支持的任意视图。

完成上面的步骤之后，你已经完成了一个组件的开发，接下来你需要把这个组件显示在页面上，因此你需要将你开发的组件定义到页面定义文件中，下面是关于 AboutFragment 的配置。

```
<fragment refer="aboutFragment" view="About" cache="300">
	<title>公告牌</title>
	<content><![CDATA[
	<ul>
		<li>官方QQ群开放了：616995</li>
		<li>本站@<a href="http://t.sina.com.cn/opensourceforce">新浪微博</a>，欢迎Follow</li>
	</ul>
	]]></content>
        <preferences>
                <pref1>prefValue</pref1>
        </preferences>
</fragment>
```