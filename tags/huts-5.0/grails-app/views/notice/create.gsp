<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title><g:message code="notice.create.title"/></title>
</head>
<body>
<div class="body">
    <h3 class="formtitle"><g:message code="notice.create.title"/></h3>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${notice}">
        <div class="errors">
            <g:renderErrors bean="${notice}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save" method="post">
        <table>
            <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="title"><g:message code="notice.shared.title"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: notice, field: 'title', 'errors')}">
                        <input type="text" id="title" name="title" value="${fieldValue(bean: notice, field: 'title')}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="text"><g:message code="notice.shared.text"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: notice, field: 'text', 'errors')}">
                        <input type="text" id="text" name="text" value="${fieldValue(bean: notice, field: 'text')}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="shown"><g:message code="notice.shared.shown"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: notice, field: 'shown', 'errors')}">
                        <g:checkBox name="shown" value="${notice?.shown}"></g:checkBox>
                    </td>
                </tr>

            </tbody>
        </table>
        <div class="buttons">
            <span class="button"><g:actionSubmit action="create" value="${message(code:'notice.create.create.button')}"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
