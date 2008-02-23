<html>
<head>
    <title><g:layoutTitle default="Grails"/></title>
    <link rel="stylesheet" href="${createLinkTo(dir: 'css', file: 'main.css')}"/>
    <link rel="shortcut icon" href="${createLinkTo(dir: 'images', file: 'favicon.ico')}" type="image/x-icon"/>
    <g:layoutHead/>
    <g:javascript library="application"/>
</head>
<body>
<div id="spinner" class="spinner" style="display:none;">
    <img src="${createLinkTo(dir: 'images', file: 'spinner.gif')}" alt="Spinner"/>
</div>
<div class="logo">
    <g:link controller="hut" action="list"><img height="80" src="${createLinkTo(dir: 'images', file: 'trollhaugen.jpg')}" alt="Huts"/></g:link>
    <g:link controller="hut" action="list"><img height="80" src="${createLinkTo(dir: 'images', file: 'title.jpg')}" alt="Huts"/></g:link>
</div>
<div class="nav">
    <g:if test="${!session.userId}">
        <span class="menuButton"><g:link controller="hut" action="list">Log In</g:link></span>
        <span class="menuButton"><g:link controller="person" action="register">Register</g:link></span>
    </g:if>
    <g:else>
        <span class="menuButton"><g:link controller="hut" action="list">Manage Huts</g:link></span>
        <span class="menuButton"><g:link controller="person" action="list">Manage Users</g:link></span>
        <span class="menuButton"><g:link controller="person" action="logout">Logout</g:link></span>
    </g:else>
</div>
<g:layoutBody/>
</body>
</html>