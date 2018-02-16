pipeline {
  agent any
  stages {
    stage('Fetch OKAPI Ctrl Script') {
      steps {
        dir('okapi-ctrl') {
            git url: 'ssh://git@git.k-int.com:100/folio-ci/okapi-control.git', branch: 'master', credentialsId: 'kint-git-key'
        }
        
        // The script is written in groovy so let's ensure that that is on the path.
        withEnv(["PATH+GROOVY=${tool name: 'Groovy 2.4.x', type: 'hudson.plugins.groovy.GroovyInstallation}/bin"]) {
          // Use full groovy integration to ensure we can build.
         
          // Execute okapi commands.
          sh '''
            ./okapi-ctrl/okapi.groovy -n checkup
          '''
        }
      }
    }
  }
}