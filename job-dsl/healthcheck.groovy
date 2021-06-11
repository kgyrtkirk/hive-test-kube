pipelineJob("healthcheck") {
    logRotator(33, -1, -1, -1)
    properties {
        pipelineTriggers {
            triggers {
            }
        }
    }
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url("https://github.com/kgyrtkirk/hive-test-kube")
                    }
                    branch('ocp')
                }
            }
            lightweight()
            scriptPath("healthcheck.Jenkinsfile")
        }
    }
}
