class Hut {
    String name
    String location
    Person owner

    String description
    Integer beds

    String latitude
    String longitude

    byte[] image

    static belongsTo = Person

    static hasMany = [bookings: Booking]

    static constraints = {
        name(blank: false, maxSize: 50)
        location(blank: false, maxSize: 50)
        latitude(blank: false)
        longitude(blank: false)
        owner(nullable: false)
        description(blank: false, maxSize: 255)
        beds(nullable: false, range: 1..50)
        image(nullable: true, maxSize: 250000)
    }

    String toString() {"${this.name}, ${this.location}"}
}
