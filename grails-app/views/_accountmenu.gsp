<ul id="nav2">
    <g:hasHut userId="${session.userId}">
        <li><g:link controller="hut" action="list" params="[hutgroup:person.id]">My Huts</g:link></li>
    </g:hasHut>
    <g:hasBooking userId="${session.userId}">
        <li><g:link controller="booking" action="my" id="${person.id}">My Bookings</g:link></li>
    </g:hasBooking>
</ul>