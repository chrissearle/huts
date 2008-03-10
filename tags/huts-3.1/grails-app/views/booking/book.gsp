<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <title><g:message code="booking.book.title" args="[hut.name]"/></title>
</head>
<body>
<div>
    <h3 class="formtitle"><g:message code="booking.book.title" args="[hut.name]"/></h3>
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
        <table>
            <tbody>

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
                    <td valign="top" class="value ${hasErrors(bean: booking, field: 'startDate', 'errors')}">
                        <g:datePicker name="endDate" value="${booking?.endDate}" precision="day"></g:datePicker>
                    </td>
                </tr>

                <tr>
                    <td valign="top" class="name">
                        <label for="peopleCount"><g:message code="booking.shared.people"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: booking, field: 'peopleCount', 'errors')}">
                        <input type="text" maxlength="50" id="peopleCount" name="peopleCount" value="${fieldValue(bean: booking, field: 'peopleCount')}"/> <g:message code="booking.book.max" args="[hut.beds]"/>
                    </td>
                </tr>

            </tbody>
        </table>
        <div class="buttons">
            <span class="button"><g:actionSubmit action="book" value="${message(code:'booking.book.book.button')}"/></span>
        </div>
    </g:form>

    <g:render template="/availabilityLong" var="hut" bean="${hut}"/>

</div>
</body>
</html>
