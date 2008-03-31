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
class Hut {
    String name
    String location
    Person owner

    String description
    Integer beds

    String latitude
    String longitude

    Boolean openHut = true

    PersonList users

    byte[] image

    static belongsTo = Person

    static hasMany = [bookings: Booking, pricePlans: PricePlan]

    static constraints = {
        name(blank: false, maxSize: 50)
        location(blank: false, maxSize: 50)
        latitude(blank: false)
        longitude(blank: false)
        owner(nullable: false)
        description(blank: false, maxSize: 255)
        beds(nullable: false, range: 1..250)
        image(nullable: true, maxSize: 250000)
    }

    String toString() {"${this.name}, ${this.location}"}
}
