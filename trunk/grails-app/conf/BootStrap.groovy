class BootStrap {

     def init = { servletContext ->
        if (!Person.findByUserId("chris")) {
            new Person(name: "Chris Searle", userId: "chris", password: "cdsamw",
                       phone: "932 55 054", email: "chris@chrissearle.org").save()
        }
     }
     def destroy = {
     }
} 