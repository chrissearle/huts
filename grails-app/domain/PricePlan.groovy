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
class PricePlan {
    String name
    Double price = 0
    String currency
    Boolean perHead = true

    Hut hut

    static belongsTo = Hut

    static constraints = {
        name(blank: false)
        currency(blank: false, inList   :["NOK", "GBP", "EUR", "USD"])
        price(nullable: false)
        perHead()
        hut(nullable: false)
    }

    String toString() { "${name} (${currency} ${price})" }
}
