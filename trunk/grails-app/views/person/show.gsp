<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:javascript library="huts"/>
    <title><g:message code="user.show.title" args="${[person.name]}"/></title>
</head>
<body>
<g:isAdmin userId="${session.userId}">
    <form method="post" action="${createLink(controller: 'person', action: 'delete')}" name="deletemenuform">
        <input type="hidden" name="id" value="${person?.id}"/>
        <ul id="nav2">
            <li><g:link controller="person" action="edit" id="${person.id}"><g:message code="user.show.menu.edit" args="${[person.name]}"/></g:link></li>
            <li><a href="#" onclick="return deleteCheckSubmit();"><g:message code="user.show.menu.delete" args="${[person.name]}"/></a></li>
        </ul>
    </form>
</g:isAdmin>
<div>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <table>
        <tbody>

            <tr>
                <td valign="top" class="name"><g:message code="user.shared.name"/></td>

                <td valign="top" class="value">${person.name}</td>

            </tr>

            <tr>
                <td valign="top" class="name"><g:message code="user.shared.organization"/></td>

                <td valign="top" class="value">${person.organization}</td>

            </tr>

            <tr>
                <td valign="top" class="name"><g:message code="user.shared.email"/></td>

                <td valign="top" class="value"><a href="mailto:${person.email}">${person.email}</a></td>

            </tr>

            <tr>
                <td valign="top" class="name"><g:message code="user.shared.jabber"/></td>

                <td valign="top" class="value">${person.jabber}</td>

            </tr>

            <tr>
                <td valign="top" class="name"><g:message code="user.shared.phone"/></td>

                <td valign="top" class="value">${person.phone}</td>

            </tr>

            <g:isAdmin userId="${session.userId}">
                <tr>
                    <td valign="top" class="name"><g:message code="user.shared.published"/></td>

                    <td valign="top" class="value">${person.published}</td>

                </tr>

                <tr>
                    <td valign="top" class="name"><g:message code="user.shared.userId"/></td>

                    <td valign="top" class="value">${person.userId}</td>

                </tr>
            </g:isAdmin>

            <tr>
                <td valign="top" class="name"><g:message code="user.shared.contact"/></td>

                <td valign="top" style="text-align:left;" class="value">
                    <ul>
                        <g:each var="o" in="${person.owns}">
                            <li><g:link controller="hut" action="show" id="${o.id}">${o}</g:link></li>
                        </g:each>
                    </ul>
                </td>

            </tr>

        </tbody>
    </table>
</div>
</body>
</html>
