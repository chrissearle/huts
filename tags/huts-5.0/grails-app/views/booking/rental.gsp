<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title><g:message code="booking.rental.title" args="[hut]"/></title>
</head>
<body>
<ul id="nav2">
    <li><g:link controller="hut" action="show" id="${hut.id}"><g:message code="booking.list.menu.hut" args="[hut.name]"/></g:link></li>
    <li><g:link controller="booking" action="create">Add booking</g:link></li>
    <li><g:link controller="booking" action="list" id="${hut.id}"><g:message code="booking.rental.menu.list"/></g:link></li>
</ul>
<div>
    <h3 class="formtitle"><g:message code="booking.rental.title" args="[hut]"/></h3>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <table>
        <thead>
            <tr>

                <th><g:message code="booking.shared.contact"/></th>

                <g:sortableColumn property="startDate" titleKey="booking.shared.startdate"/>

                <g:sortableColumn property="endDate" titleKey="booking.shared.enddate"/>

                <th><g:message code="booking.shared.value"/></th>
            </tr>
        </thead>

        <g:set var="total" value="${0}"/>
        <g:set var="currency" value=""/>

        <tbody>
            <g:each in="${bookingList}" status="i" var="booking">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link controller="person" action="show" id="${booking?.contact?.id}">${booking.contact?.encodeAsHTML()}</g:link></td>

                    <td><g:formatDate date="${booking.startDate}" format="dd/MM/yyyy"/></td>

                    <td><g:formatDate date="${booking.endDate}" format="dd/MM/yyyy"/></td>

                    <td>${booking.priceplan.currency} ${booking.peopleCount * booking.priceplan.price}</td>

                    <g:set var="total" value="${total + (booking.peopleCount * booking.priceplan.price)}"/><br>

                    <!-- TODO - assumes same currency for a hut -->

                    <g:set var="currency" value="${booking.priceplan.currency}"/>
                </tr>
            </g:each>
            <tr>
                <td colspan="3">&nbsp;</td>
                <td>${currency} ${total}</td>
            </tr>
        </tbody>
    </table>
</div>
</body>
</html>
