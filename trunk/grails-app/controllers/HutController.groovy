import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile

class HutController extends BaseController {

    def beforeInterceptor = [action: this.&auth]

    def scaffold = true

    def picture = {
        if (!params.id) {
            redirect(controller: "hut", action: "list")
        }

        def hut = Hut.get(params.id)

        if (!hut) {
            redirect(controller: "hut", action: "list")
        }

        if (request.method == "GET") {
            return ['hut': hut]
        } else {
            if (!(request instanceof MultipartHttpServletRequest)) {
                println("no multipart")
            }

            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            CommonsMultipartFile file = (CommonsMultipartFile) multiRequest.getFile("file");

            hut.image = file.getBytes()
        }

        flash['message'] = "Image for ${hut.name} set"

        redirect(controller: 'hut', action: 'list')
    }

    def showpic = {
        if (!params.id) {
            redirect(controller: "hut", action: "list")
        }

        def hut = Hut.get(params.id)

        if (!hut) {
            redirect(controller: "hut", action: "list")
        }

        if (!hut.image) {
            redirect(controller: "hut", action: "list")
        }

        response.contentType = "image/jpeg"
        response.outputStream << hut.image
    }
}
