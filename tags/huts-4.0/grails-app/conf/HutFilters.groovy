class HutFilters {
    def filters = {
        // First we store off any sticky params
        hutgroup(controller: '*', action: '*') {
            before = {
                if (params.hutgroup) {
                    session.setAttribute('hutgroup', params.hutgroup)
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
        // Then we check if login is required
        loginCheck(controller: '*', action: '*') {
            before = {
                log.info("In loginCheck")
                if (!session.userId) {
                    log.info("In loginCheck - not logged in")
                    if (!(actionName == "denied" ||
                            controllerName == "welcome" ||
                            (controllerName == "hut" &&
                                    (actionName == "list" ||
                                            actionName == "show" ||
                                            actionName == "showpic")) ||
                            (controllerName == "person" &&
                                    (actionName == null ||
                                            actionName == "login" ||
                                            actionName == "logout" ||
                                            actionName == "register" ||
                                            actionName == "forgotten" ||
                                            actionName == "confirm")))) {

                        log.info("In loginCheck - login required " + controllerName + " " + actionName)

                        def originalRequestParams = [controller: controllerName, action: actionName]

                        originalRequestParams.putAll(params)

                        session.originalRequestParams = originalRequestParams

                        redirect(controller: 'person', action: 'login')

                        return false
                    }
                }
            }
        }
        accessCheckHut(controller: 'hut', action: '*') {
            before = {
                log.info("In accessCheckHut")
                if (!session.userId) {
                    if (actionName == "show" || actionName == "showpic") {
                        Hut hut = Hut.get(params.id)

                        if (!hut.openHut) {
                            redirect(controller: 'hut', action: 'denied')

                            return false
                        }
                    }
                } else {
                    switch (actionName) {
                        case "edit":
                        case "update":
                        case "delete":
                            Person p = Person.findByUserId(session.userId)

                            if (!p.admin) {
                                Hut hut = Hut.get(params.id)

                                if (p != hut.owner) {
                                    redirect(controller: 'hut', action: 'denied')

                                    return false
                                }
                            }
                            break;
                        case "show":
                        case "showpic":
                            Person p = Person.findByUserId(session.userId)

                            if (!p.admin) {
                                Hut hut = Hut.get(params.id)

                                if (!(hut.openHut || (p == hut.owner) || hut.users.users.contains(p))) {
                                    redirect(controller: 'hut', action: 'denied')

                                    return false
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        accessCheckBooking(controller: 'booking', action: '*') {
            before = {
                log.info("In accessCheckBooking")
                if (session.userId) {
                    Person p = Person.findByUserId(session.userId)

                    if (!p.admin) {
                        switch (actionName) {
                            case "show":
                            case "edit":
                            case "update":
                                Booking b = Booking.get(params.id)

                                if (!((p == b.hut.owner) || (p == b.contact))) {
                                    redirect(controller: 'booking', action: 'denied')

                                    return false
                                }
                                break;

                            case "list":
                                Hut hut = Hut.get(params.id)

                                if (p != hut.owner) {
                                    redirect(controller: 'hut', action: 'denied')

                                    return false
                                }
                                break;

                            default:
                                break;
                        }
                        // TODO - create & save: see Trac #17 - needs this in place before an access check is meaningful - will be hut id
                    }
                }
            }
        }
        accessCheckPerson(controller: 'person', action: '*') {
            before = {
                log.info("In accessCheckPerson")
                if (session.userId) {
                    Person p = Person.findByUserId(session.userId)

                    if (!p.admin && params.id) {
                        Person p2 = Person.get(params.id)

                        if (p.id != p2.id) {
                            redirect(controller: 'person', action: 'denied')

                            return false
                        }
                    }
                }
            }
        }
    }
}