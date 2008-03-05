<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <title><g:message code="hut.picture.title" args="${[hut.name]}"/></title>
</head>
<body>
<div class="body">
    <h3 class="formtitle"><g:message code="hut.picture.title" args="${[hut.name]}"/></h3>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${hut}">
        <div class="errors">
            <g:renderErrors bean="${hut}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form method="post" action="picture" enctype="multipart/form-data">
        <g:hiddenField value="${hut.id}" name="id"/>
        <div class="dialog">
            <table>
                <tbody>
                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="file"><g:message code="hut.shared.file"/></label>
                        </td>
                        <td valign="top" class="value">
                            <input type="file" name="file"/>
                        </td>
                    </tr>
                </tbody>
            </table>

        </div>
        <div class="buttons">
            <span class="button"><input class="save" type="submit" value="Upload"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
