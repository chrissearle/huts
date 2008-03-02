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
    <g:form action="book" method="post">
        <g:hiddenField name="hut.id" value="${hut.id}"/>
        <g:hiddenField name="user.id" value="${session.userId}"/>
        <table>
            <tbody>

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
            <span class="button"><input class="save" type="submit" value="Book"/></span>
        </div>
    </g:form>

    <h4 class="formtitle">Availability</h4>

    <p class="formtext">This chart shows only nights - so day of departure is not shown</p>

    <table class="datetable">
        <tr><td width="50%" class="booked">Booked</td><td width="50%" class="bookedMe">Booked by you</td></tr>
    </table>

    <g:monthView hutId="${hut.id}" monthcount="12" userId="${session.userId}"/>

</div>
</body>
</html>
