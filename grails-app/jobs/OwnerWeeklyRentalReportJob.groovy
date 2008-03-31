/*
   Copyright 2008 Chris Searle

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
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
