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
class PricePlanController {

    def scaffold = true

    def list = {
        if (!params.id) {
            redirect(controller: "hut", action: "list")
        }

        def hut = Hut.get(params.id)

        return ['pricePlanList': hut.pricePlans, 'hut': hut]
    }

    def create = {
        if (!params.id) {
            redirect(controller: "hut", action: "list")
        }

        def pricePlan = new PricePlan()

        pricePlan.hut = Hut.get(params.id)

        pricePlan.properties = params;

        return ['priceplan': pricePlan]
    }

    def save = {
        def pricePlan = new PricePlan(params)

        if (!pricePlan.hasErrors() && pricePlan.save()) {
            flash.message = message(code: "priceplan.created.ok", args: [pricePlan.hut])

            redirect(controller: "pricePlan", action: "list", id: pricePlan.hut.id)
        } else {
            render(view: 'create', model: ['priceplan': pricePlan])
        }
    }

    def edit = {
        def pricePlan = PricePlan.get(params.id)

        if (!pricePlan) {
            flash.message = message(code: "priceplan.shared.notfound")

            redirect(controller: "hut", action: "list")
        }
        else {
            return [priceplan: pricePlan]
        }
    }

    def update = {
        def pricePlan = PricePlan.get(params.id)
        if (pricePlan) {
            pricePlan.properties = params
            if (!pricePlan.hasErrors() && pricePlan.save()) {
                flash.message = message(code: "priceplan.updated.ok")
                redirect(action: list, id: pricePlan.hut.id)
            }
            else {
                render(view: 'edit', model: [priceplan: pricePlan])
            }
        }
        else {
            flash.message = message(code: "priceplan.shared.notfound")

            redirect(action: edit, id: params.id)
        }
    }

    def show = {
        def pricePlan = PricePlan.get(params.id)

        if (!pricePlan) {
            flash.message = message(code: "priceplan.shared.notfound")
            redirect(controller: "hut", action: "list")
        }
        else {return [priceplan: pricePlan]}
    }

    def delete = {
        def pricePlan = PricePlan.get(params.id)

        if (pricePlan) {
            def hut = pricePlan.hut
            pricePlan.delete()
            flash.message = message(code: "priceplan.deleted.ok")
            redirect(action: list, id: hut.id)
        }
        else {
            flash.message = message(code: "priceplan.shared.notfound")
            redirect(controller: "hut", action: "list")
        }
    }
}
