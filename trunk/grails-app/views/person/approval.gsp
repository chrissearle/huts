<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Person List</title>
</head>
<body>
<div class="body">
    <h1>Approval List</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
                <tr>

                    <g:sortableColumn property="name" title="Name"/>

                    <g:sortableColumn property="email" title="Email"/>

                    <g:sortableColumn property="phone" title="Phone"/>

                    <th></th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${personList}" status="i" var="person">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                        <td>${person.name?.encodeAsHTML()}</td>

                        <td><a href="mailto:${person.email}">${person.email}</a></td>

                        <td>${person.phone?.encodeAsHTML()}</td>

                        <td><g:link action="approval" id="${person.id}">Approve</g:link></td>

                    </tr>
                </g:each>
            </tbody>
        </table>
    </div>
    <div class="paginateButtons">
        <g:paginate total="${Person.count()}"/>
    </div>
</div>
</body>
</html>
