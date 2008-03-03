<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="summer-days"/>
    <title>Upload hut picture</title>
</head>
<body>
<div>
    <h3 class="formtitle">${hut.name} - upload picture</h3>
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
                            <label for="file">Image file:</label>
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
