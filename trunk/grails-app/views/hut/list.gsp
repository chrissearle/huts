<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Hut List</title>
</head>
<body>
<div class="body">
    <h1>Hut List</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
                <tr>

                    <g:sortableColumn property="name" title="Name"/>

                    <g:sortableColumn property="location" title="Location"/>

                    <g:sortableColumn property="beds" title="Beds"/>

                    <th>Owner</th>

                    <th>Bookings</th>

                </tr>
            </thead>
            <tbody>
                <g:each in="${hutList}" status="i" var="hut">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                        <td><g:link action="show" id="${hut.id}">${hut.name?.encodeAsHTML()}</g:link></td>

                        <td>${hut.location?.encodeAsHTML()}</td>

                        <td>${hut.beds?.encodeAsHTML()}</td>

                        <td><g:link controller="person" action="show" id="${hut?.owner?.id}">${hut.owner?.encodeAsHTML()}</g:link></td>

                        <td>
                            <g:if test="${hut?.bookings?.size()}">
                                <g:link controller="booking" action="list" id="${hut?.id}">Existing Bookings</g:link>
                                <br/>
                            </g:if>
                            <g:link controller="booking" action="book" id="${hut?.id}">Book</g:link>
                        </td>

                    </tr>
                </g:each>
            </tbody>
        </table>
    </div>
    <div class="paginateButtons">
        <g:link controller="hut" action="create">Add hut</g:link>
        <g:paginate total="${Hut.count()}"/>
    </div>
</div>
</body>
</html>
