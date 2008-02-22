<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Edit Person</title>
</head>
<body>
<div class="body">
    <h1>Edit Person</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${person}">
        <div class="errors">
            <g:renderErrors bean="${person}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form method="post">
        <input type="hidden" name="id" value="${person?.id}"/>
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

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="admin">Admin:</label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: person, field: 'admin', 'errors')}">
                            <g:checkBox name="admin" value="${person?.admin}"></g:checkBox>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="owns">Owns:</label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: person, field: 'owns', 'errors')}">

                            <ul>
                                <g:each var="o" in="${person?.owns?}">
                                    <li><g:link controller="hut" action="show" id="${o.id}">${o}</g:link></li>
                                </g:each>
                            </ul>
                            <g:link controller="hut" params="[" person.id":person?.id]" action="create">Add Hut</g:link>

                        </td>
                    </tr>

                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><g:actionSubmit class="save" value="Update"/></span>
            <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
