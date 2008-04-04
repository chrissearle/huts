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
class BookingCommand implements JabberCommand {
    String list(Person person) {
        def msg = new StringBuffer();

        def bookings = Booking.list()

        if (!person.admin) {
            def crit = Booking.createCriteria()

            bookings = crit.listDistinct {
                or {
                    eq('contact', person)
                    hut {
                        eq('owner', person)
                    }
                }
            }
        }

        bookings.each {booking ->
            msg.append("${booking.id}: ${booking.hut.name}, ${booking.startDate}, ${booking.endDate}").append("\n")
        }

        return msg.toString()
    }

    String get(Person person, Long id) {
        Booking booking = Booking.get(id)

        def msg = ""

        if (!booking) {
            msg = "Not found"
        } else {
            if (person.admin || booking.hut.owner == person || booking.contact == person) {
                msg += """Hut: ${booking.hut.id}: ${booking.hut.name}
Contact: ${booking.contact.id}: ${booking.contact.name}
Start: ${booking.startDate}
End: ${booking.endDate}
People: ${booking.peopleCount}
"""
            }
        }

        return msg
    }
}
