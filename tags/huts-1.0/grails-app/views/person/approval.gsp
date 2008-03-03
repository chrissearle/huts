<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <title>People waiting approval</title>
</head>
<body>
<div>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
                <tr>

                    <th>Name</th>

                    <th>Email</th>

                    <th>Phone</th>

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
</div>
</body>
</html>
