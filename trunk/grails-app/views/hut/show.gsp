<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <g:javascript library="huts"/>
    <title>${hut.name}</title>
</head>
<body>

<g:manageHut hutId="${hut.id}" userId="${session.userId}">
    <form method="post" action="${createLink(controller: 'hut', action: 'delete')}" name="deletemenuform">
        <input type="hidden" name="id" value="${hut?.id}"/>

        <ul id="nav2">
            <li><g:link controller="hut" action="edit" id="${hut.id}">Edit ${hut.name}</g:link></li>
            <li><g:link controller="hut" action="picture" id="${hut.id}">Upload picture for ${hut.name}</g:link></li>
            <li><a href="#" onclick="return deleteCheckSubmit();">Delete ${hut.name}</a></li>
            <li><g:link controller="booking" action="list" id="${hut.id}">Manage Bookings</g:link></li>
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
                <td>
                    <g:if test="${hut.image}">
                        <g:link action="show" id="$hut.id"><img src="${createLink(action: 'showpic')}/${hut.id}" alt="hut" width="300"/></g:link>
                    </g:if>
                    <g:else>
                        <div class="unavailable">
                            No image available
                        </div>
                    </g:else>
                </td>
                <td>
                    <h3><g:link action="show" id="$hut.id">${hut.name}</g:link></h3>
                    <p>Contact: <g:link controller="person" action="show" id="$hut.owner.id">${hut.owner}</g:link></p>
                    <p>Location: ${hut.location}</p>
                    <p>Beds: ${hut.beds}</p>
                </td>
            </tr>
            <tr valign="top">
                <td>
                    <h4>Bookings</h4>
                    <g:each var="b" in="${hut.bookings}">
                        <p>
                            <g:showBooking bookingId="${b.id}" userId="${session.userId}">
                                <g:link controller="booking" action="show" id="${b.id}">
                                    ${b.contact.name}:
                                    <g:formatDate date="${b.startDate}" format="yyyy-MM-dd"/>
                                    -
                                    <g:formatDate date="${b.endDate}" format="yyyy-MM-dd"/>
                                </g:link>
                            </g:showBooking>
                            <g:showBookingDate bookingId="${b.id}" userId="${session.userId}">
                                <g:formatDate date="${b.startDate}" format="yyyy-MM-dd"/>
                                -
                                <g:formatDate date="${b.endDate}" format="yyyy-MM-dd"/>
                            </g:showBookingDate>
                        </p>
                    </g:each>
                    <br/>
                    <p><g:link controller="booking" action="book" id="${hut.id}">Add Booking</g:link></p>

                </td>
                <td>
                    <h4>About ${hut.name}</h4>
                    <p>${hut.description}</p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <h4>Availability</h4>

                    <p>This chart shows only nights - so day of departure is not shown</p>

                    <table class="datetable">
                        <tr><td width="50%" class="booked">Booked</td><td width="50%" class="bookedMe">Booked by you</td></tr>
                    </table>

                    <g:monthView hutId="${hut.id}" monthcount="4" userId="${session.userId}"/>
                </td>
            </tr>
        </tbody>
    </table>
</div>
</body>
</html>
