<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Create Booking</title>
</head>
<body>
<div class="body">
    <h1>Book ${hut}</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${booking}">
        <div class="errors">
            <g:renderErrors bean="${booking}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="book" method="post">
        <g:hiddenField name="hut.id" value="${hut.id}"/>
        <g:hiddenField name="user.id" value="${session.userId}"/>
        <div class="dialog">
            <table>
                <tbody>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="startDate">Start Date:</label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: booking, field: 'startDate', 'errors')}">
                            <g:datePicker name="startDate" value="${booking?.startDate}" precision="day"></g:datePicker>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="endDate">End Date:</label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: booking, field: 'endDate', 'errors')}">
                            <g:datePicker name="endDate" value="${booking?.endDate}" precision="day"></g:datePicker>
                        </td>
                    </tr>

                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><input class="save" type="submit" value="Book"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
