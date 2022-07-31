#FROM jenkins/jenkins:lts
FROM jenkins/jenkins:2.346.2-lts-jdk11
#latest-jdk8

#RUN jenkins-plugin-cli --plugins github-api:1.111
RUN jenkins-plugin-cli --plugins git
RUN jenkins-plugin-cli --plugins timestamper
RUN jenkins-plugin-cli --plugins ansicolor github build-name-setter timestamper kubernetes:1.30.1 kubernetes-client-api:5.4.1
# some issue with service; 
RUN jenkins-plugin-cli --plugins maven-plugin
RUN jenkins-plugin-cli --plugins parallel-test-executor
RUN jenkins-plugin-cli --plugins junit-realtime-test-reporter blueocean
RUN jenkins-plugin-cli --plugins build-timeout config-file-provider rebuild junit copyartifact
RUN jenkins-plugin-cli --plugins metrics
RUN jenkins-plugin-cli --plugins antisamy-markup-formatter slack github-oauth throttle-concurrents lockable-resources pipeline-github envinject build-environment schedule-build matrix-auth
RUN jenkins-plugin-cli --plugins gerrit-trigger gerrit-code-review http_request
RUN jenkins-plugin-cli --plugins docker-workflow command-launcher google-oauth-plugin jira blueocean-jira jdk-tool ssh-credentials
RUN jenkins-plugin-cli --plugins job-dsl
RUN jenkins-plugin-cli --plugins sonar
RUN jenkins-plugin-cli --plugins cloudbees-folder
RUN jenkins-plugin-cli --plugins branch-api
USER root
RUN apt-get update && apt-get install -y nano curl nmap make git less && apt-get clean
RUN apt-get update && apt-get install -y psmisc procps iproute2 && apt-get clean
#USER root
# FIXME: this should be jenkins!
ADD jenkins_home /var/jenkins_home_default
RUN chown -R jenkins /var/jenkins_home /var/jenkins_home_default
USER jenkins
#RUN ls -l /var/jenkins_home
ADD entrypoint /entrypoint
ENTRYPOINT ["/entrypoint"]
