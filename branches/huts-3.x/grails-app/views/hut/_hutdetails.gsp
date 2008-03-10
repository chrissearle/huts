<td>
    <h3><g:link action="show" id="$hut.id">${hut.name}</g:link></h3>
    <p><g:message code="hut.shared.contact"/> <g:link controller="person" action="show" id="$hut.owner.id">${hut.owner}</g:link></p>
    <p><g:message code="hut.shared.location"/> ${hut.location}</p>
    <p><g:message code="hut.shared.beds"/> ${hut.beds}</p>
</td>