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
                if (user.approved) {
                    session.userId = user.userId

                    def redirectParams = session.originalRequestParams ? session.originalRequestParams : redirect(controller: 'hut', action: 'list')

                    redirect(redirectParams)
                } else {
                    flash['message'] = "Sorry, your account has not yet been approved by an administrator"
                }
            } else {
                flash['message'] = "Please enter a valid user id and password"
            }
        }
    }

    def logout = {
        session.userId = null
        flash['message'] = 'Successfully logged out'
        redirect(controller: 'hut', action: 'list')
    }

    def approval = {
        def userid = params.id

        if (userid) {
            Person user = Person.get(userid)
            user.approved = true
            if (user.save(flush: true)) {
                flash['message'] = "User ${user.name} successfully approved"
            } else {
                flash['message'] = "User ${user.name} was not saved"
                /*user.errors.each {
                      flash['message'] = flash['message'] + it + "<br/>"
                 } */
            }
        }
        return [personList: Person.findAllByApproved(false)]
    }
}
