<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <title><g:message code="user.list.title"/></title>
</head>
<body>
<ul id="nav2">
    <li></span><g:link controller="person" action="create"><g:message code="user.list.menu.add"/></g:link></li>
    <li><g:link controller="person" action="approval"><g:message code="user.list.menu.approve"/></g:link></li>
</ul>

<div>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
                <tr>

                    <g:sortableColumn property="name" titleKey="user.shared.name"/>

                    <g:sortableColumn property="email" titleKey="user.shared.email"/>

                    <g:sortableColumn property="phone" titleKey="user.shared.phone"/>

                    <th><g:message code="user.shared.contact"/></th>

                </tr>
            </thead>
            <tbody>
                <g:each in="${personList}" status="i" var="person">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}" valign="top">

                        <td><g:link action="show" id="${person.id}">${person.name?.encodeAsHTML()}</g:link></td>

                        <td><a href="mailto:${person.email}">${person.email}</a></td>

                        <td>${person.phone?.encodeAsHTML()}</td>

                        <td>
                            <g:if test="${person?.owns?.size() > 0}">
                                <g:each var="o" in="${person.owns}">
                                    <g:link controller="hut" action="show" id="${o.id}">${o}</g:link><br/>
                                </g:each>
                            </g:if>
                        </td>

                    </tr>
                </g:each>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
