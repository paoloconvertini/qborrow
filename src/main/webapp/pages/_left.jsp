<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="qs2" uri="/quix-strut2-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:url id="homeUrl" namespace="/" action="home" escapeAmp="false" includeParams="none">
	<s:param name="version">2</s:param>
</s:url>
<s:url id="oggettoUrl" namespace="/" action="oggetto" escapeAmp="false" includeParams="none">
	<s:param name="task">mainPage</s:param>
</s:url>
<s:url id="mieiOggettiUrl" namespace="/" action="oggetto" escapeAmp="false" includeParams="none">
	<s:param name="task">mieiOggetti</s:param>
</s:url>
<s:url id="prestitoUrl" namespace="/" action="prestito" escapeAmp="false" includeParams="none">
	<s:param name="task">mainPage</s:param>
</s:url>
<s:url id="soggettoUrl" namespace="/" action="soggetto" escapeAmp="false" includeParams="none">
	<s:param name="task">mainPage</s:param>
</s:url>
<s:url id="frmkUrl" namespace="/framework" action="admin" escapeAmp="false" includeParams="none">
	<s:param name="version">2</s:param>
</s:url>

<!-- Sidebar Menu -->
<ul class="framework-sidebar-menu">
	<li class="framework-sidebar-menu-element qtext-left <% if("Home".equals(request.getParameter("menuActive"))) { %>framework-sidebar-menu-element-active<% } %>">
		<a class="framework-sidebar-menu-element-link" href="<s:property value="#homeUrl" escape="false" />">
			<i class='fa fa-home fa-lg'></i><span style="display:inline-block;font-size:12px;">&nbsp;<s:text name="menu.home" /></span>
		</a>
	</li>
	<li class="framework-sidebar-menu-element qtext-left <% if("oggetto".equals(request.getParameter("menuActive"))) { %>framework-sidebar-menu-element-active<% } %>">
		<a class="framework-sidebar-menu-element-link" href="<s:property value="#oggettoUrl" escape="false" />">
			<i class='fa fa-dot-circle-o fa-lg'></i><span style="display:inline-block;font-size:12px;">&nbsp;oggetto</span>
		</a>
	</li>
		<li class="framework-sidebar-menu-element qtext-left <% if("oggetto".equals(request.getParameter("menuActive"))) { %>framework-sidebar-menu-element-active<% } %>">
		<a class="framework-sidebar-menu-element-link" href="<s:property value="#mieiOggettiUrl" escape="false" />">
			<i class='fa fa-dot-circle-o fa-lg'></i><span style="display:inline-block;font-size:12px;">&nbsp;Miei Oggetti</span>
		</a>
	</li>
	<li class="framework-sidebar-menu-element qtext-left <% if("prestito".equals(request.getParameter("menuActive"))) { %>framework-sidebar-menu-element-active<% } %>">
		<a class="framework-sidebar-menu-element-link" href="<s:property value="#prestitoUrl" escape="false" />">
			<i class='fa fa-dot-circle-o fa-lg'></i><span style="display:inline-block;font-size:12px;">&nbsp;prestito</span>
		</a>
	</li>
	<li class="framework-sidebar-menu-element qtext-left <% if("soggetto".equals(request.getParameter("menuActive"))) { %>framework-sidebar-menu-element-active<% } %>">
		<a class="framework-sidebar-menu-element-link" href="<s:property value="#soggettoUrl" escape="false" />">
			<i class='fa fa-dot-circle-o fa-lg'></i><span style="display:inline-block;font-size:12px;">&nbsp;soggetto</span>
		</a>
	</li>
	<li class="framework-sidebar-menu-element qtext-left <% if("Frmk".equals(request.getParameter("menuActive"))) { %>framework-sidebar-menu-element-active<% } %>">
		<a class="framework-sidebar-menu-element-link" href="<s:property value="#frmkUrl" escape="false" />">
			<i class='fa fa-wrench fa-lg'></i><span style="display:inline-block;font-size:12px;">&nbsp;Framework</span>
		</a>
	</li>
</ul>


