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
        Person.list()*.delete()
        Hut.list()*.delete()
        Booking.list()*.delete()

        def owner = new Person(name: "Hut owner", email: "test1@example.com", phone: "12345678", userId: "user1",
                password: "passw1", admin: false, approved: true, confirmed: true)
        assertNotNull "Owner null", owner

        if (!owner.save()) {
            fail owner.errors;
        }

        def renter1 = new Person(name: "Hut renter 1", email: "test2@example.com", phone: "11223344", userId: "user2",
                password: "passw2", admin: false, approved: true, confirmed: true).save()
        def renter2 = new Person(name: "Hut renter 2", email: "test3@example.com", phone: "87654321", userId: "user3",
                password: "passw3", admin: false, approved: true, confirmed: true).save()
        assertNotNull "Renter 1 null", renter1
        assertNotNull "Renter 2 null", renter2

        def hut = new Hut(name: "Test Hut", location: "Test Location", owner: owner, description: "Test description",
                beds: 5, latitude: "10", longitude: "10", openHut: true)
        hut.users = new PersonList()
        hut.users.hut = hut
        assertNotNull "Hut null", hut

        if (!hut.save()) {
            fail hut.errors;
        }

        assertEquals "Owner not owner", owner, hut.owner;

        new Booking(hut: hut, contact: renter1, startDate: new Date() - 2, endDate: new Date() + 2, peopleCount: 4, priceplan: null).save()
    }

    void tearDown() {
        Person.list()*.delete()
        Hut.list()*.delete()
        Booking.list()*.delete()
    }

    void testGetMessage() {
        def messageText = new OwnerWeeklyRentalReportJob().getMessage()

        assertToString(messageText, "Woot")
    }
}
