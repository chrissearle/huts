<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Show Hut</title>
</head>
<body>
<div class="body">
    <h1>${hut.name}</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>
                <tr class="prop">
                    <td valign="top" class="name">Image:</td>

                    <td valign="top" class="value">
                        <g:if test="${hut.image}">
                            <img src="${createLink(action: 'showpic')}/${hut.id}" alt="hut" height="80"/>
                            <br/>
                        </g:if>
                        <span class="button"><g:link controller="hut" action="picture" id="${hut.id}">Set image</g:link></span>
                    </td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name">Location:</td>

                    <td valign="top" class="value">${hut.location}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name">Description:</td>

                    <td valign="top" class="value">${hut.description}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name">Beds:</td>

                    <td valign="top" class="value">${hut.beds}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name">Contact:</td>

                    <td valign="top" class="value"><g:link controller="person" action="show" id="${hut?.owner?.id}">${hut?.owner}</g:link></td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name">Bookings:</td>

                    <td valign="top" style="text-align:left;" class="value">
                        <ul>
                            <g:each var="b" in="${hut.bookings}">
                                <li><g:link controller="booking" action="show" id="${b.id}">${b}</g:link></li>
                            </g:each>
                        </ul>
                        <g:link controller="booking" action="book" id="${hut.id}">Add Booking</g:link>
                    </td>

                </tr>

            </tbody>
        </table>
    </div>
    <div class="buttons">
        <g:form>
            <input type="hidden" name="id" value="${hut?.id}"/>
            <span class="button"><g:actionSubmit class="edit" value="Edit"/></span>
            <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete"/></span>
        </g:form>
    </div>
</div>
</body>
</html>
