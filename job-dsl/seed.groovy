// If you want, you can define your seed job in the DSL and create it via the REST API.
// See https://github.com/sheehan/job-dsl-gradle-example#rest-api-runner

job('htk-seed') {
    scm {
        git {
            remote {
                name('kgyrtkirk')
                url("https://github.com/kgyrtkirk/hive-test-kube")
        }
        branch('ocp')
        extensions {
          cleanBeforeCheckout()
          relativeTargetDirectory('hive-test-kube')
        }
      }
    }
    triggers {
        scm '*/1 * * * *'
    }
    steps {
        dsl {
            external 'hive-test-kube/job-dsl/**/*.groovy'
            removeAction('DISABLE')
        }
    }
}
