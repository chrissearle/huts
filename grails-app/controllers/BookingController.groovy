class BookingController extends BaseController {
    def emailService
    def templateService

    def scaffold = true

    def delete = {
        def booking = Booking.get(params.id)

        if (booking) {
            def user = Person.getByUserId(session.userId)

            if (booking.contact == user) {
                def messageText = templateService.processTemplate("mailTemplates",
                        message(code: "booking.owner.cancellation.notification.file"),
                        ["booking": booking])

                emailService.sendMail(message(code: "booking.owner.cancellation.notification.subject", args: [booking.hut.name]),
                        messageText, [booking.hut.owner], [booking.contact])

            }

            def id = booking.hut.id

            booking.delete()

            flash.message = message(code: "booking.deleted")
            redirect(controller: 'hut', action: 'show', id: id)
        }
        else {
            flash.message = message(code: "booking.not.found")
            redirect(controller: 'hut', action: 'list')
        }
    }

    def book = {
        def booking = new Booking()

        booking.startDate = new Date() + 1
        booking.endDate = new Date() + 2

        booking.properties = params;

        if (request.method == "GET") {
            def hut = Hut.get(params.id)

            flash['funnel'] = '/funnel/booking/step1.html'

            return ['booking': booking, 'hut': hut]
        } else {
            Person user = Person.findByUserId(params['user.id'])
            booking.contact = user

            if (booking.save()) {
                def messageText = templateService.processTemplate("mailTemplates",
                        message(code: "booking.owner.notification.file"),
                        ["booking": booking])

                emailService.sendMail(message(code: "booking.owner.notification.subject", args: [booking.hut.name]),
                        messageText, [booking.hut.owner], [booking.contact])

                flash.message = message(code: "booking.booked.ok", args: [booking.hut])

                flash['funnel'] = '/funnel/booking/step2.html'
                redirect(controller: "hut", action: "show", id: booking.hut.id)
            } else {
                def hut = Hut.get(params['hut.id'])
                flash['funnel'] = '/funnel/booking/step1.html'
                return ['booking': booking, 'hut': hut]
            }
        }
    }

    def list = {
        if (!params.id) {
            redirect(controller: "hut", action: "list")
        }

        def hut = Hut.get(params.id)

        def criteria = Booking.createCriteria();

        def now = new Date() - 1

        def bookings = criteria.list {
            eq('hut', hut)
            ge('startDate', now)
            order('startDate', 'asc')
        }

        return ['bookingList': bookings, 'hut': hut]
    }

    def rental = {
        if (!params.id) {
            redirect(controller: "hut", action: "list")
        }

        def hut = Hut.get(params.id)

        return ['bookingList': hut.bookings, 'hut': hut]
    }

    def create = {
        if (!params.id) {
            redirect(controller: "hut", action: "list")
        }

        def booking = new Booking()

        booking.hut = Hut.get(params.id)

        booking.properties = params;

        return ['booking': booking]
    }

    def save = {
        def booking = new Booking(params)

        if (!booking.hasErrors() && booking.save()) {
            flash.message = message(code: "booking.created.ok", args: [booking.hut])

            redirect(controller: "booking", action: "list", id: booking.hut.id)
        } else {
            render(view: 'create', model: ['booking': booking])
        }
    }

    def my = {
        if (!params.id) {
            redirect(controller: "hut", action: "list")
        }

        def person = Person.get(params.id)

        def bookings = Booking.findByContact(person);

        return ['bookingList': bookings, 'person': person]
    }
}
