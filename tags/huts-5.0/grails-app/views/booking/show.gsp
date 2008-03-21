<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:javascript library="huts"/>
    <title>Show Booking</title>
</head>
<body>
<div>
    <form method="post" action="${createLink(controller: 'booking', action: 'delete')}" name="deletemenuform">
        <input type="hidden" name="id" value="${booking?.id}"/>
        <ul id="nav2">
            <li><g:link controller="booking" action="edit" id="${booking.id}">Edit</g:link></li>
            <li><a href="#" onclick="return deleteCheckSubmit();">Delete</a></li>
            <g:manageHut hutId="${booking.hut.id}" userId="${session.userId}">
                <li><g:link controller="booking" action="list" id="${booking.hut.id}">Back to list</g:link></li>
            </g:manageHut>
        </ul>
    </form>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <table>
        <tbody>

            <tr>
                <td valign="top" class="name"><g:message code="booking.shared.hut"/></td>

                <td valign="top" class="value"><g:link controller="hut" action="show" id="${booking?.hut?.id}">${booking?.hut}</g:link></td>

            </tr>

            <tr>
                <td valign="top" class="name"><g:message code="booking.shared.contact"/></td>

                <td valign="top" class="value"><g:link controller="person" action="show" id="${booking?.contact?.id}">${booking?.contact}</g:link></td>

            </tr>

            <tr>
                <td valign="top" class="name"><g:message code="booking.shared.startdate"/></td>

                <td valign="top" class="value"><g:formatDate date="${booking.startDate}" format="dd/MM/yyyy"/></td>

            </tr>

            <tr>
                <td valign="top" class="name"><g:message code="booking.shared.enddate"/></td>

                <td valign="top" class="value"><g:formatDate date="${booking.endDate}" format="dd/MM/yyyy"/></td>

            </tr>

            <tr>
                <td valign="top" class="name"><g:message code="booking.shared.people"/></td>

                <td valign="top" class="value">${booking.peopleCount}</td>
            </tr>

            <g:if test="${booking.hut.pricePlans.size() > 0}">
                <tr>
                    <td valign="top" class="name"><g:message code="booking.shared.price"/></td>

                    <td valign="top" class="value">${booking.priceplan}</td>
                </tr>
            </g:if>
        </tbody>
    </table>
</div>
</body>
</html>
