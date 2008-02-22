class Person {
    String name
    String email
    String phone

    String userId
    String password

    Boolean admin

    static hasMany = [owns: Hut]

    static constraints = {
        name(blank: false, maxSize: 50)
        email(email: true)
        phone(maxSize: 15)
        userId(blank: false, unique: true, size: 3..20)
        password(blank: false, size: 6..20)
    }

    String toString() {"${this.name}, ${this.phone}"}
}
