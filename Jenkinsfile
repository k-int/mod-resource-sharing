pipeline {
  agent any
  stages {
    stage('Fetch OKAPI Ctrl') {
      steps {
        git(url: 'https://git.k-int.com/folio-ci/okapi-control.git', branch: 'master', poll: true)
      }
    }
  }
}