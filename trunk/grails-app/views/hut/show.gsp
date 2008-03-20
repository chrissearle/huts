<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:javascript library="huts"/>
    <script type="text/javascript" src="http://www.google.com/jsapi?key=${grailsApplication.config.google.map.key}"></script> <!-- add &hl=lang here -->
    <script type="text/javascript">
    google.load("maps", "2.x");

    function initialize() {
    initializeSingleMap("${hut.latitude}","${hut.longitude}",
    <g:if test="${hut.owner.userId == session.userId}">"OWNER"</g:if>
        <g:else>
            <g:if test="${hut.openHut}">"PUBLIC"</g:if>
            <g:else>"PRIVATE"</g:else>
        </g:else>);
    }

    google.setOnLoadCallback(initialize);
    </script>
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
            <g:if test="${!hut.openHut}">
                <li><g:link controller="hut" action="userlist" id="${hut.id}"><g:message code="hut.show.menu.userlist"/></g:link></li>
            </g:if>
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
                    <p><g:link controller="booking" action="book" id="${hut.id}"><g:message code="hut.show.booking.link"/></g:link></p>

                </td>
                <td>
                    <h4><g:message code="hut.show.title.about" args="${[hut.name]}"/></h4>
                    <p>${hut.description.encodeAsHTML()}</p>
                </td>
            </tr>
<g:manageHut hutId="${hut.id}" userId="${session.userId}">
            <tr>
                <td colspan="2">
                    <h4><g:message code="hut.show.title.links"/></h4>
                    <ul>
                        <li><a href="${createLink(controller: 'hut', action: 'list')}?hutgroup=${hut.owner.id}"><g:message code="hut.show.link.group"/></a></li>
                        <li><a href="${createLink(controller: 'hut', action: 'list')}?centerHut=${hut.id}"><g:message code="hut.show.link.hut"/></a></li>
                        <li><a href="${createLink(controller: 'hut', action: 'list')}?hutgroup=${hut.owner.id}&centerHut=${hut.id}"><g:message code="hut.show.link.group.and.hut"/></a></li>
                    </ul>
                </td>
            </tr>
</g:manageHut>
            <tr>
                <td colspan="2">
                    <g:render template="/availabilityShort" var="hut" bean="${hut}"/>
                </td>
            </tr>
        </tbody>
    </table>
</div>
</body>
</html>
