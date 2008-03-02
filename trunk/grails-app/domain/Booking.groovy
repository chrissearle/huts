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
    }

    static belongsTo = Hut

    String toString() {"${this.hut}, ${this.contact}"}
}
