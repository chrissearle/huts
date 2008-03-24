class Person {
    String name
    String email
    String phone

    String userId
    String password
    String organization

    Boolean admin
    Boolean approved
    Boolean confirmed

    String challenge

    static hasMany = [owns: Hut]

    static constraints = {
        name(blank: false, maxSize: 50)
        email(email: true)
        phone(blank: true, maxSize: 15)
        userId(blank: false, unique: true, size: 3..20)
        password(blank: false, size: 6..20)
        challenge(nullable: true)
        organization(nullable: true)
    }

    String toString() {"${this.name}, ${this.phone}"}
}
