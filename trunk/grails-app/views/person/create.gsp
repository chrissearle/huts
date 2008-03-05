<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <title><g:message code="user.create.title"/></title>
</head>
<body>
<div>
    <h3 class="formtitle"><g:message code="user.create.title"/></h3>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${person}">
        <div class="errors">
            <g:renderErrors bean="${person}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save" method="post">
        <table>
            <tbody>

                <tr>
                    <td valign="top" class="name">
                        <label for="name"><g:message code="user.shared.name"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: person, field: 'name', 'errors')}">
                        <input type="text" maxlength="50" id="name" name="name" value="${fieldValue(bean: person, field: 'name')}"/>
                    </td>
                </tr>

                <tr>
                    <td valign="top" class="name">
                        <label for="email"><g:message code="user.shared.email"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: person, field: 'email', 'errors')}">
                        <input type="text" id="email" name="email" value="${fieldValue(bean: person, field: 'email')}"/>
                    </td>
                </tr>

                <tr>
                    <td valign="top" class="name">
                        <label for="phone"><g:message code="user.shared.phone"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: person, field: 'phone', 'errors')}">
                        <input type="text" maxlength="15" id="phone" name="phone" value="${fieldValue(bean: person, field: 'phone')}"/>
                    </td>
                </tr>

                <tr>
                    <td valign="top" class="name">
                        <label for="userId"><g:message code="user.shared.userId"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: person, field: 'userId', 'errors')}">
                        <input type="text" maxlength="20" id="userId" name="userId" value="${fieldValue(bean: person, field: 'userId')}"/>
                    </td>
                </tr>

                <tr>
                    <td valign="top" class="name">
                        <label for="password"><g:message code="user.shared.password"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: person, field: 'password', 'errors')}">
                        <input type="text" maxlength="20" id="password" name="password" value="${fieldValue(bean: person, field: 'password')}"/>
                    </td>
                </tr>

                <tr>
                    <td valign="top" class="name">
                        <label for="admin"><g:message code="user.shared.admin"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: person, field: 'admin', 'errors')}">
                        <g:checkBox name="admin" value="${person?.admin}"></g:checkBox>
                    </td>
                </tr>

                <tr>
                    <td valign="top" class="name">
                        <label for="approved"><g:message code="user.shared.approved"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: person, field: 'approved', 'errors')}">
                        <g:checkBox name="approved" value="${person?.approved}"></g:checkBox>
                    </td>
                </tr>

            </tbody>
        </table>
        <div class="buttons">
            <span class="button"><input class="save" type="submit" value="Create"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
