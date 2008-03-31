/*
   Copyright 2008 Chris Searle

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile

class HutController extends BaseController {
    def hutService

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
            flash.message = message(code: "hut.deleted", args: [hut.name])

            hut.delete()

            redirect(controller: 'hut', action: 'list')
        }
        else {
            flash.message = message(code: "hut.not.found")
            redirect(controller: 'hut', action: 'list')
        }
    }

    def list = {
        def hutgroup = session.hutgroup

        if (!session.userId) {
            def hutlist = hutService.visibleHuts(hutgroup)

            return ['hutList': hutlist]
        } else {
            Person p = Person.findByUserId(session.userId)

            def hutlist = hutService.visibleHuts(p, hutgroup)

            return ['hutList': hutlist]

        }
    }

    def userlist = {
        def hut = Hut.get(params.id)

        if (hut) {
            def criteria = Person.createCriteria();

            def people = criteria.list {
                eq('approved', true)
                eq('confirmed', true)
                ne('userId', hut.owner.userId)
            }

            return ['hut': hut, 'users': people]
        }
    }

    def adduser = {
        def hut = Hut.get(params.id)

        def person = Person.get(params.user)

        if (hut && person) {
            hut.users.addToUsers(person).save()
        }

        redirect(controller: 'hut', action: 'userlist', id: hut.id);
    }

    def save = {
        def hut = new Hut(params)

        hut.users = new PersonList()
        hut.users.hut = hut

        def p = Person.findByUserId(session.userId);

        if (!p.admin) {
            hut.owner = p
        }

        if (!hut.hasErrors() && hut.save()) {
            flash.message = message(code: 'hut.save.saved', args: [hut.name])
            redirect(action: show, id: hut.id)
        } else {
            render(view: 'create', model: [hut: hut])
        }
    }

    def update = {
        def hut = Hut.get(params.id)

        if (hut) {

            hut.properties = params

            def p = Person.findByUserId(session.userId);

            if (!p.admin) {
                hut.owner = p
            }

            if (!hut.users) {
                hut.users = new PersonList()
                hut.users.hut = hut
            }

            if (!hut.hasErrors() && hut.save()) {
                flash.message = message(code: 'hut.update.saved', args: [hut.name])
                redirect(action: show, id: hut.id)
            } else {
                render(view: 'edit', model: [hut: hut])
            }
        }
        else {
            flash.message = message(code: 'hut.update.not.found')
            redirect(action: edit, id: params.id)
        }
    }
}
