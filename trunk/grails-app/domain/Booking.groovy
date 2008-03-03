class Booking {
    Hut hut
    Person contact
    Date startDate
    Date endDate
    Integer peopleCount

    static constraints = {
        hut(nullable: false)
        contact(nullable: false)
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
                    and {
                        eq("hut", obj.hut)
                        gt("endDate", val)
                        le("startDate", val)
                    }
                    and {
                        eq("hut", obj.hut)
                        ge("endDate", obj.endDate)
                        lt("startDate", obj.endDate)
                    }
                    // TODO around and within
                    and {
                        eq("hut", obj.hut)
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
