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
class HutFilters {
    def filters = {
        logSession(controller: '*', action: '*') {
            before = {
                log.debug("In log session")
                session.attributeNames.each {attr ->
                    log.info("${attr} ${session.getAttribute(attr)}")
                }
            }
        }
        // First we store off any sticky params
        hutgroup(controller: '*', action: '*') {
            before = {
                if (params.hutgroup) {
                    session.setAttribute('hutgroup', params.hutgroup)
                }
            }
        }
        zoom(controller: 'hut', action: 'list') {
            before = {
                if (params.zoom) {
                    session.setAttribute('zoom', params.zoom)
                }
            }
        }
        centerHut(controller: 'hut', action: 'list') {
            before = {
                if (params.centerHut) {
                    session.setAttribute('centerHut', params.centerHut)
                }
            }
        }
        centerPoint(controller: 'hut', action: 'list') {
            before = {
                if (params.latitude && params.longitude) {
                    session.setAttribute('latitude', params.latitude)
                    session.setAttribute('longitude', params.longitude)
                }
            }

        }
        clearStickyParams(controller: '*', action: '*') {
            before = {
                if (params.clearSticky) {
                    session.removeAttribute('hutgroup')
                    session.removeAttribute('zoom')
                    session.removeAttribute('centerHut')
                    session.removeAttribute('latitude')
                    session.removeAttribute('longitude')
                }
            }
        }
        // Then we check if login is required
        loginCheck(controller: '*', action: '*') {
            before = {
                log.info("In loginCheck")
                if (!session.userId) {
                    log.info("In loginCheck - not logged in")
                    if (!(actionName == "denied" ||
                            controllerName == "welcome" ||
                            (controllerName == "hut" &&
                                    (actionName == "list" ||
                                            actionName == "show" ||
                                            actionName == "showpic")) ||
                            (controllerName == "person" &&
                                    (actionName == "login" ||
                                            actionName == "logout" ||
                                            actionName == "register" ||
                                            actionName == "forgotten" ||
                                            actionName == "resend" ||
                                            actionName == "confirm")))) {

                        log.info("In loginCheck - login required " + controllerName + " " + actionName)

                        def originalRequestParams = [controller: controllerName, action: actionName]

                        originalRequestParams.putAll(params)

                        session.originalRequestParams = originalRequestParams

                        redirect(controller: 'person', action: 'login')

                        return false
                    }
                }
            }
        }
        accessCheckHut(controller: 'hut', action: '*') {
            before = {
                log.info("In accessCheckHut")
                if (!session.userId) {
                    if (actionName == "show" || actionName == "showpic") {
                        Hut hut = Hut.get(params.id)

                        if (!hut.openHut) {
                            redirect(controller: 'hut', action: 'denied')

                            return false
                        }
                    }
                } else {
                    switch (actionName) {
                        case "edit":
                        case "update":
                        case "delete":
                            Person p = Person.findByUserId(session.userId)

                            if (!p.admin) {
                                Hut hut = Hut.get(params.id)

                                if (p != hut.owner) {
                                    redirect(controller: 'hut', action: 'denied')

                                    return false
                                }
                            }
                            break;
                        case "show":
                        case "showpic":
                            Person p = Person.findByUserId(session.userId)

                            if (!p.admin) {
                                Hut hut = Hut.get(params.id)

                                if (!(hut.openHut || (p == hut.owner) || hut.users.users.contains(p))) {
                                    redirect(controller: 'hut', action: 'denied')

                                    return false
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        accessCheckBooking(controller: 'booking', action: '*') {
            before = {
                log.info("In accessCheckBooking")
                if (session.userId) {
                    Person p = Person.findByUserId(session.userId)

                    if (!p.admin) {
                        switch (actionName) {
                            case "show":
                            case "edit":
                            case "update":
                            case "save":
                                Booking b = Booking.get(params.id)

                                if (!((p == b.hut.owner) || (p == b.contact))) {
                                    redirect(controller: 'booking', action: 'denied')

                                    return false
                                }
                                break;

                            case "list":
                                Hut hut = Hut.get(params.id)

                                if (p != hut.owner) {
                                    redirect(controller: 'hut', action: 'denied')

                                    return false
                                }
                                break;

                            case "create":
                                Hut hut = Hut.get(params.id)

                                if (!((p == b.hut.owner) || (p == b.contact))) {
                                    redirect(controller: 'booking', action: 'denied')

                                    return false
                                }
                                break;

                            default:
                                break;
                        }
                    }
                }
            }
        }
        accessCheckPerson(controller: 'person', action: '*') {
            before = {
                log.info("In accessCheckPerson")
                if (session.userId) {
                    Person p = Person.findByUserId(session.userId)

                    switch (actionName) {
                        case 'contact':
                            break;
                        case 'show':
                            if (!p.admin && params.id) {
                                Person p2 = Person.get(params.id)

                                if (!p2.published && (p.id != p2.id)) {
                                    redirect(controller: 'person', action: 'denied')

                                    return false
                                }
                            }
                            break;

                        default:
                            if (!p.admin && params.id) {
                                Person p2 = Person.get(params.id)

                                if (p.id != p2.id) {
                                    redirect(controller: 'person', action: 'denied')

                                    return false
                                }
                            }
                            break;
                    }
                }
            }
        }
        accessCheckPricePlan(controller: 'pricePlan', action: '*') {
            before = {
                log.info("In accessCheckPricePlan")
                if (session.userId) {
                    Person p = Person.findByUserId(session.userId)

                    if (!p.admin) {
                        switch (actionName) {
                            case "show":
                            case "edit":
                            case "update":
                            case "save":
                                PricePlan pp = PricePlan.get(params.id)

                                if (p != pp.hut.owner) {
                                    redirect(controller: 'pricePlan', action: 'denied')

                                    return false
                                }
                                break;

                            case "list":
                            case "create":
                                Hut hut = Hut.get(params.id)

                                if (p != hut.owner) {
                                    redirect(controller: 'pricePlan', action: 'denied')

                                    return false
                                }
                                break;

                            default:
                                break;
                        }
                    }
                }
            }
        }
    }
}
