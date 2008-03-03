<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <title>Create Booking</title>
</head>
<body>
<div>
    <h3 class="formtitle">Book ${hut}</h3>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${booking}">
        <div class="errors">
            <g:renderErrors bean="${booking}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save" method="post">
        <table>
            <tbody>

                <tr>
                    <td valign="top" class="name">
                        <label for="hut">Hut:</label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: booking, field: 'hut', 'errors')}">
                        <g:select optionKey="id" from="${Hut.list()}" name="hut.id" value="${booking?.hut?.id}"></g:select>
                    </td>
                </tr>

                <tr>
                    <td valign="top" class="name">
                        <label for="contact">Contact:</label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: booking, field: 'contact', 'errors')}">
                        <g:select optionKey="id" from="${Person.list()}" name="contact.id" value="${booking?.contact?.id}"></g:select>
                    </td>
                </tr>

                <tr>
                    <td valign="top" class="name">
                        <label for="startDate">Start Date:</label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: booking, field: 'startDate', 'errors')}">
                        <g:datePicker name="startDate" value="${booking?.startDate}" precision="day"></g:datePicker>
                    </td>
                </tr>

                <tr>
                    <td valign="top" class="name">
                        <label for="endDate">End Date:</label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: booking, field: 'endDate', 'errors')}">
                        <g:datePicker name="endDate" value="${booking?.endDate}" precision="day"></g:datePicker>
                    </td>
                </tr>

                <tr>
                    <td valign="top" class="name">
                        <label for="peopleCount">Number of people</label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: booking, field: 'peopleCount', 'errors')}">
                        <input type="text" maxlength="50" id="peopleCount" name="peopleCount" value="${fieldValue(bean: booking, field: 'peopleCount')}"/>
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
