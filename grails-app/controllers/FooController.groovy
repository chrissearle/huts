class FooController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ fooInstanceList: Foo.list( params ) ]
    }

    def show = {
        def fooInstance = Foo.get( params.id )

        if(!fooInstance) {
            flash.message = "Foo not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ fooInstance : fooInstance ] }
    }

    def delete = {
        def fooInstance = Foo.get( params.id )
        if(fooInstance) {
            fooInstance.delete()
            flash.message = "Foo ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Foo not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def fooInstance = Foo.get( params.id )

        if(!fooInstance) {
            flash.message = "Foo not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ fooInstance : fooInstance ]
        }
    }

    def update = {
        def fooInstance = Foo.get( params.id )
        if(fooInstance) {
            fooInstance.properties = params
            if(!fooInstance.hasErrors() && fooInstance.save()) {
                flash.message = "Foo ${params.id} updated"
                redirect(action:show,id:fooInstance.id)
            }
            else {
                render(view:'edit',model:[fooInstance:fooInstance])
            }
        }
        else {
            flash.message = "Foo not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def fooInstance = new Foo()
        fooInstance.properties = params
        return ['fooInstance':fooInstance]
    }

    def save = {
        def fooInstance = new Foo(params)
        if(!fooInstance.hasErrors() && fooInstance.save()) {
            flash.message = "Foo ${fooInstance.id} created"
            redirect(action:show,id:fooInstance.id)
        }
        else {
            render(view:'create',model:[fooInstance:fooInstance])
        }
    }
}
