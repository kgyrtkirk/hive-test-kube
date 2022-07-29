[
  'cdw-master',
].each { branch ->
  pipelineJob("internal-hive-precommit-${branch}-healthcheck") {
    logRotator(33, -1, -1, -1)
    triggers {
      cron('@daily')
    }
    parameters {
      stringParam('SPLIT', '20', 'Number of buckets to split tests into.')
      stringParam('OPTS', '-q', 'additional maven opts')
      stringParam('VERSION', branch, 'the version to be used (e.g. by cdpd-patcher)')
      stringParam('GERRIT_CHANGE_SUBJECT', '')
      booleanParam('IS_HEALTHCHECK', true)
    }
    definition {
      cps {
        script(new File("${WORKSPACE}/hive-test-kube/job-dsl/hive.Jenkinsfile").text)
        sandbox()
      }
    }
  }
}
