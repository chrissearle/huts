<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:javascript library="huts"/>
    <title><g:message code="notice.show.title" args="${[noticeInstance.title]}"/></title>
</head>
<body>
<form method="post" action="${createLink(controller: 'notice', action: 'delete')}" name="deletemenuform">
    <input type="hidden" name="id" value="${noticeInstance?.id}"/>
    <ul id="nav2">
        <li><g:link controller="notice" action="edit" id="${noticeInstance.id}"><g:message code="notice.show.menu.edit" args="${[noticeInstance.title]}"/></g:link></li>
        <li><a href="#" onclick="return deleteCheckSubmit();"><g:message code="notice.show.menu.delete" args="${[noticeInstance.title]}"/></a></li>
    </ul>
</form>
<div class="body">
    <h3 class="formtitle"><g:message code="notice.show.title" args="${[noticeInstance.title]}"/></h3>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="notice.shared.title"/></td>

                    <td valign="top" class="value">${noticeInstance.title}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="notice.shared.text"/></td>

                    <td valign="top" class="value">${noticeInstance.text}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="notice.shared.shown"/></td>

                    <td valign="top" class="value">${noticeInstance.shown}</td>

                </tr>

            </tbody>
        </table>
    </div>
</div>
</body>
</html>
