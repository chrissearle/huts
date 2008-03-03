<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"><html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"><head>    <title><g:layoutTitle default="Grails"/></title>    <link rel="stylesheet" href="${createLinkTo(dir: 'css', file: 'sd-stylesheet.css')}" type="" text/css"/>    <link rel="shortcut icon" href="${createLinkTo(dir: 'images', file: 'favicon.ico')}" type="image/x-icon"/>    <g:layoutHead/>    <g:javascript library="application"/></head><body><div id="container">    <div id="header">        <h1>Huts</h1>    </div>    <ul id="nav">        <g:if test="${!session.userId}">            <li><g:link controller="hut" action="list">Log In</g:link></li>            <li><g:link controller="person" action="register">Register</g:link></li>            <li><g:link controller="person" action="forgotten">Forgotten Password</g:link></li>        </g:if>        <g:else>            <li><g:link controller="hut" action="list">Home</g:link></li>            <g:isAdmin userId="${session.userId}">                <li><g:link controller="person" action="list">Manage Users</g:link></li>            </g:isAdmin>            <li><g:link controller="person" action="edit" id="${session.userPK}">My Account</g:link></li>            <li><g:link controller="person" action="logout">Logout</g:link></li>        </g:else>    </ul>    <g:layoutBody/>    <div id="footer">        <a href="https://svn.chrissearle.net/trac/chrissearle.net/">Trac Support System</a> |        <a href="mailto:support-huts@chrissearle.org">Mail Support</a>        <br/>        Design by <a href="http://www.diagnosticdesign.co.uk">Diagnostic Design</a>    </div></div></body></html>