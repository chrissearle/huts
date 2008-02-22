

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show Person</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Person List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Person</g:link></span>
        </div>
        <div class="body">
            <h1>Show Person</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Name:</td>
                            
                            <td valign="top" class="value">${person.name}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Email:</td>
                            
                            <td valign="top" class="value"><a href="mailto:${person.email}">${person.email}</a></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Phone:</td>
                            
                            <td valign="top" class="value">${person.phone}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">User Id:</td>
                            
                            <td valign="top" class="value">${person.userId}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Owns:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="o" in="${person.owns}">
                                    <li><g:link controller="hut" action="show" id="${o.id}">${o}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${person?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
