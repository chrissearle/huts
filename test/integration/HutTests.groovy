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
class HutTests extends GroovyTestCase {

    void setUp() {
        Person.list()*.delete()

        def owner = new Person(name: "Owner", email: "test1@example.com", phone: "12345678", userId: "user1",
                password: "passw1", admin: false, approved: true, confirmed: true)


        def hut = new Hut(name: "Test Hut",
                location: "Rhubarb Island",
                description: "blabla",
                beds: 20,
                latitude: "10",
                longitude: "10",
                users: []
        )

        assertNotNull "Owner null", owner
        assertNotNull "Hut null", hut

        owner.addToOwns(hut)

        if (!owner.save()) {
            fail owner.errors;
        }
    }


    void testHutCount() {
        def list = Hut.list()

        assertLength 1, list
    }

    void testRelationships() {
        Hut hut = Hut.findByName("Test Hut")

        assertNotNull "Hut owner null", hut.owner
        assertNotNull "Hut owner's huts null", hut.owner.owns
        assertTrue "Hut owner doesn't own hut", hut.owner.owns.contains(hut)
    }

    void testHutToString() {
        def list = Hut.list()

        assertEquals "toString incorrect", "Test Hut, Rhubarb Island", list[0].toString()
    }

    void tearDown() {
        Person.list()*.delete()
    }
}
