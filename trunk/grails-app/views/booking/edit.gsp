

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Booking</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Booking List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Booking</g:link></span>
        </div>
        <div class="body">
            <h1>Edit Booking</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${booking}">
            <div class="errors">
                <g:renderErrors bean="${booking}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${booking?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="hut">Hut:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:booking,field:'hut','errors')}">
                                    <g:select optionKey="id" from="${Hut.list()}" name="hut.id" value="${booking?.hut?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="contact">Contact:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:booking,field:'contact','errors')}">
                                    <g:select optionKey="id" from="${Person.list()}" name="contact.id" value="${booking?.contact?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="startDate">Start Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:booking,field:'startDate','errors')}">
                                    <g:datePicker name="startDate" value="${booking?.startDate}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="endDate">End Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:booking,field:'endDate','errors')}">
                                    <g:datePicker name="endDate" value="${booking?.endDate}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
