class BootStrap {

    def init = {servletContext ->
        Locale.setDefault(new Locale("nb"))

        if (!Person.get(1)) {
            new Person(name: "Default Admin - Change these details to your own!", userId: "admin", password: "admin",
                    phone: "xxxxxxxx", email: "root@localhost", admin: true, approved: true).save()
        }
    }
    def destroy = {
    }
} 