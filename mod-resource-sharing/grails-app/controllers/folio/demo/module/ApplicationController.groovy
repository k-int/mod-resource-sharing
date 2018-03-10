package folio.demo.module

import grails.core.GrailsApplication
import grails.util.Environment
import grails.plugins.*

class ApplicationController implements PluginManagerAware {

    GrailsApplication grailsApplication
    GrailsPluginManager pluginManager

    def index() {
      log.debug("ApplicationController::index() ${params}");
        [grailsApplication: grailsApplication, pluginManager: pluginManager]
    }
}
