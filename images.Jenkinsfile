
def executorNode(host,run) {
  hdbPodTemplate(host) {
    timeout(time: 60, unit: 'MINUTES') {
      node(POD_LABEL) {
        container('hdb') {
          run()
        }
      }
    }
  }
}

def hdbPodTemplate(closure) {
  podTemplate(
  slaveConnectTimeout: -100,
  containers: [
    containerTemplate(name: 'hdb', image: 'docker-sandbox.infra.cloudera.com/hive/hive-dev-box:executor', ttyEnabled: true, command: 'tini -- cat',
        envVars: [
            envVar(key: 'DOCKER_HOST', value: 'tcp://localhost:2375')
        ]
    ),
    containerTemplate(name: 'dind', image: 'docker:18.05-dind',
        privileged: true,
    ),
  ],
  volumes: [
    emptyDirVolume(mountPath: '/var/lib/docker', memory: false),
  ], yaml:'''
''') {
  closure();
  }
}

timestamps {
    executorNode() {
        container('hdb') {
            sh('echo ok');
            sh('docker ps -a');
            sh('ls -la');
        }
    }
}
