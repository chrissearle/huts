class HutController extends BaseController {

    def beforeInterceptor = [action: this.&auth]

    def scaffold = true
}
