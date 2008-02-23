<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Edit Hut</title>
</head>
<body>
<div class="body">
    <h1>Edit Hut</h1>
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
                            <label for="name">Name:</label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: hut, field: 'name', 'errors')}">
                            <input type="text" maxlength="50" id="name" name="name" value="${fieldValue(bean: hut, field: 'name')}"/>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="location">Location:</label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: hut, field: 'location', 'errors')}">
                            <input type="text" maxlength="50" id="location" name="location" value="${fieldValue(bean: hut, field: 'location')}"/>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="description">Description:</label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: hut, field: 'location', 'errors')}">
                            <textarea id="description" name="description">${fieldValue(bean: hut, field: 'description')}</textarea>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="beds">Beds:</label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: hut, field: 'beds', 'errors')}">
                            <input type="text" maxlength="5" id="beds" name="beds" value="${fieldValue(bean: hut, field: 'beds')}"/>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="owner">Owner:</label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: hut, field: 'owner', 'errors')}">
                            <g:select optionKey="id" from="${Person.list()}" name="owner.id" value="${hut?.owner?.id}"></g:select>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="bookings">Bookings:</label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: hut, field: 'bookings', 'errors')}">

                            <ul>
                                <g:each var="b" in="${hut?.bookings?}">
                                    <li><g:link controller="booking" action="show" id="${b.id}">${b}</g:link></li>
                                </g:each>
                            </ul>
                            <g:link controller="booking" action="book" id="${hut.id}">Add Booking</g:link>

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
