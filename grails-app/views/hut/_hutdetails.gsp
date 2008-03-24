<td>
    <h3><g:link action="show" id="$hut.id">${hut.name}</g:link></h3>
    <g:if test="${hut.owner.organization}">
        <p><g:message code="hut.shared.organization"/> ${hut.owner.organization.encodeAsHTML()}</p>
    </g:if>
    <p><g:message code="hut.shared.contact"/>
    <g:if test="${hut.owner.published}">
        <g:link controller="person" action="show" id="$hut.owner.id">${hut.owner.encodeAsHTML()}</g:link>
    </g:if>
    <g:else>
        <g:link controller="person" action="contact" id="$hut.owner.id">${hut.owner.encodeAsHTML()}</g:link>
    </g:else>
    </p>
    <p><g:message code="hut.shared.location"/> ${hut.location.encodeAsHTML()}</p>
    <p><g:message code="hut.shared.beds"/> ${hut.beds.encodeAsHTML()}</p>
</td>