<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="qs2" uri="/quix-strut2-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:url id="homeUrl" namespace="/" action="home" escapeAmp="false"
	includeParams="none">
</s:url>
<s:url id="logoutUrl" action="logout" includeParams="none"
	escapeAmp="false">
</s:url>

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


<div class="qrow">

	<nav class="qnavbar qnavbar-default">

		<!-- Brand and toggle get grouped for better mobile display -->

		<div class="qnavbar-header">
			<button type="button" class="qnavbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="qsr-only">Toggle navigation</span> <span
					class="qicon-bar"></span> <span class="qicon-bar"></span> <span
					class="qicon-bar"></span>
			</button>
			<a class="qnavbar-brand" href="#"><img
				onclick="document.location.href='<s:property value="#homeUrl"/>';"
				src="<qs2:imagesPath nome="/logo-qfrmk.png" version="2"/>"></a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="qcollapse qnavbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="qnav qnavbar-nav">
				<li><a class="framework-sidebar-menu-element-link"
					href="<s:property value="#homeUrl" escape="false" />"><s:text
								name="menu.home" /></span>
				</a></li>
				<li><a class="framework-sidebar-menu-element-link"
					href="<s:property value="#soggettoUrl" escape="false" />">Soggetto
				</a></li>
				<li><a class="framework-sidebar-menu-element-link"
					href="<s:property value="#oggettoUrl" escape="false" />">Oggetto
				</a></li>
				<li><a class="framework-sidebar-menu-element-link"
					href="<s:property value="#prestitoUrl" escape="false" />">Prestito
				</a></li>
				<li><a class="framework-sidebar-menu-element-link"
					href="<s:property value="#mieiOggettiUrl" escape="false" />">Miei
							Oggetti
				</a></li>
			</ul>
			<ul class="qnav qnavbar-nav qnavbar-right">
				<li>

						<div class="frameworkHeaderRightCellUserDiv qpull-right">
							<a href="#" class="qdropdown-toggle user-menu-link"
								data-toggle="qdropdown">
								<div style="display: inline-block; padding-left: 30px; padding-top: 10px;">
								
									<i class="fa fa-user" aria-hidden="true"></i><span class="hidden-xs"><s:property
											value="userContext.getUserText()" /></span>
								</div>
							</a>

							<ul class="qdropdown-menu user-dropdown-menu">
								<li class="user-header"><img
									src="<qs2:imagesPath nome="/user.jpg" version="2"/>"
									class="qimg-circle" alt="<s:text name="header.userimage"/>" />
									<p class="user-header-p">
										<br>
										<s:property value="userContext.getUserText()" />
										-
										<s:if test="userContext.isUserInRole('framework-admin')">
											<s:text name="header.adminRole" />
										</s:if>
										<s:else>
											<s:text name="header.userRole" />
										</s:else>
										<BR> <small><s:text name="header.membersince" />
											<s:property
												value="userContext.getUserCreateDate(getText('format.date'))" /></small>
									</p></li>
								<li class="user-footer">
									<div class="qpull-left">
										<a href=""
											onclick="javascript:window.open('/puma/mydetailedit.do?dn=<s:property value="userContext.realUserDn"/>', 'detailPuma', 'width=600,height=600,statusbar=no,toolbar=no,scrollbars=no,resizable=yes')"
											class="qbtn qbtn-default qbtn-flat"><s:text
												name="header.profile" /></a>
									</div>
									<div class="qpull-right">
										<a href="<s:property value="#logoutUrl" escape="false" />"
											class="qbtn qbtn-default qbtn-flat"><s:text
												name="header.signout" /></a>
									</div>
								</li>
							</ul>
						</div>
					
				</li>
			</ul>
		</div>
	</nav>
</div>