<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title><g:message code="priceplan.create.title" args="[priceplan.hut]"/></title>
</head>
<body>
<div class="body">
    <h3 class="formtitle"><g:message code="priceplan.create.title" args="[priceplan.hut]"/></h3>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${priceplan}">
        <div class="errors">
            <g:renderErrors bean="${priceplan}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save" method="post">
        <g:hiddenField name="hut.id" value="${priceplan?.hut?.id}"/>
        <div>
            <table>
                <tbody>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="name"><g:message code="priceplan.shared.name"/></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: priceplan, field: 'name', 'errors')}">
                            <input type="text" id="name" name="name" value="${fieldValue(bean: priceplan, field: 'name')}"/>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="currency"><g:message code="priceplan.shared.currency"/></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: priceplan, field: 'currency', 'errors')}">
                            <g:select id="currency" name="currency" from="${priceplan.constraints.currency.inList.collect{it.encodeAsHTML()}}" value="${fieldValue(bean:priceplan,field:'currency')}"></g:select>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="price"><g:message code="priceplan.shared.price"/></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: priceplan, field: 'price', 'errors')}">
                            <input type="text" id="price" name="price" value="${fieldValue(bean: priceplan, field: 'price')}"/>
                        </td>
                    </tr>

                    <tr>
                        <td valign="top" class="name">
                            <label for="perHead"><g:message code="priceplan.shared.perHead"/></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: priceplan, field: 'perHead', 'errors')}">
                            <g:checkBox name="perHead" value="${priceplan?.perHead}"></g:checkBox>
                        </td>
                    </tr>

                    <tr>
                        <td valign="top" class="name" colspan="2">
                            <g:message code="priceplan.shared.perHead.details"/>
                        </td>
                    </tr>

                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><input class="save" type="submit" value="Create"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
