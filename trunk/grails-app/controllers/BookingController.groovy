class BookingController extends BaseController {
    EmailService emailService

    def beforeInterceptor = [action: this.&auth]

    def scaffold = true

    def delete = {
        def booking = Booking.get(params.id)

        if (booking) {
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
                emailService.sendMail(message(code: "booking.owner.notification.file"), ["booking": booking], [booking.hut.owner.email],
                        [booking.contact.email], message(code: "booking.owner.notification.subject", args: [booking.hut.name]))

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
}
