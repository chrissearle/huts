<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:javascript library="huts"/>
    <title><g:message code="notice.show.title" args="${[notice.title]}"/></title>
</head>
<body>
<form method="post" action="${createLink(controller: 'notice', action: 'delete')}" name="deletemenuform">
    <input type="hidden" name="id" value="${notice?.id}"/>
    <ul id="nav2">
        <li><g:link controller="notice" action="edit" id="${notice.id}"><g:message code="notice.show.menu.edit" args="${[notice.title]}"/></g:link></li>
        <li><a href="#" onclick="return deleteCheckSubmit();"><g:message code="notice.show.menu.delete" args="${[notice.title]}"/></a></li>
    </ul>
</form>
<div class="body">
    <h3 class="formtitle"><g:message code="notice.show.title" args="${[notice.title]}"/></h3>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="notice.shared.title"/></td>

                    <td valign="top" class="value">${notice.title}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="notice.shared.text"/></td>

                    <td valign="top" class="value">${notice.text}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="notice.shared.shown"/></td>

                    <td valign="top" class="value">${notice.shown}</td>

                </tr>

            </tbody>
        </table>
    </div>
</div>
</body>
</html>
