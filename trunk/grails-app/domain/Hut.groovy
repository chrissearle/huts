class Hut {
    String name
    String location
    Person owner

    static belongsTo = Person

    static hasMany = [bookings: Booking]


    static constraints = {
        name(blank: false, maxSize: 50)
        location(blank: false, maxSize: 50)
        owner(nullable: false)
    }

    String toString() { "${this.name}, ${this.location}" }
}
