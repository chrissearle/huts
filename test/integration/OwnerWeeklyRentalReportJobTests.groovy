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
class OwnerWeeklyRentalReportJobTests extends GroovyTestCase {

    void setUp() {
        Booking.list()*.delete()
        Hut.list()*.delete()
        Person.list()*.delete()

        def owner = new Person(name: "Hut owner", email: "test1@example.com", phone: "12345678", userId: "user1",
                password: "passw1", admin: false, approved: true, confirmed: true).save(flush: true)
        def renter1 = new Person(name: "Hut renter 1", email: "test2@example.com", phone: "11223344", userId: "user2",
                password: "passw2", admin: false, approved: true, confirmed: true).save(flush: true)
        def renter2 = new Person(name: "Hut renter 2", email: "test3@example.com", phone: "87654321", userId: "user3",
                password: "passw3", admin: false, approved: true, confirmed: true).save(flush: true)

        def hut = new Hut(name: "Test Hut", location: "Test Location", owner: owner, description: "Test description",
                beds: 5, latitude: "10", longitude: "10", openHut: true)
        hut.users = new PersonList()
        hut.users.hut = hut
        hut.save(flush: true)

        
        new Booking(hut: hut, contact: renter1, startDate: new Date() - 5, endDate: new Date() -3, peopleCount: 4, priceplan: null).save(flush: true)
        new Booking(hut: hut, contact: renter2, startDate: new Date() - 2, endDate: new Date() + 2, peopleCount: 4, priceplan: null).save(flush: true)
    }

    void testData() {
        assertEquals 3, Person.list().size()

        assertEquals 1, Hut.list().size()

        Hut.list().each { hut ->
            assertNotNull hut.owner
            assertNotNull hut.owner.owns
        }

        assertEquals 2, Booking.list().size()
    }

    void testGetMessage() {
        def messageText = new OwnerWeeklyRentalReportJob().getMessage()

        assertToString(messageText, "Woot")
    }

    void tearDown() {
        Booking.list()*.delete()
        Hut.list()*.delete()
        Person.list()*.delete()
    }
}
