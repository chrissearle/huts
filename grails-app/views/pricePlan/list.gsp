<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title><g:message code="priceplan.list.title" args="[hut]"/></title>
</head>
<body>
<ul id="nav2">
    <li><g:link controller="hut" action="show" id="${hut.id}"><g:message code="priceplan.list.menu.hut" args="[hut.name]"/></g:link></li>
    <li><g:link action="create" id="${hut.id}"><g:message code="priceplan.list.menu.create"/></g:link></li>
</ul>
<div>
    <h3 class="formtitle"><g:message code="priceplan.list.title" args="[hut]"/></h3>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
                <tr>

                    <g:sortableColumn property="name" titleKey="priceplan.shared.name"/>

                    <g:sortableColumn property="currency" titleKey="priceplan.shared.currency"/>

                    <g:sortableColumn property="price" titleKey="priceplan.shared.price"/>

                    <g:sortableColumn property="perHead" titleKey="priceplan.shared.perHead"/>

                    <th></th>

                    <th></th>

                </tr>
            </thead>
            <tbody>
                <g:each in="${pricePlanList}" status="i" var="pricePlan">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                        <td>${pricePlan.name?.encodeAsHTML()}</td>

                        <td>${pricePlan.currency?.encodeAsHTML()}</td>

                        <td>${pricePlan.price?.encodeAsHTML()}</td>

                        <td>${pricePlan.perHead?.encodeAsHTML()}</td>

                        <td><g:link action="show" id="${pricePlan.id}"><g:message code="priceplan.shared.show"/></g:link></td>

                        <td><g:link action="edit" id="${pricePlan.id}"><g:message code="priceplan.shared.edit"/></g:link></td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </div>
    <div class="paginateButtons">
        <g:paginate total="${PricePlan.count()}"/>
    </div>
</div>
</body>
</html>
