class PersonController extends BaseController {

    def beforeInterceptor = [action: this.&auth, except: ['login', 'logout']]

    def scaffold = true


    def login = {
        if (request.method == "GET") {
            session.userId = null
            def user = new Person()
        } else {
            def user = Person.findByUserIdAndPassword(params.userId,
                    params.password)

            if (user) {
                session.userId = user.userId

                def redirectParams = session.originalRequestParams ? session.originalRequestParams : redirect(controller: 'huts', action: 'list')

                redirect(redirectParams)
            } else {
                flash['message'] = 'Please enter a valid user id and password'
            }
        }
    }

    def logout = {
        session.userId = null
        flash['message'] = 'Successfully logged out'
        redirect(controller: 'huts', action: 'list')
    }
}
