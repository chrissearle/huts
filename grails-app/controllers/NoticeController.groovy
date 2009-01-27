class NoticeController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ noticeInstanceList: Notice.list( params ) ]
    }

    def show = {
        def noticeInstance = Notice.get( params.id )

        if(!noticeInstance) {
            flash.message = "Notice not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ noticeInstance : noticeInstance ] }
    }

    def delete = {
        def noticeInstance = Notice.get( params.id )
        if(noticeInstance) {
            noticeInstance.delete()
            flash.message = "Notice ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Notice not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def noticeInstance = Notice.get( params.id )

        if(!noticeInstance) {
            flash.message = "Notice not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ noticeInstance : noticeInstance ]
        }
    }

    def update = {
        def noticeInstance = Notice.get( params.id )
        if(noticeInstance) {
            noticeInstance.properties = params
            if(!noticeInstance.hasErrors() && noticeInstance.save()) {
                flash.message = "Notice ${params.id} updated"
                redirect(action:show,id:noticeInstance.id)
            }
            else {
                render(view:'edit',model:[noticeInstance:noticeInstance])
            }
        }
        else {
            flash.message = "Notice not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def noticeInstance = new Notice()
        noticeInstance.properties = params
        return ['noticeInstance':noticeInstance]
    }

    def save = {
        def noticeInstance = new Notice(params)
        if(!noticeInstance.hasErrors() && noticeInstance.save()) {
            flash.message = "Notice ${noticeInstance.id} created"
            redirect(action:show,id:noticeInstance.id)
        }
        else {
            render(view:'create',model:[noticeInstance:noticeInstance])
        }
    }
}
