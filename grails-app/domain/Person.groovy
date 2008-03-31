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
class Person {
    String name
    String email
    String jabber
    String phone

    String userId
    String password
    String organization

    Boolean admin
    Boolean approved
    Boolean confirmed

    Boolean published = false

    String challenge

    static hasMany = [owns: Hut]

    static constraints = {
        name(blank: false, maxSize: 50)
        email(email: true)
        jabber(email: true, nullable: true)
        phone(blank: true, maxSize: 15)
        userId(blank: false, unique: true, size: 3..20)
        password(blank: false, size: 6..20)
        challenge(nullable: true)
        organization(nullable: true)
    }

    String toString() {"${this.name}"}
}
