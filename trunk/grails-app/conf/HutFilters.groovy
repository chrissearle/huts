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
                }
            }
        }
    }
}