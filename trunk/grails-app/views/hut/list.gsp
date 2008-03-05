<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <title><g:message code="hut.list.title"/></title>
</head>
<body>

<ul id="nav2">
    <li><g:link controller="hut" action="create"><g:message code="hut.list.menu.add"/></g:link></li>
</ul>

<div>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <table>
        <tbody>
            <g:each in="${hutList}" status="i" var="hut">
                <tr valign="top">
                    <g:if test="${(i % 2) == 0}">
                        <g:render template="hutimage" var="hut" bean="${hut}"/>
                    </g:if>
                    <g:render template="hutdetails" var="hut" bean="${hut}"/>
                    <g:if test="${(i % 2) == 1}">
                        <g:render template="hutimage" var="hut" bean="${hut}"/>
                    </g:if>
                </tr>
            </g:each>
        </tbody>
    </table>
</div>
</body>
</html>
