package folio.demo.module

class UrlMappings {

    static mappings = {
//        delete "/$controller/$id(.$format)?"(action:"delete")
//        get "/$controller(.$format)?"(action:"index")
//        get "/$controller/$id(.$format)?"(action:"show")
//        post "/$controller(.$format)?"(action:"save")
//        put "/$controller/$id(.$format)?"(action:"update")
//        patch "/$controller/$id(.$format)?"(action:"patch")

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
        "401"(view: '/authRequired')
        "403"(view: '/accessDenied')

        "/_/tenant"(controller: 'okapi', action:'tenant')

        '/locations'(resources: 'party')
        '/requests'(resources: 'request') {
          '/start' (controller: 'request', action: 'rotaStart')
        }

        "/admin/$action?/$id?"(controller: 'admin')
    }
}
