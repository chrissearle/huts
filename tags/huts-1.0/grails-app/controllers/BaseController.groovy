abstract class BaseController {
    def auth = {
        if (!session.userId) {
            def originalRequestParams = [controller: controllerName, action: actionName]

            originalRequestParams.putAll(params)

            session.originalRequestParams = originalRequestParams

            redirect(controller: 'person', action: 'login')

            return false
        } else {
            if (params.controller) {
                switch (params.controller) {
                    case "person":
                        Person p = Person.findByUserId(session.userId)

                        if (params.id) {
                            Person p2 = Person.get(params.id)

                            if (!p.admin) {

                                if (p.id != p2.id) {
                                    log.error("PARAMS: ${params}");

                                    redirect(controller: 'person', action: 'denied')

                                    return false
                                }
                            }
                        }
                        break;
                    case "hut":
                        if (params.action == "edit" || params.action == "delete") {

                            Person p = Person.findByUserId(session.userId)

                            Hut hut = Hut.get(params.id)

                            if (!(p.admin || (p == hut.owner))) {
                                redirect(controller: 'hut', action: 'denied')

                                return false
                            }
                        }
                        break;
                    case "booking":
                        Person p = Person.findByUserId(session.userId)

                        if (params.action == "show" || params.action == "edit") {
                            Booking b = Booking.get(params.id)

                            if (!(p.admin || (p == b.hut.owner) || (p == b.contact))) {
                                redirect(controller: 'booking', action: 'denied')

                                return false
                            }
                        }
                        if (params.action == "list") {
                            Hut hut = Hut.get(params.id)

                            if (!(p.admin || (p == hut.owner))) {
                                redirect(controller: 'hut', action: 'denied')

                                return false
                            }
                        }
                        // TODO - create: see Trac #17 - needs this in place before an access check is meaningful - will be hut id

                        break;
                    default: // NOP
                        break;
                }
            }
        }
    }

    def isadmin = {
        if (session.userId) {
            def user = Person.getByUserId(session.userId)

            return user.admin
        }

        return false
    }

    def denied = {}
}
