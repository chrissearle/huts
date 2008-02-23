class Hut {
    String name
    String location
    Person owner

    String description
    Integer beds

    byte[] image

    static belongsTo = Person

    static hasMany = [bookings: Booking]

    static constraints = {
        name(blank: false, maxSize: 50)
        location(blank: false, maxSize: 50)
        owner(nullable: false)
        description(blank: false, maxSize: 255)
        beds(blank: false, size: 1..50)
        image(nullable: true, maxSize: 250000)
    }

    String toString() {"${this.name}, ${this.location}"}
}
