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
class BookingReminderJob {
    def templateService
    def emailService

    def cronExpression = "0 40 3 * * ?"

    def daysBefore = 2;

    def execute() {
        Booking.findAllStartDateBetween(new Date() + daysBefore, new Date() + daysBefore + 1).each {booking ->
            def messageText = templateService.processTemplate("cronTemplates", "bookingReminder.gtpl", [booking: booking])

            emailService.sendMail("Booking Reminder", messageText, [booking.contact], [booking.hut.owner])
        }
    }
}
