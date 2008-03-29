<td>
    <g:if test="${hut.image}">
        <div id="map" class="showmap"></div>
        <g:link action="show" id="$hut.id"><img id="hutimg" style="display: none;" src="${createLink(action: 'showpic', id: hut.id)}" alt="${hut.name}" width="300"/></g:link>
        <p><a href="javascript:$('#map,#hutimg').toggle();"><g:message code="hut.show.toggle"/></a></p>
    </g:if>
    <g:else>
        <div id="map" class="showmap"></div>
    </g:else>

</td>
