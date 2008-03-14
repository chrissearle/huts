<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title><g:message code="notice.list.title"/></title>
</head>
<body>
<ul id="nav2">
    <li><g:link class="create" action="create"><g:message code="notice.menu.new"/></g:link></li>
</ul>
<div class="body">
    <h3 class="formtitle"><g:message code="notice.list.title"/></h3>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
                <tr>

                    <g:sortableColumn property="id" titleKey="notice.shared.id"/>

                    <g:sortableColumn property="title" titleKey="notice.shared.title"/>

                    <g:sortableColumn property="text" titleKey="notice.shared.text"/>

                    <g:sortableColumn property="shown" titleKey="notice.shared.shown"/>

                </tr>
            </thead>
            <tbody>
                <g:each in="${noticeList}" status="i" var="notice">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                        <td><g:link action="show" id="${notice.id}">${notice.id?.encodeAsHTML()}</g:link></td>

                        <td>${notice.title?.encodeAsHTML()}</td>

                        <td>${notice.text?.encodeAsHTML()}</td>

                        <td>${notice.shown?.encodeAsHTML()}</td>

                    </tr>
                </g:each>
            </tbody>
        </table>
    </div>
    <div class="paginateButtons">
        <g:paginate total="${Notice.count()}"/>
    </div>
</div>
</body>
</html>
