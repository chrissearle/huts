<td>
    <g:if test="${hut.image}">
        <g:link action="show" id="$hut.id"><img id="hutimg" src="${createLink(action: 'showpic')}/${hut.id}" alt="${hut.name}" width="300"/></g:link>
    </g:if>
    <g:else>
        <div class="unavailable">
            <g:message code="hut.shared.image.unavailable"/>
        </div>
    </g:else>
</td>
