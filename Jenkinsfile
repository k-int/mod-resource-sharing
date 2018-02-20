pipeline {
 agent any
  stages {
    stage('Build this module') {
      steps {
        dir('mod-resource-sharing') {
          sh './gradlew -Dgrails.env=okapi clean package'
        }
      }
    }
  }
}