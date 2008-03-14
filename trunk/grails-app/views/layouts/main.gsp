<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"><html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"><head>    <title><g:layoutTitle default="Grails"/></title>    <link rel="stylesheet" href="${createLinkTo(dir: 'css', file: 'sd-stylesheet.css')}" type="text/css"/>    <link rel="shortcut icon" href="${createLinkTo(dir: 'images', file: 'favicon.ico')}" type="image/x-icon"/>    <g:javascript library="application"/>    <g:layoutHead/></head><body><div id="container">    <div id="header">        <div class="lang">            <g:langFlag lang="en" params="${params}"/>            <g:langFlag lang="nb" params="${params}"/>        </div>        <h1>Huts</h1>    </div>    <ul id="nav">        <li><g:link controller="hut" action="list"><g:message code="layout.menu.home"/></g:link></li>        <g:if test="${!session.userId}">            <li><g:link controller="person" action="login"><g:message code="layout.menu.login"/></g:link></li>            <li><g:link controller="person" action="register"><g:message code="layout.menu.register"/></g:link></li>            <li><g:link controller="person" action="forgotten"><g:message code="layout.menu.remind"/></g:link></li>        </g:if>        <g:else>            <g:isAdmin userId="${session.userId}">                <li><g:link controller="person" action="list"><g:message code="layout.menu.users"/></g:link></li>                <li><g:link controller="notice" action="list"><g:message code="layout.menu.notices"/></g:link></li>            </g:isAdmin>            <li><g:link controller="person" action="edit" id="${session.userPK}"><g:message code="layout.menu.account"/></g:link></li>            <li><g:link controller="person" action="logout"><g:message code="layout.menu.logout"/></g:link></li>        </g:else>    </ul>    <g:layoutBody/>    <div id="footer">        <a href="https://svn.chrissearle.net/trac/chrissearle.net/"><g:message code="layout.footer.support.web"/></a> |        <a href="mailto:support-web@chrissearle.net"><g:message code="layout.footer.support.mail"/></a>        <br/>        <g:message code="layout.footer.design"/>    </div></div><g:if test="${grailsApplication.config.google.analytics.key}">    <script type="text/javascript">    var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");    document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));    </script>    <script type="text/javascript">    var pageTracker = _gat._getTracker("${grailsApplication.config.google.analytics.key}");    pageTracker._initData();    pageTracker._trackPageview();    </script></g:if><a href="http://huts.chrissearle.net/grandmotherly.php"><!-- transonic --></a><script language="javascript" type="text/javascript" src="/huts/js/awstats_misc_tracker.js"></script><noscript><img src="/huts/js/awstats_misc_tracker.js?nojs=y" height="0" width="0" border="0" style="display: none"></noscript></body></html>