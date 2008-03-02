<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <g:javascript library="huts"/>
    <title>Show Booking</title>
</head>
<body>
<div>
    <form method="post" action="${createLink(controller: 'booking', action: 'delete')}" name="deletemenuform">
        <input type="hidden" name="id" value="${booking?.id}"/>
        <ul id="nav2">
            <li><g:link controller="booking" action="edit" id= q"${booking.id}">Edit</g:link></li>
            <li><a href="#" onclick="return deleteHutSubmit();">Delete</a></li>
            <li><g:link controller="booking" action="list" id="${booking.hut.id}">Back to list</g:link></li>
        </ul>
    </form>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <table>
        <tbody>

            <tr>
                <td valign="top" class="name">Hut:</td>

                <td valign="top" class="value"><g:link controller="hut" action="show" id="${booking?.hut?.id}">${booking?.hut}</g:link></td>

            </tr>

            <tr>
                <td valign="top" class="name">Contact:</td>

                <td valign="top" class="value"><g:link controller="person" action="show" id="${booking?.contact?.id}">${booking?.contact}</g:link></td>

            </tr>

            <tr>
                <td valign="top" class="name">Start Date:</td>

                <td valign="top" class="value"><g:formatDate date="${booking.startDate}" format="yyyy-MM-dd"/></td>

            </tr>

            <tr>
                <td valign="top" class="name">End Date:</td>

                <td valign="top" class="value"><g:formatDate date="${booking.endDate}" format="yyyy-MM-dd"/></td>

            </tr>

            <tr>
                <td valign="top" class="name">Number of people</td>

                <td valign="top" class="value">${booking.peopleCount}</td>
            </tr>
        </tbody>
    </table>
</div>
</body>
</html>
