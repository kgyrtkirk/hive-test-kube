[
  'CDH-7.2.11.1':['CDH-7.2.11.1'],
  'CDH-7.2.12.0':['CDH-7.2.12.0'],
  'CDH-7.1-maint':['CDH-7.1-maint'],
  'cdpd-master': ['cdpd-master'],
  'cdw-master': [ 'ocp',
                  'cdw-master',
                  'R24','R25', 'R26',
                  'dev-direct-delete-and-update',
                  'dev-compaction-observability-metrics-2021',
                  'dev-compaction-observability-metrics-2021-v2',
                  'dev-iceberg-ga',
                  'dev-comp-R25',
                  ],
  'CDWH-2021.0.5' : ['CDWH-2021.0.5'],
].each { baseBranch, inputBranches ->
  pipelineJob("internal-hive-precommit-${baseBranch}") {
    logRotator(33, -1, -1, -1)
    properties {
      pipelineTriggers {
        triggers {
          gerrit {
            gerritProjects {
              gerritProject {
                disableStrictForbiddenFileVerification(false)
                compareType('PLAIN')
                pattern('cdh/hive')
                branches {
                  inputBranches.each { b -> 
                    branch {
                      compareType('PLAIN')
                      pattern(b)
                    }
                  }
                }
              }
            }
            skipVote {
              // the person who built this dsl construct for gerrit events must have been braindead
              onNotBuilt(true); onSuccessful(false); onFailed(false); onUnstable(false); onAborted(false);
            }
          }
        }
      }
    }
    parameters {
      stringParam('SPLIT', '20', 'Number of buckets to split tests into.')
      stringParam('OPTS', '-q', 'additional maven opts')
      stringParam('VERSION', baseBranch, 'the version to be used; cdpd-master, CDH-7.1-maint, 7.1.1.2, 7.1.1.2-111, NO to skip, leave empty for autodetect')
    }
    definition {
      cps {
        script(new File("${WORKSPACE}/hive-test-kube/job-dsl/hive.Jenkinsfile").text)
        sandbox()
      }
    }
  }
}
