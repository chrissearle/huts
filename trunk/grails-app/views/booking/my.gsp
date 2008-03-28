<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title><g:message code="booking.list.title" args="[person.name]"/></title>
</head>
<body>
<g:if test="${session.userId == person.userId}">
    <g:render template="/accountmenu" var="person" bean="${person}"/>
</g:if>
<div>
    <h3 class="formtitle"><g:message code="booking.list.title" args="[person.name]"/></h3>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <table>
        <thead>
            <tr>

                <g:sortableColumn property="startDate" titleKey="booking.shared.startdate"/>

                <g:sortableColumn property="endDate" titleKey="booking.shared.enddate"/>

                <th><g:message code="booking.shared.people"/></th>

                <th></th>

                <th></th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${bookingList}" status="i" var="booking">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:formatDate date="${booking.startDate}" format="dd/MM/yyyy"/></td>

                    <td><g:formatDate date="${booking.endDate}" format="dd/MM/yyyy"/></td>

                    <td>${booking.peopleCount?.encodeAsHTML()}</td>

                    <td><g:link action="show" id="${booking.id}"><g:message code="booking.shared.show"/></g:link></td>
                    <td><g:link action="edit" id="${booking.id}"><g:message code="booking.shared.edit"/></g:link></td>
                </tr>
            </g:each>
        </tbody>
    </table>
</div>
</body>
</html>
