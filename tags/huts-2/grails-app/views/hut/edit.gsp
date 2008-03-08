<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <title><g:message code="hut.edit.title" args="${[hut.name]}"/></title>
</head>
<body>

<div>
    <h3 class="formtitle"><g:message code="hut.edit.title" args="${[hut.name]}"/></h3>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <g:hasErrors bean="${hut}">
        <div class="errors">
            <g:renderErrors bean="${hut}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form method="post">
        <input type="hidden" name="id" value="${hut?.id}"/>
        <div class="dialog">
            <table>
                <tbody>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="name"><g:message code="hut.shared.name"/></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: hut, field: 'name', 'errors')}">
                            <input type="text" maxlength="50" id="name" name="name" value="${fieldValue(bean: hut, field: 'name')}"/>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="location"><g:message code="hut.shared.location"/></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: hut, field: 'location', 'errors')}">
                            <input type="text" maxlength="50" id="location" name="location" value="${fieldValue(bean: hut, field: 'location')}"/>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="description"><g:message code="hut.shared.description"/></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: hut, field: 'location', 'errors')}">
                            <textarea id="description" name="description" cols="40" rows="5">${fieldValue(bean: hut, field: 'description')}</textarea>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="beds"><g:message code="hut.shared.beds"/></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: hut, field: 'beds', 'errors')}">
                            <input type="text" maxlength="5" id="beds" name="beds" size="6" value="${fieldValue(bean: hut, field: 'beds')}"/>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="owner"><g:message code="hut.shared.contact"/></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: hut, field: 'owner', 'errors')}">
                            <g:select optionKey="id" from="${Person.list()}" name="owner.id" value="${hut?.owner?.id}"></g:select>
                        </td>
                    </tr>

                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><g:actionSubmit action="update" value="${message(code:'hut.edit.update.button')}"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
