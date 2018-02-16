node {
  stage('Fetch OKAPI Ctrl Script') {
    steps {
      dir('okapi-ctrl') {
          git url: 'ssh://git@git.k-int.com:100/folio-ci/okapi-control.git', branch: 'master', credentialsId: 'kint-git-key'
      }
      
      // The script is written in groovy lets pull in the script here.
      def okapi = load("okapi-ctrl/okapi.groovy")
      
      okapi ( '-n checkup' )
    }
  }
}