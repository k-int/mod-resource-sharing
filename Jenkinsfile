pipeline {
  agent any
  stages {
    stage('Fetch OKAPI Ctrl Script') {
      steps {
        dir('okapi-ctrl') {
            git url: 'ssh://git@git.k-int.com:100/folio-ci/okapi-control.git', branch: 'master', credentialsId: 'kint-git-key'
        }
        
        // Execute okapi commands.
        sh '''
          ./okapi-ctrl/okapi.groovy -n checkup
        '''
      }
    }
  }
}