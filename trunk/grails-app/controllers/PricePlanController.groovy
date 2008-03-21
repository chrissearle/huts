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
