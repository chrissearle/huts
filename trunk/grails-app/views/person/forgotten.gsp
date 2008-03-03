<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <title>Forgotten Password</title>
</head>
<body>

<div>
    <h3 class="formtitle">Forgotten Password</h3>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <p class="formtext">Please enter either your login or your registered e-mail address.</p>

    <g:form controller="person" method="post">
        <table>
            <tbody>

                <tr>
                    <td valign="top" class="name">
                        <label for="userId">User Id:</label>
                    </td>
                    <td valign="top" class="value">
                        <input type="text" maxlength="20" id="userId" name="userId" value="${fieldValue(bean: person, field: 'userId')}"/>
                    </td>
                </tr>

                <tr>
                    <td valign="top" class="name">
                        <label for="email">E-mail:</label>
                    </td>
                    <td valign="top" class="value">
                        <input type="text" maxlength="50" id="email" name="email" value="${fieldValue(bean: person, field: 'email')}"/>
                    </td>
                </tr>

            </tbody>
        </table>

        <div class="buttons">
            <span class="button"><g:actionSubmit action="forgotten" value="Send password"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
