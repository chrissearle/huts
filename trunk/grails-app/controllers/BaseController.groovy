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
                        if (params.action != "editme") {
                            Person p = Person.findByUserId(session.userId)

                            if (!p.admin) {
                                redirect(controller: 'person', action: 'denied')

                                return false
                            }
                        }
                        break;
                    case "hut":
                        if (params.action == "edit" || params.action == "delete") {

                            Person p = Person.findByUserId(session.userId)

                            Hut hut = Hut.get(params.id)

                            if (!p.admin && !(p == hut.owner)) {
                                redirect(controller: 'hut', action: 'denied')

                                return false
                            }
                        }
                        break;
                    case "booking":
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
