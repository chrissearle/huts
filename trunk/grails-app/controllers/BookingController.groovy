class BookingController extends BaseController {

    def beforeInterceptor = [action: this.&auth]

    def scaffold = true

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

                redirect(controller: "hut", action: "list")
            } else {
                def hut = Hut.get(params['hut.id'])
                return ['booking': booking, 'hut': hut]
            }
        }
    }

    def list = {
        def hut = Hut.get(params.id)

        return [bookingList: Booking.findByHut(hut), 'hut': hut]
    }
}
