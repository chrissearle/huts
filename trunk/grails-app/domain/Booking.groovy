class Booking {
    Hut hut
    Person contact
    Date startDate
    Date endDate

    static constraints = {
        hut(nullable: false)
        contact(nullable: false)
        startDate(validator: {return (it > new Date())})
        endDate(validator: {return (it > new Date())})        
    }

    static belongsTo = Hut

    String toString() { "${this.hut}, ${this.person}" }
}
