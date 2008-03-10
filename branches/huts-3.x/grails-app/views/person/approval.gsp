<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <title><g:message code="user.approval.title"/></title>
</head>
<body>
<div>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
                <tr>

                    <th><g:message code="user.shared.name"/></th>

                    <th><g:message code="user.shared.email"/></th>

                    <th><g:message code="user.shared.phone"/></th>

                    <th></th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${personList}" status="i" var="person">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                        <td>${person.name?.encodeAsHTML()}</td>

                        <td><a href="mailto:${person.email}">${person.email}</a></td>

                        <td>${person.phone?.encodeAsHTML()}</td>

                        <td><g:link action="approval" id="${person.id}"><g:message code="user.approval.approve.link"/></g:link></td>

                    </tr>
                </g:each>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
