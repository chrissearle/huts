class BookingController extends BaseController {
    EmailerService emailerService

    def beforeInterceptor = [action: this.&auth]

    def scaffold = true

    def delete = {
        def booking = Booking.get(params.id)

        if (booking) {
            def id = booking.hut.id
            booking.delete()
            flash.message = "Booking deleted"
            redirect(controller: 'hut', action: 'show', id: id)
        }
        else {
            flash.message = "Booking not found"
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
            return ['booking': booking, 'hut': hut]
        } else {
            Person user = Person.findByUserId(params['user.id'])
            booking.contact = user

            if (booking.save()) {

                // Each "email" is a simple Map
                def email = [
                        to: [booking.hut.owner.email],
                        cc: [booking.contact.email],
                        subject: "Booking made for hut: ${booking.hut.name}",
                        text: """A booking has been made for:
Hut: ${booking.hut.name}
Start: ${booking.startDate}
End: ${booking.endDate}

by

Name:   ${booking.contact.name}
E-Mail: ${booking.contact.email}
Phone:  ${booking.contact.phone}"""
                ]

                // sendEmails expects a List
                emailerService.sendEmails([email])

                flash.message = "Successfully booked ${booking.hut}"

                redirect(controller: "hut", action: "show", id: booking.hut.id)
            } else {
                def hut = Hut.get(params['hut.id'])
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
