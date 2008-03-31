<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title><g:message code="user.contact.title" args="${[user.name]}"/></title>
</head>
<body>

<div>
    <h3 class="formtitle"><g:message code="user.contact.title" args="${[user.name]}"/></h3>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <p class="formtext"><g:message code="user.contact.text" args="${[user.name]}"/></p>

    <g:form controller="person" method="post">
        <g:hiddenField name="id" value="${user.id}"/>
        <table>
            <tbody>

                <tr>
                    <td valign="top" class="name">
                        <label for="subject"><g:message code="user.shared.subject"/></label>
                    </td>
                    <td valign="top" class="value">
                        <input type="text" maxlength="20" id="subject" name="subject" value="${fieldValue(bean: contactMessage, field: 'subject')}"/>
                    </td>
                </tr>

                <tr>
                    <td valign="top" class="name">
                        <label for="messageContent"><g:message code="user.shared.content"/></label>
                    </td>
                    <td valign="top" class="value">
                        <g:textArea name="messageContent" value="${fieldValue(bean: contactMessage, field: 'messageContent')}" rows="10" cols="40"/>
                    </td>
                </tr>

            </tbody>
        </table>

        <div class="buttons">
            <span class="button"><g:actionSubmit action="contact" value="${message(code:'user.contact.send.button')}"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
