class PricePlan {
    String name
    Double price = 0
    String currency
    Boolean perHead = true

    Hut hut

    static belongsTo = Hut

    static constraints = {
        name(blank: false)
        currency(blank: false, inList   :["NOK", "GBP", "EUR", "USD"])
        price(nullable: false)
        perHead()
        hut(nullable: false)
    }
}
