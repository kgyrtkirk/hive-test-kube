
def executorNode(run) {
  hdbPodTemplate {
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
        alwaysPullImage: true
    )
  ], yaml: '''
''') {
  closure();
}

executorNode {
  container('hdb') {
    sh('echo ok');
  }
}
