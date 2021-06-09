[   'ocp':'cdpd-master',
    'dev-ocp-7.1':'CDH-7.1-maint',
    'CDH-7.1-maint':'CDH-7.1-maint',
    'cdpd-master':'cdpd-master',
    'FENG':'cdpd-master',
    'r20':'cdpd-master',
    'dev-compaction-observability-metrics-2021':'cdpd-master',
    'dev-iceberg-ga2':'cdpd-master',
    'dev-iceberg-ga':'cdpd-master'].each { hiveBranch,baseBranch ->
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
      stringParam('SPLIT', '20', 'Number of buckets to split tests into.')
      stringParam('OPTS', '-q', 'additional maven opts')
      stringParam('VERSION', baseBranch, 'the version to be used; cdpd-master, CDH-7.1-maint, 7.1.1.2, 7.1.1.2-111, NO to skip, leave empty for autodetect')
    }
    definition {
      cpsScm {
        scm {
          git {
            remote {
              url('ssh://ptest@gerrit.sjc.cloudera.com:29418/cdh/hive.git')
              credentials('gerrit-ptest')
              refspec('+${GERRIT_REFSPEC}:refs/remotes/gerrit/patch +refs/heads/'+baseBranch+':refs/remotes/gerrit/target')
            }
            branch(hiveBranch)
          }
        }
        lightweight()
      }
    }
  }
}
