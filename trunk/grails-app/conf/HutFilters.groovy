class HutFilters {
    def filters = {
        stickyParams(controller: '*', action: '*') {
            before = {
                if (params.hutgroup) {
                    session.setAttribute('hutgroup', params.hutgroup)
                }
            }
        }
        clearStickyParams(controller: '*', action: '*') {
            before = {
                if (params.clearSticky) {
                    session.removeAttribute('hutgroup')
                    session.removeAttribute('zoom')
                    session.removeAttribute('centerHut')
                    session.removeAttribute('latitude')
                    session.removeAttribute('longitude')
                }
            }
        }
        zoom(controller: 'hut', action: 'list') {
            before = {
                if (params.zoom) {
                    session.setAttribute('zoom', params.zoom)
                }
            }
        }
        centerHut(controller: 'hut', action: 'list') {
            before = {
                if (params.centerHut) {
                    session.setAttribute('centerHut', params.centerHut)
                }
            }
        }
        centerPoint(controller: 'hut', action: 'list') {
            before = {
                if (params.latitude && params.longitude) {
                    session.setAttribute('latitude', params.latitude)
                    session.setAttribute('longitude', params.longitude)
                }
            }

        }
    }
}