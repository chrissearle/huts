<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <g:javascript library="huts"/>
    <title><g:message code="hut.show.title" args="${[hut.name]}"/></title>
</head>
<body>

<g:manageHut hutId="${hut.id}" userId="${session.userId}">
    <form method="post" action="${createLink(controller: 'hut', action: 'delete')}" name="deletemenuform">
        <input type="hidden" name="id" value="${hut?.id}"/>

        <ul id="nav2">
            <li><g:link controller="hut" action="edit" id="${hut.id}"><g:message code="hut.show.menu.edit" args="${[hut.name]}"/></g:link></li>
            <li><g:link controller="hut" action="picture" id="${hut.id}"><g:message code="hut.show.menu.upload" args="${[hut.name]}"/></g:link></li>
            <li><a href="#" onclick="return deleteCheckSubmit();"><g:message code="hut.show.menu.delete" args="${[hut.name]}"/></a></li>
            <li><g:link controller="booking" action="list" id="${hut.id}"><g:message code="hut.show.menu.manage" args="${[hut.name]}"/></g:link></li>
        </ul>
    </form>
</g:manageHut>

<div>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <table>
        <tbody>
            <tr valign="top">
                <g:render template="hutimage" var="hut" bean="${hut}"/>
                <g:render template="hutdetails" var="hut" bean="${hut}"/>
            </tr>
            <tr valign="top">
                <td>
                    <h4><g:message code="hut.show.title.bookings"/></h4>
                    <g:each var="b" in="${hut.bookings}">
                        <p>
                            <g:showBooking bookingId="${b.id}" userId="${session.userId}">
                                <g:link controller="booking" action="show" id="${b.id}">
                                    ${b.contact.name}:
                                    <g:formatDate date="${b.startDate}" format="dd/MM/yyyy"/>
                                    -
                                    <g:formatDate date="${b.endDate}" format="dd/MM/yyyy"/>
                                </g:link>
                            </g:showBooking>
                            <g:showBookingDate bookingId="${b.id}" userId="${session.userId}">
                                <g:formatDate date="${b.startDate}" format="dd/MM/yyyy"/>
                                -
                                <g:formatDate date="${b.endDate}" format="dd/MM/yyyy"/>
                            </g:showBookingDate>
                        </p>
                    </g:each>
                    <br/>
                    <p><g:link controller="booking" action="book" id="${hut.id}">Add Booking</g:link></p>

                </td>
                <td>
                    <h4><g:message code="hut.show.title.about" args="${[hut.name]}"/></h4>
                    <p>${hut.description}</p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <h4><g:message code="hut.show.title.availability"/></h4>

                    <p><g:message code="hut.show.text.availability"/></p>

                    <table class="datetable">
                        <tr>
                            <td width="50%" class="booked"><g:message code="hut.show.key.booked"/></td>
                            <td width="50%" class="bookedMe"><g:message code="hut.show.key.youbooked"/></td>
                        </tr>
                    </table>

                    <g:monthView hutId="${hut.id}" monthcount="4" userId="${session.userId}"/>
                </td>
            </tr>
        </tbody>
    </table>
</div>
</body>
</html>
