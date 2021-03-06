<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Create Booking</title>
</head>
<body>
<ul id="nav2">
    <li><g:link controller="booking" action="list" id="${booking.hut.id}"><g:message code="booking.create.menu.back"/></g:link></li>
</ul>
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
        <g:hiddenField name="hut.id" value="${booking?.hut?.id}"/>
        <table>
            <tbody>
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

                <!-- TODO - add price plan here - needs some kind of AJAX -->
            </tbody>
        </table>
        <div class="buttons">
            <span class="button"><input class="save" type="submit" value="${message(code:'booking.create.create.button')}"/></span>
        </div>
    </g:form>

    <g:if test="${hut}">
        <g:render template="/availabilityLong" var="hut" bean="${hut}"/>
    </g:if>
</div>
</body>
</html>
