<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title><g:message code="hut.list.title"/></title>
    <g:javascript library="iconfactory"/>
    <g:javascript library="huts"/>

    <g:if test="${session.getAttribute('org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE')}">
        <script type="text/javascript" src="http://www.google.com/jsapi?key=${grailsApplication.config.google.map.key}&h1=${session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE")}"></script>
    </g:if>
    <g:else>
        <script type="text/javascript" src="http://www.google.com/jsapi?key=${grailsApplication.config.google.map.key}"></script>
    </g:else>
    <script type="text/javascript">
    google.load("maps", "2.x");

    function initialize() {

    <g:hutLocs huts="${hutList}"/>

    var map = initializeMaps(hutlocs);

    <g:if test="${session.zoom}">
        map.setZoom(${session.zoom});
    </g:if>
    <g:if test="${session.centerHut}">
        <g:centerHut hutId="${session.centerHut}"/>
    </g:if>
    <g:if test="${session.latitude && session.longitude}">
        map.setCenter(new GLatLng(${session.latitude}, ${session.longitude}));
    </g:if>
    }

    google.setOnLoadCallback(initialize);
    </script></head>
<body onunload="GUnload()">

<ul id="nav2">

    <g:if test="${session.userId}">
        <li><g:link controller="hut" action="create"><g:message code="hut.list.menu.add"/></g:link></li>
    </g:if>
    <li><a href="javascript:toggleMapList();"><g:message code="hut.list.menu.toggle"/></a></li>
</ul>

<div>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <g:render template="/notices"/>

    <div id="map" class="mainmap"></div>

    <div id="oldlist" style="display: none;">
        <table>
            <tbody>
                <g:each in="${hutList}" status="i" var="hut">
                    <tr valign="top">
                        <g:if test="${(i % 2) == 0}">
                            <g:render template="hutimagenomap" var="hut" bean="${hut}"/>
                        </g:if>
                        <g:render template="hutdetails" var="hut" bean="${hut}"/>
                        <g:if test="${(i % 2) == 1}">
                            <g:render template="hutimagenomap" var="hut" bean="${hut}"/>
                        </g:if>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
