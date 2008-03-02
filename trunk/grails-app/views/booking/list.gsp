<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <title>Bookings for ${hut.name}</title>
</head>
<body>
<div>
    <ul id="nav2">
        <li><g:link controller="booking" action="create">Add booking</g:link></li>
    </ul>
    <h3 class="formtitle">Booking List for ${hut}</h3>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <table>
        <thead>
            <tr>

                <th>Contact</th>

                <g:sortableColumn property="startDate" title="Start Date"/>

                <g:sortableColumn property="endDate" title="End Date"/>

                <th>Number of people</th>

                <th></th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${bookingList}" status="i" var="booking">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link controller="person" action="show" id="${booking?.contact?.id}">${booking.contact?.encodeAsHTML()}</g:link></td>

                    <td><g:formatDate date="${booking.startDate}" format="yyyy-MM-dd"/></td>

                    <td><g:formatDate date="${booking.endDate}" format="yyyy-MM-dd"/></td>

                    <td>${booking.peopleCount?.encodeAsHTML()}</td>

                    <td><g:link action="edit" id="${booking.id}">Edit</g:link></td>
                </tr>
            </g:each>
        </tbody>
    </table>
</div>
</body>
</html>
