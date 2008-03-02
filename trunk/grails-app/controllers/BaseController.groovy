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
                if (params.controller == "person") {
                    if (params.action != "editme") {
                        Person p = Person.findByUserId(session.userId)

                        if (!p.admin) {
                            redirect(controller: 'person', action: 'denied')

                            return false
                        }
                    }
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
}
