
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

def hdbPodTemplate(host,closure) {
  podTemplate(
  slaveConnectTimeout: -100,
  containers: [
    containerTemplate(name: 'hdb', image: 'docker-sandbox.infra.cloudera.com/hive/hive-dev-box:executor', ttyEnabled: true, command: 'tini -- cat'
    )
  ],
  //volumes: [ emptyDirVolume(mountPath: '/empty', memory: false),
   yaml: """
spec:
  nodeSelector:
    kubernetes.io/hostname: ${host}
""") {
  closure();
  }
}

timestamps {
  def branches = [:]
  for(int i=0;i<15;i++ ) {
    def name="worker${i}"
    branches[name]={
        executorNode("${name}.kc-04-ocp4.cloudera.com") {
        container('hdb') {
          sh('echo ok');
        }
      }
    }
  }

  stage('Testing') {
    parallel branches
  }
}
