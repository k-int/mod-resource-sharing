pipeline {
  agent any
  stages {
    stage('Fetch OKAPI Ctrl Script') {
      steps {
        dir('okapi-ctrl') {
            url: 'ssh://git@git.k-int.com:100/folio-ci/okapi-control.git', branch: 'master', credentialsId: 'kint-git-key'
        }
      }
    }
  }
}