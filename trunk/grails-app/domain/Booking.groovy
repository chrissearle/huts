class Booking {
    Hut hut
    Person contact
    Date startDate
    Date endDate
    Integer peopleCount
    PricePlan priceplan

    static constraints = {
        hut(nullable: false)
        contact(nullable: false)
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

            if (results && results.size() > 0) {
                return "overlap"
            }
        })
    }

    static belongsTo = Hut

    String toString() {"${this.hut}, ${this.contact}"}
}
