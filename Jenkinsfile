pipeline {
  agent any
  stages {
    stage('Fetch OKAPI Ctrl Script') {
      steps {
        dir('okapi-ctrl') {
            git url: 'ssh://git@git.k-int.com:100/folio-ci/okapi-control.git', branch: 'master', credentialsId: 'kint-git-key'
        }
        
        // The script is written in groovy so let's ensure that that is on the path.
        tool name: 'Groovy 2.4.x', type: 'hudson.plugins.groovy.GroovyInstallation' 
        
        // Execute okapi commands.
        sh '''
          ./okapi-ctrl/okapi.groovy -n checkup
        '''
      }
    }
  }
}