
['ocp','FENG'].each { hiveBranch ->
  pipelineJob("tx-internal-hive-precommit-${hiveBranch}") {
    logRotator(33, -1, -1, -1)
    triggers {
      gerrit {
        events {
          patchsetCreated()
        }
        project('plain:cdh/hive', ["plain:${hiveBranch}"])
        buildNotBuilt(0,0);
      }
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
