<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Show Booking</title>
</head>
<body>
<div class="body">
    <h1>Show Booking</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>

                <tr class="prop">
                    <td valign="top" class="name">Id:</td>

                    <td valign="top" class="value">${booking.id}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name">Hut:</td>

                    <td valign="top" class="value"><g:link controller="hut" action="show" id="${booking?.hut?.id}">${booking?.hut}</g:link></td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name">Contact:</td>

                    <td valign="top" class="value"><g:link controller="person" action="show" id="${booking?.contact?.id}">${booking?.contact}</g:link></td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name">Start Date:</td>

                    <td valign="top" class="value">${booking.startDate}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name">End Date:</td>

                    <td valign="top" class="value">${booking.endDate}</td>

                </tr>

            </tbody>
        </table>
    </div>
    <div class="buttons">
        <g:form>
            <input type="hidden" name="id" value="${booking?.id}"/>
            <span class="button"><g:actionSubmit class="edit" value="Edit"/></span>
            <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete"/></span>
        </g:form>
    </div>
</div>
</body>
</html>
