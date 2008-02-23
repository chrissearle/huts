class Booking {
    Hut hut
    Person contact
    Date startDate
    Date endDate

    static constraints = {
        hut(nullable: false)
        contact(nullable: false)
    }

    static belongsTo = Hut

    String toString() {"${this.hut}, ${this.contact}"}
}
