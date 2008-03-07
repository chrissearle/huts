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

        flash['message'] = message(code: "hut.picture.uploaded", args: [hut.name])

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

    def delete = {
        def hut = Hut.get(params.id)

        if (hut) {
            flash.message = message(code: "hut.deleted", args = [hut.name])

            hut.delete()

            redirect(controller: 'hut', action: 'list')
        }
        else {
            flash.message = message(code: "hut.not.found")
            redirect(controller: 'hut', action: 'list')
        }
    }
}
