class PersonController extends BaseController {
    def emailService
    def randomService
    def templateService

    def scaffold = true

    def login = {
        if (request.method == "GET") {
            session.userId = null
            session.userPK = null
            def user = new Person()
        } else {
            def user = Person.findByUserIdAndPassword(params.userId,
                    params.password)

            if (user) {
                if (user.approved && user.confirmed) {
                    session.userId = user.userId
                    session.userPK = user.id

                    def redirectParams = session.originalRequestParams

                    session.originalRequestParams = null

                    if (redirectParams == null) {
                        redirect(controller: 'hut', action: 'list')
                    } else {
                        redirect(redirectParams)
                    }
                } else {
                    if (!user.approved) {
                        flash['message'] = message(code: "user.not.approved")
                    }
                    if (!user.confirmed) {
                        flash['notconf'] = user.id
                        flash['message'] = message(code: "user.not.confirmed")
                    }
                }
            } else {
                flash['message'] = message(code: "user.login.invalid")
            }
        }
    }

    def resend = {
        def user = Person.get(params.id)

        if (user) {
            user.challenge = randomService.getRandomKey(40)
            user.save(flush: true)

            def messageText = templateService.processTemplate("mailTemplates",
                    message(code: "user.new.confirmation.file"),
                    ["user": user])

            emailService.sendMail(message(code: "user.new.confirmation.subject"),
                    messageText, [user], [])

            flash['message'] = message(code: "user.confirm.resent")
        }
        redirect(controller: 'person', action: 'login')
    }

    def logout = {
        session.userId = null
        session.originalRequestParams = null

        flash['message'] = message(code: "user.logout.loggedout")
        redirect(controller: 'hut', action: 'list')
    }

    def approval = {
        def userid = params.id

        if (userid) {
            Person user = Person.get(userid)
            user.approved = true
            if (user.save(flush: true)) {
                flash['message'] = message(code: "user.approval.approved", args: [user.name])
            } else {
                flash['message'] = message(code: "user.approval.failed", args: [user.name])
            }
        }
        return [personList: Person.findAllByApproved(false)]
    }

    def register = {
        if (request.method == "GET") {
            def user = new Person()
            flash['funnel'] = '/funnel/register/step1.html'
        } else {
            def user = new Person()
            user.properties = params
            user.admin = false
            user.approved = true
            user.confirmed = false
            user.challenge = randomService.getRandomKey(40)

            if (user.save()) {
                def admins = Person.findAllByAdmin(true)

                def adminMessageText = templateService.processTemplate("mailTemplates",
                        message(code: "user.new.notification.subject"),
                        ["user": user])

                emailService.sendMail(message(code: "user.new.confirmation.subject"),
                        adminMessageText, [admins], [])


                def messageText = templateService.processTemplate("mailTemplates",
                        message(code: "user.new.confirmation.file"),
                        ["user": user])

                emailService.sendMail(message(code: "user.new.confirmation.subject"),
                        messageText, [user], [])

                flash['message'] = message(code: "user.account.created")
                flash['funnel'] = '/funnel/register/step2.html'
                redirect(controller: 'person', action: 'login')
            } else {
                flash['funnel'] = '/funnel/register/step1.html'
                render(view: 'register', model: [person: user])
            }
        }
    }

    def delete = {
        def person = Person.get(params.id)

        if (person) {
            flash.message = message(code: "user.deleted", args: [person.name])

            person.delete()

            redirect(controller: 'person', action: 'list')
        }
        else {
            flash.message = message(code: "user.not.found")

            redirect(controller: 'person', action: 'list')
        }
    }

    def forgotten = {
        if (request.method == "GET") {
            def user = new Person()
        } else {
            def user = Person.findByUserId(params.userId)

            if (!user) {
                user = Person.findByEmail(params.email)
            }

            if (user) {
                def messageText = templateService.processTemplate("mailTemplates",
                        message(code: "user.forgotten.password.file"), ["user": user])

                emailService.sendMail(message(code: "user.forgotten.password.subject"), messageText, [user], [])

                flash['message'] = message(code: "user.password.sent")

                redirect(controller: 'person', action: 'login')
            } else {
                flash['message'] = message(code: "user.not.found")

                redirect(controller: 'person', action: 'forgotten')
            }
        }
    }

    def confirm = {
        def user = Person.get(params.id)

        if (user && params.key) {
            if (user.challenge.equals(params.key)) {
                user.confirmed = true
                user.challenge = ""

                if (user.save()) {
                    flash['message'] = message(code: 'user.confirm.confirmed')
                }
            }
        }

        redirect(controller: 'person', action: 'login')
    }

    def contact = {
        def user = Person.get(params.id)
        def contactMessage = new ContactMessage()
        if (params.subject) {
            contactMessage.subject = params.subject
        }
        if (params.messageContent) {
            contactMessage.messageContent = params.messageContent
        }

        if (user) {
            if (request.method == "POST") {
                def contacter = Person.findByUserId(session.userId)

                def messageText = templateService.processTemplate("mailTemplates", message(code: "user.contact.file"),
                        [contact: contacter, contactMessage: contactMessage])

                emailService.sendMail(message(code: "user.contact.subject"), messageText, [user], [])

                flash['message'] = message(code: "user.contact.sent", args: [user.name])
                redirect(controller: 'hut', action: 'list')
            } else {
                return [user: user, contactMessage: contactMessage]
            }
        } else {
            flash['message'] = message(code: "user.not.found")
            redirect(controller: 'hut', action: 'list')
        }
    }
}
