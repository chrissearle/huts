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
class Booking {
    Person contact
    Date startDate
    Date endDate
    Integer peopleCount
    PricePlan priceplan

    static constraints = {
        priceplan(nullable: true)
        peopleCount(nullable: false,
                min: 1,
                validator: {val, obj ->
                    if (val > obj.hut.beds) {
                        return false;
                    }
                })
        startDate(validator: {val, obj ->
            if (val >= obj.endDate) {
                return "endBeforeStart"
            }

            def criteria = Booking.createCriteria()

            def results = criteria.list {
                and {
                    not {
                        eq("id", obj.id)
                    }
                    or {
                        // "startDate"<=val<"endDate"
                        and {
                            eq("hut", obj.hut)
                            le("startDate", val)
                            gt("endDate", val)
                        }
                        // "startDate"<=obj.endDate<"endDate"
                        and {
                            eq("hut", obj.hut)
                            le("startDate", obj.endDate)
                            ge("endDate", obj.endDate)
                        }
                        // val<"startDate" & obj.endDate>"endDate"
                        and {
                            eq("hut", obj.hut)
                            gt("startDate", val)
                            lt("endDate", obj.endDate)
                        }
                    }
                }
            }

            if (results && results.size() > 0) {
                return "overlap"
            }
        })
    }

    static belongsTo = [hut: Hut]

    String toString() {"${this.hut}, ${this.contact}"}
}
