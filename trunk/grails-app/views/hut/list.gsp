<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <title>Huts</title>
</head>
<body>

<ul id="nav2">
    <li><g:link controller="hut" action="create">Add hut</g:link></li>
</ul>

<div>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <table>
        <tbody>
            <g:each in="${hutList}" status="i" var="hut">
                <tr valign="top">
                    <g:if test="${(i % 2) == 0}">
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
                    </g:if>
                    <td>
                        <h3><g:link action="show" id="$hut.id">${hut.name}</g:link></h3>
                        <p>Contact: <g:link controller="person" action="show" id="$hut.owner.id">${hut.owner}</g:link></p>
                        <p>Location: ${hut.location}</p>
                        <p>Beds: ${hut.beds}</p>
                    </td>
                    <g:if test="${(i % 2) == 1}">
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
                    </g:if>
                </tr>
            </g:each>
        </tbody>
    </table>
</div>
</body>
</html>
