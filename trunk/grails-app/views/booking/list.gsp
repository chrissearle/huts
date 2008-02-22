

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Booking List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Booking</g:link></span>
        </div>
        <div class="body">
            <h1>Booking List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Hut</th>
                   	    
                   	        <th>Contact</th>
                   	    
                   	        <g:sortableColumn property="startDate" title="Start Date" />
                        
                   	        <g:sortableColumn property="endDate" title="End Date" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${bookingList}" status="i" var="booking">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${booking.id}">${booking.id?.encodeAsHTML()}</g:link></td>
                        
                            <td><g:link controller="hut" action="list">${booking.hut?.encodeAsHTML()}</g:link></td>
                        
                            <td><g:link controller="person" action="show" id="${booking?.contact?.id}">${booking.contact?.encodeAsHTML()}</g:link></td>
                        
                            <td>${booking.startDate?.encodeAsHTML()}</td>
                        
                            <td>${booking.endDate?.encodeAsHTML()}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Booking.count()}" />
            </div>
        </div>
    </body>
</html>
