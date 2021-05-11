
['ocp','FENG','dev-compaction-observability-metrics-2021','dev-iceberg-ga'].each { hiveBranch ->
  pipelineJob("tx-internal-hive-precommit-${hiveBranch}") {
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
                  branch {
                    compareType('PLAIN')
                    pattern(hiveBranch)
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
      stringParam('SPLIT', '20', '')
      stringParam('OPTS', '-q', '')
    }
    definition {
      cpsScm {
        scm {
          git {
            remote {
              url('ssh://ptest@gerrit.sjc.cloudera.com:29418/cdh/hive.git')
              credentials('gerrit-ptest')
              refspec('+${GERRIT_REFSPEC}:refs/remotes/gerrit/patch +refs/heads/${GERRIT_BRANCH}:refs/remotes/gerrit/target')
            }
            branch(hiveBranch)
          }
        }
        lightweight()
      }
    }
  }
}
