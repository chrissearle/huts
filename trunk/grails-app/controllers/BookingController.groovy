class BookingController extends BaseController {

    def beforeInterceptor = [action: this.&auth]

    def scaffold = true

    def delete = {
        log.warn("Deleting")
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

        return [bookingList: Booking.findByHut(hut), 'hut': hut]
    }
}
