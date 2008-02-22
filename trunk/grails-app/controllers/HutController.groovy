class HutController {

    def scaffold = true

    def show = {
        redirect(action: list)
    }
}
