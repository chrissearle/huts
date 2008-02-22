<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Booking List</title>
</head>
<body>
<div class="body">
    <h1>Booking List for ${hut}</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
                <tr>

                    <th>Contact</th>

                    <g:sortableColumn property="startDate" title="Start Date"/>

                    <g:sortableColumn property="endDate" title="End Date"/>

                    <th></th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${bookingList}" status="i" var="booking">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                        <td><g:link controller="person" action="show" id="${booking?.contact?.id}">${booking.contact?.encodeAsHTML()}</g:link></td>

                        <td><g:formatDate date="${booking.startDate}" format="yyyy-MM-dd"/></td>

                        <td><g:formatDate date="${booking.endDate}" format="yyyy-MM-dd"/></td>

                        <td><g:link action="edit" id="${booking.id}">Edit</g:link></td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </div>
    <div class="paginateButtons">
        <g:paginate total="${Booking.count()}"/>
    </div>
</div>
</body>
</html>
