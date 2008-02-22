abstract class BaseController {
    def auth = {
        if (!session.userId) {
            def originalRequestParams = [controller: controllerName, action: actionName]

            originalRequestParams.putAll(params)

            session.originalRequestParams = originalRequestParams

            redirect(controller: 'person', action: 'login')

            return false
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
