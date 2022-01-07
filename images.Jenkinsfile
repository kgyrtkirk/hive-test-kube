
def executorNode(run) {
  hdbPodTemplate() {
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
      //      envVar(key: 'DOCKER_HOST', value: 'tcp://localhost:2375')
        ]
    ),
    containerTemplate(name: 'dind', image: 'docker:20.10-dind',
        privileged: true,
    ),
  ],
  volumes: [
    persistentVolumeClaim(claimName: 'images-job-cache', mountPath: '/var/lib/docker'),
    //emptyDirVolume(mountPath: '/var/lib/docker', memory: false),
    emptyDirVolume(mountPath: '/var/run', memory: false),
  ], yaml:'''
''') {
  closure();
  }
}

timestamps {
    executorNode() {
        container('hdb') {
          checkout scm
          sh('docker ps -a');
          sh('ls -la');
          sh('./buildAll');
        }
    }
}
