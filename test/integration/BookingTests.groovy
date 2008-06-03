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
class BookingTests extends GroovyTestCase {

    void setUp() {
        def person = new Person(name: "Test Person",
                email: "test@example.com",
                userId: "testuser",
                password: "testpass"
        )

        def hut = new Hut(name: "Test Hut",
                location: "Rhubarb Land",
                owner: person,
                description: "Blah blah blah",
                beds: 3,
                latitude: "10",
                longitude: "10"
        )

        new Booking(hut: hut,
                contact: person,
                startDate: "2007-01-01",
                endDate: "2007-01-10",
                peopleCount: 3
        )
    }

    void testSomething() {

    }
}
