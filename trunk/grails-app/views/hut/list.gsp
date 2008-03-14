<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <title><g:message code="hut.list.title"/></title>
    <g:javascript library="huts"/>
    <script type="text/javascript" src="http://www.google.com/jsapi?key=${grailsApplication.config.google.map.key}"></script> <!-- add &hl=lang here -->
    <script type="text/javascript">
    google.load("maps", "2.x");

    function initialize() {
    var hutlocs = new Array()

    <g:each var="hut" in="${hutList}">
        hutlocs["${hut.name}"] = new HutLocation(
          "${hut.name}",
          "${hut.latitude}",
          "${hut.longitude}",
        <g:if test="${hut.image}">"${createLink(action: 'showpic', id: hut.id)}",</g:if><g:else>"",</g:else>
        "${createLink(action: 'show', id: hut.id)}",
          "<g:message code='hut.list.show.link'/>",
          "${hut.description}",
          "${hut.owner.organization}",
        <g:if test="${hut.owner.userId == session.userId}">"OWNER"</g:if>
        <g:else>
            <g:if test="${hut.openHut}">"PUBLIC"</g:if>
            <g:else>"PRIVATE"</g:else>
        </g:else>
        );
    </g:each>

    initializeMaps(hutlocs);
    }

    google.setOnLoadCallback(initialize);
    </script>
</head>
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
