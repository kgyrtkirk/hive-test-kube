[
  'cdw-master',
].each { branch ->
  pipelineJob("internal-hive-precommit-${branch}-healthcheck") {
    logRotator(33, -1, -1, -1)
    properties {
        pipelineTriggers {
            triggers {
                cron {
                    spec('@daily')
                }
            }
        }
    }
    parameters {
      stringParam('SPLIT', '20', 'Number of buckets to split tests into.')
      stringParam('OPTS', '-q', 'Additional maven opts')
      stringParam('VERSION', branch, 'The version to be used (e.g. by cdpd-patcher)')
      stringParam('GERRIT_CHANGE_SUBJECT', '')
      booleanParam('IS_HEALTHCHECK', true)
      stringParam('SLACK_CHANNEL', '', 'Slack channel (without "#") to alert from healthcheck jobs, defaults to global settings')
    }
    definition {
      cps {
        script(new File("${WORKSPACE}/hive-test-kube/job-dsl/hive.Jenkinsfile").text)
        sandbox()
      }
    }
  }
}
