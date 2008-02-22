

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Hut List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Hut</g:link></span>
        </div>
        <div class="body">
            <h1>Hut List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="location" title="Location" />
                        
                   	        <th>Owner</th>

                            <th>Bookings</th>

                            <th></th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${hutList}" status="i" var="hut">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                            <td>${hut.name?.encodeAsHTML()}</td>
                        
                            <td>${hut.location?.encodeAsHTML()}</td>
                        
                            <td><g:link controller="person" action="show" id="${hut?.owner?.id}">${hut.owner?.encodeAsHTML()}</g:link></td>

                            <td>
                                <g:if test="${hut?.bookings?.size()}">
                                    <g:link controller="booking" action="list" id="${hut?.id}">Bookings</g:link>
                                </g:if>
                                <br/>
                                <g:link controller="booking" action="create" id="${hut?.id}">Add</g:link>
                            </td>

                            <td><g:link action="edit" id="${hut.id}">Edit</g:link></td>

                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Hut.count()}" />
            </div>
        </div>
    </body>
</html>
