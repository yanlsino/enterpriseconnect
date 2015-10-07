## 1.1.0 版本起，Enterprise Connect 改名为 [FocusSNS](http://code.google.com/p/focus-sns/) 并启用新的项目主页 ##

Enterprise Connect is a themed social network software(SNS). It can build kinds of themed communities, enterprise communities and collaboration platforms.<br>
Enterprise Connect 是一个开源的主题社会化网络软件(SNS)，可以用于构建各类主题社区、企业社区、协作平台等。<br><br> Enterprise Connect is under <a href='http://www.opensource.org/licenses/agpl-v3'>AGPL3</a> license.Enterprise Connect采用<a href='http://www.opensource.org/licenses/agpl-v3'>AGPL3</a> 授权协议.<br>
<br>
普及下主题SNS概念: 进入主题SNS后将呈现的是一些特定主题，比如技术, 产品，商务，文化等。 人们基于这些兴趣主题进行社交，交流。  而论坛，wiki, 博客，文档等技术表现手段都是为更好的表现这些主题服务。 论坛，wiki等将不再出现在主菜单。典型的主题SNS网站如豆瓣，搜房网等.<br>
<br>
<h4>Enterprise Connect 的界面还有大量可改进的空间，故付费招募前端设计工程师，你写的代码将会开源发布，将会有更多的人使用到你的代码，要求了解Enterprise Connect, 熟悉前端开发技术，信奉开源理念优先，请联系steven.cheng@opensourceforce.org。</h4>

<h3>Features</h3>
<ol><li>Social Profile<br>
</li><li>Calendar<br>
</li><li>Micro Blog<br>
</li><li>Blog<br>
</li><li>Document<br>
</li><li>Discussion (Forum)<br>
</li><li>Team<br>
</li><li>Message<br>
</li><li>Multi Site Support<br>
</li><li>Multi Theme Support<br>
</li><li>Photo <img src='http://www.ipower.com/images/icons/icon-new-badge.png' />
</li><li>Question & Answer <img src='http://www.ipower.com/images/icons/icon-new-badge.png' /></li></ol>

<h3><a href='http://code.google.com/p/focus-sns/'>Focus SNS</a> 开发文档 ING...</h3>

<h4>Enterprise Connect QQ 讨论群  144832020 <a href='http://www.opensourceforce.org:8088/connect-web'>Enterprise Connect 1.0.3 Demo</a></h4>
<h4><a href='http://code.google.com/p/spring4me/'>Spring4Me</a> 从 Enterprise Connect 中提炼出的基于组件的编程模型</h4>


<blockquote>本周末将将发布 1.1.0 版本，同时项目将使用新的名称 <a href='http://code.google.com/p/focus-sns/'>Focus SNS</a>，接下来一段时间项目会逐步过度到 <a href='http://code.google.com/p/focus-sns/'>Focus SNS</a>， 1.1.0 版本的主要更新内容如下：</blockquote>

<ol><li>弃用 URLRewrite ，使用一个全局的 Route Controller，使得页面的流转更清晰<br>
</li><li>使用 <a href='http://code.google.com/p/spring4me/'>Spring4Me</a> 替代原有的 platform 部分代码，目前 <a href='http://code.google.com/p/spring4me/'>Spring4Me</a> 也已是 Google Code 的一个开源项目<br>
</li><li>优化表单提交跳转流程，并增强服务端的基于 Annotation 的表单验证<br>
</li><li>为了未来更容易的国际化，视图有 JSP 转为 FreeMarker</li></ol>

<blockquote>总的来说这是一次较大的更新，目的是为了简化二次开发，让更多的开发者可以很容易的参与进来！</blockquote>

<blockquote><hr /></blockquote>

<blockquote>Enterprise Connect 1.0.3 发布，主要包括如下更新：</blockquote>

<ol><li>浏览器兼容 firefox chrome ie7～8<br>
</li><li>微博优化，支持表情，图片，链接及图片的放大显示<br>
</li><li>新增 platform-social 工程，用来支持 sina 腾讯的 oauth 认证，及微博同步<br>
</li><li>将 Enterprise Connect 的 platform 部分代码进行了一次整理，并创建了一个新的项目 <a href='http://code.google.com/p/spring4me/'>Spring4Me</a></li></ol>

<blockquote><hr /></blockquote>

<blockquote>Enterprise Connect 1.0.2 发布，主要包括如下更新：</blockquote>

<ol><li>完善 Activity Stream，目前已支持 Profile Calendar Blog Document Discussion Team Knowledge   Gallery<br>
</li><li>完善邮件通知功能<br>
</li><li>解决上一版本中存在的问题<br>
</li><li>编写开发指南</li></ol>

<blockquote><hr /></blockquote>

<blockquote>Enterprise Connect 1.0.1 发布，关于Enterprise Connect的安装部署及开发，请阅读Wiki文档...</blockquote>

<blockquote>主要修改了1.0.0 版本中存在的一些Bug，并简化了后台的权限管理。</blockquote>

<blockquote>Enterprise Connect 1.0.1 版本在源代码根目录下的 <a href='http://enterpriseconnect.googlecode.com/svn/trunk/src/databases/'>src/databases</a> 下增加了一个 mysql 脚本，mysql 用户只需要</blockquote>

<blockquote>导入该脚本就可以使用最简单的 Enterprise Connect。</blockquote>

<img src='http://enterpriseconnect.googlecode.com/files/osforce-3.png' />

<img src='http://enterpriseconnect.googlecode.com/files/osforce-4.png' />