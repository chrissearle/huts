<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <title><g:message code="hut.userlist.title" args="[hut.name]"/></title>
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

                    <g:sortableColumn property="name" titleKey="user.shared.name"/>

                    <g:sortableColumn property="email" titleKey="user.shared.email"/>

                    <g:sortableColumn property="phone" titleKey="user.shared.phone"/>

                    <th></th>

                </tr>
            </thead>
            <tbody>
                <g:each in="${users}" status="i" var="person">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}" valign="top">

                        <td><g:link action="show" id="${person.id}">${person.name?.encodeAsHTML()}</g:link></td>

                        <td><a href="mailto:${person.email}">${person.email}</a></td>

                        <td>${person.phone?.encodeAsHTML()}</td>

                        <td>
                            <g:if test="${hut.users.users.contains(person)}">
                                <a href="${createLink(controller: "hut", action: "deluser", id: hut.id)}?user=${person.id}">Remove</a>
                            </g:if>

                            <g:else>
                                <a href="${createLink(controller: "hut", action: "adduser", id: hut.id)}?user=${person.id}">Add</a>
                            </g:else>

                        </td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
