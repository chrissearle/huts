class Hut {
    String name
    String location
    Person owner

    String description
    Integer beds

    static belongsTo = Person

    static hasMany = [bookings: Booking]


    static constraints = {
        name(blank: false, maxSize: 50)
        location(blank: false, maxSize: 50)
        owner(nullable: false)
        description(blank: false, maxSize: 255)
        beds(blank: false, size: 1..50)
    }

    String toString() {"${this.name}, ${this.location}"}
}
