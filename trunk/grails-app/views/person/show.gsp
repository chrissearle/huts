<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <g:javascript library="huts"/>
    <title><g:message code="user.show.title" args="${[person.name]}"/></title>
</head>
<body>
<div>
    <g:isAdmin userId="${session.userId}">
        <form method="post" action="${createLink(controller: 'person', action: 'delete')}" name="deletemenuform">
            <input type="hidden" name="id" value="${person?.id}"/>
            <ul id="nav2">
                <li><g:link controller="person" action="edit" id="${person.id}"><g:message code="user.show.menu.edit" args="${[person.name]}"/></g:link></li>
                <li><a href="#" onclick="return deleteCheckSubmit();"><g:message code="user.show.menu.delete" args="${[person.name]}"/></a></li>
            </ul>
        </form>
    </g:isAdmin>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <table>
        <tbody>

            <tr>
                <td valign="top" class="name">Name:</td>

                <td valign="top" class="value">${person.name}</td>

            </tr>

            <tr>
                <td valign="top" class="name">Email:</td>

                <td valign="top" class="value"><a href="mailto:${person.email}">${person.email}</a></td>

            </tr>

            <tr>
                <td valign="top" class="name">Phone:</td>

                <td valign="top" class="value">${person.phone}</td>

            </tr>

            <tr>
                <td valign="top" class="name">User Id:</td>

                <td valign="top" class="value">${person.userId}</td>

            </tr>

            <tr>
                <td valign="top" class="name">Contact for:</td>

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
