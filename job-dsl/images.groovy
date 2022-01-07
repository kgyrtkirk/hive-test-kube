pipelineJob("images") {
    logRotator(33, -1, -1, -1)
    properties {
        pipelineTriggers {
            triggers {
                //pollSCM('H */4 * * *')
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
            scriptPath("images.Jenkinsfile")
        }
    }
}
