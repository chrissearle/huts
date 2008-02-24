<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <title>User List</title>
</head>
<body>
<ul id="nav2">
    <li></span><g:link controller="person" action="create">Add user</g:link></li>
    <li><g:link controller="person" action="approval">Users waiting approval</g:link></li>
</ul>

<div>
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

                    <th>Contact for</th>

                </tr>
            </thead>
            <tbody>
                <g:each in="${personList}" status="i" var="person">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}" valign="top">

                        <td><g:link action="show" id="${person.id}">${person.name?.encodeAsHTML()}</g:link></td>

                        <td><a href="mailto:${person.email}">${person.email}</a></td>

                        <td>${person.phone?.encodeAsHTML()}</td>

                        <td>
                            <g:if test="${person?.owns?.size() > 0}">
                                <g:each var="o" in="${person.owns}">
                                    <g:link controller="hut" action="show" id="${o.id}">${o}</g:link><br/>
                                </g:each>
                            </g:if>
                        </td>

                    </tr>
                </g:each>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
