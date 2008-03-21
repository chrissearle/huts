<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Edit Booking</title>
</head>
<body>
<div>
    <ul id="nav2">
        <li><g:link controller="booking" action="list" id="${booking.hut.id}">Back to list</g:link></li>
    </ul>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${booking}">
        <div class="errors">
            <g:renderErrors bean="${booking}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form method="post">
        <input type="hidden" name="id" value="${booking?.id}"/>
        <table>
            <tbody>

                <tr>
                    <td valign="top" class="name">
                        <label for="hut"><g:message code="booking.shared.hut"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: booking, field: 'hut', 'errors')}">
                        <g:select optionKey="id" from="${Hut.list()}" name="hut.id" value="${booking?.hut?.id}"></g:select>
                    </td>
                </tr>

                <tr>
                    <td valign="top" class="name">
                        <label for="contact"><g:message code="booking.shared.contact"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: booking, field: 'contact', 'errors')}">
                        <g:select optionKey="id" from="${Person.list()}" name="contact.id" value="${booking?.contact?.id}"></g:select>
                    </td>
                </tr>

                <tr>
                    <td valign="top" class="name">
                        <label for="startDate"><g:message code="booking.shared.startdate"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: booking, field: 'startDate', 'errors')}">
                        <g:datePicker name="startDate" value="${booking?.startDate}" precision="day"></g:datePicker>
                    </td>
                </tr>

                <tr>
                    <td valign="top" class="name">
                        <label for="endDate"><g:message code="booking.shared.enddate"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: booking, field: 'endDate', 'errors')}">
                        <g:datePicker name="endDate" value="${booking?.endDate}" precision="day"></g:datePicker>
                    </td>
                </tr>

                <tr>
                    <td valign="top" class="name">
                        <label for="peopleCount"><g:message code="booking.shared.people"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: booking, field: 'peopleCount', 'errors')}">
                        <input type="text" maxlength="50" id="peopleCount" name="peopleCount" value="${fieldValue(bean: booking, field: 'peopleCount')}"/>
                    </td>
                </tr>

                <g:if test="${booking.hut.pricePlans.size() > 0}">
                    <tr>
                        <td valign="top" class="name">
                            <label for="priceplan"><g:message code="booking.shared.price"/></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: booking, field: 'priceplan', 'errors')}">
                            <g:select optionKey="id" from="${booking.hut.pricePlans}" name="priceplan.id" value="${booking?.priceplan?.id}"></g:select>
                        </td>
                    </tr>
                </g:if>

            </tbody>
        </table>
        <div class="buttons">
            <span class="button"><g:actionSubmit action="update" value="${message(code:'booking.edit.update.button')}"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
