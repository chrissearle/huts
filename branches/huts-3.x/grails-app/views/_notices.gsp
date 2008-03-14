<g:if test="${notices}"><div class="notices">
<g:each var="notice" in="${notices}">
    <h4 class="formtitle">${notice.title}</h4>

    <p class="formtext">${notice.text}</p>
</g:each>
</div></g:if>