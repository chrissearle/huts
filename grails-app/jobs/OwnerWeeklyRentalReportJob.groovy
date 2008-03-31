class OwnerWeeklyRentalReportJob {
    def templateService
    def emailService

    def cronExpression = "0 20 3 ? * MON"

    def execute() {
        def people = Person.list()

        people.each {person ->
            if (person.owns) {
                def huts = person.owns

                def rentals = []

                huts.each {hut ->
                    def now = new Date()
                    def lastWeek = now - 7
                    def bookings = Booking.findAllByHutAndStartDateBetween(hut, lastWeek, now)

                    if (bookings) {
                        bookings.each {booking ->
                            def rental = [hut: hut,
                                    contact: booking.contact,
                                    start: booking.startDate,
                                    end: booking.endDate,
                                    people: booking.peopleCount
                            ];

                            if (booking.priceplan) {
                                def price = (booking.endDate - booking.startDate) * booking.priceplan.price

                                if (booking.priceplan.perHead) {
                                    price *= booking.peopleCount
                                }

                                rental.put(currency: booking.priceplan.currency,
                                        price: price)
                            }

                            rentals.add(rental)
                        }
                    }
                }

                if (rentals.count) {
                    def messageText = templateService.processTemplate("cronTemplates", "ownerWeeklyRentalReport.gtpl")

                    emailService.sendMail("Weekly Rental Report", messageText, [person], [])
                }
            }
        }
    }
}
