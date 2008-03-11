class PersonList {
    Hut hut

    static hasMany = ['users': Person]

    static belongsTo = Hut
}
