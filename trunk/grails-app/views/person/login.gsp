<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <title><g:message code="user.login.title"/></title>
</head>
<body>

<div>
    <h3 class="formtitle"><g:message code="user.login.title"/></h3>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

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
                        <label for="password"><g:message code="user.shared.password"/></label>
                    </td>
                    <td valign="top" class="value">
                        <input type="password" maxlength="20" id="password" name="password" value="${fieldValue(bean: person, field: 'password')}"/>
                    </td>
                </tr>

            </tbody>
        </table>

        <div class="buttons">
            <span class="button"><g:actionSubmit value="Log in"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
