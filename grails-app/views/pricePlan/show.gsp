<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:javascript library="huts"/>
    <title><g:message code="priceplan.show.title" args="[priceplan.hut]"/></title>
</head>
<body>
<form method="post" action="${createLink(controller: 'pricePlan', action: 'delete')}" name="deletemenuform">
    <input type="hidden" name="id" value="${priceplan?.id}"/>
    <ul id="nav2">
        <li><g:link controller="pricePlan" action="edit" id="${priceplan.id}"><g:message code="priceplan.show.menu.edit"/></g:link></li>
        <li><a href="#" onclick="return deleteCheckSubmit();"><g:message code="priceplan.show.menu.delete"/></a></li>
    </ul>
</form>
<div>
    <h3 class="formtitle"><g:message code="priceplan.show.title" args="[priceplan.hut]"/></h3>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div>
        <table>
            <tbody>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="priceplan.shared.name"/></td>

                    <td valign="top" class="value">${priceplan.name}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="priceplan.shared.currency"/></td>

                    <td valign="top" class="value">${priceplan.currency}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="priceplan.shared.price"/></td>

                    <td valign="top" class="value">${priceplan.price}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="priceplan.shared.perHead"/></td>

                    <td valign="top" class="value">${priceplan.perHead}</td>

                </tr>

            </tbody>
        </table>
    </div>
</div>
</body>
</html>
