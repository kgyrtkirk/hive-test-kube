
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
  ], yaml: """
spec:
  nodeSelector:
    kubernetes.io/hostname: worker12.kc-04-ocp4.cloudera.com
""") {
  closure();
  }
}

timestamps {
  def branches = [:]
  for(int i=0;i<15;i++ ) {
    branches["worker${i}"]={
        executorNode("worker${i}.kc-04-ocp4.cloudera.com") {
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
