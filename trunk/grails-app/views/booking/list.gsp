<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Bookings for ${hut.name}</title>
</head>
<body>
<ul id="nav2">
    <li><g:link controller="hut" action="show" id="${hut.id}"><g:message code="booking.list.menu.hut" args="[hut.name]"/></g:link></li>
    <li><g:link controller="booking" action="create" id="${hut.id}">Add booking</g:link></li>
    <g:if test="${hut.pricePlans.size() > 0}">
        <li><g:link controller="booking" action="rental" id="${hut.id}">Rental report</g:link></li>
    </g:if>
</ul>
<div>
    <h3 class="formtitle">Booking List for ${hut}</h3>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <table>
        <thead>
            <tr>

                <th><g:message code="booking.shared.contact"/></th>

                <g:sortableColumn property="startDate" titleKey="booking.shared.startdate"/>

                <g:sortableColumn property="endDate" titleKey="booking.shared.enddate"/>

                <th><g:message code="booking.shared.people"/></th>

                <g:if test="${hut.pricePlans.size() > 0}">
                    <th><g:message code="booking.shared.price"/></th>
                </g:if>

                <th></th>

                <th></th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${bookingList}" status="i" var="booking">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link controller="person" action="show" id="${booking?.contact?.id}">${booking.contact?.encodeAsHTML()}</g:link></td>

                    <td><g:formatDate date="${booking.startDate}" format="dd/MM/yyyy"/></td>

                    <td><g:formatDate date="${booking.endDate}" format="dd/MM/yyyy"/></td>

                    <td>${booking.peopleCount?.encodeAsHTML()}</td>

                    <g:if test="${hut.pricePlans.size() > 0}">
                        <td valign="top" class="value">${booking.priceplan}</td>
                    </g:if>

                    <td><g:link action="show" id="${booking.id}"><g:message code="booking.shared.show"/></g:link></td>

                    <td><g:link action="edit" id="${booking.id}"><g:message code="booking.shared.edit"/></g:link></td>
                </tr>
            </g:each>
        </tbody>
    </table>
</div>
</body>
</html>
