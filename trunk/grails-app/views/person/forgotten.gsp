<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <title><g:message code="user.forgotten.title"/></title>
</head>
<body>

<div>
    <h3 class="formtitle"><g:message code="user.forgotten.title"/></h3>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <p class="formtext"><g:message code="user.forgotten.text.forgetten"/></p>

    <g:form controller="person" method="post">
        <table>
            <tbody>

                <tr>
                    <td valign="top" class="name">
                        <label for="userId"><g:message code="user.shared.userId"/></label>
                    </td>
                    <td valign="top" class="value">
                        <input type="text" maxlength="20" id="userId" name="userId" value="${fieldValue(bean: person, field: 'userId')}"/>
                    </td>
                </tr>

                <tr>
                    <td valign="top" class="name">
                        <label for="email"><g:message code="user.shared.email"/></label>
                    </td>
                    <td valign="top" class="value">
                        <input type="text" maxlength="50" id="email" name="email" value="${fieldValue(bean: person, field: 'email')}"/>
                    </td>
                </tr>

            </tbody>
        </table>

        <div class="buttons">
            <span class="button"><g:actionSubmit action="forgotten" value="${message(code:'user.forgotten.send.button')}"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
