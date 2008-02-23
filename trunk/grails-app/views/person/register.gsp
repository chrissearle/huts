<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Register</title>
</head>
<body>
<div class="body">
    <h1>Register</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${person}">
        <div class="errors">
            <g:renderErrors bean="${person}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="register" method="post">
        <div class="dialog">
            <table>
                <tbody>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="name">Name:</label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: person, field: 'name', 'errors')}">
                            <input type="text" maxlength="50" id="name" name="name" value="${fieldValue(bean: person, field: 'name')}"/>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="email">Email:</label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: person, field: 'email', 'errors')}">
                            <input type="text" id="email" name="email" value="${fieldValue(bean: person, field: 'email')}"/>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="phone">Phone:</label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: person, field: 'phone', 'errors')}">
                            <input type="text" maxlength="15" id="phone" name="phone" value="${fieldValue(bean: person, field: 'phone')}"/>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="userId">User Id:</label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: person, field: 'userId', 'errors')}">
                            <input type="text" maxlength="20" id="userId" name="userId" value="${fieldValue(bean: person, field: 'userId')}"/>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="password">Password:</label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: person, field: 'password', 'errors')}">
                            <input type="text" maxlength="20" id="password" name="password" value="${fieldValue(bean: person, field: 'password')}"/>
                        </td>
                    </tr>

                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><input class="save" type="submit" value="Register"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
