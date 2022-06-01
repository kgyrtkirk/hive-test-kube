#FROM jenkins/jenkins:lts
FROM jenkins/jenkins:2.303.3-lts-jdk11
#latest-jdk8

#RUN /usr/local/bin/install-plugins.sh github-api:1.111
RUN /usr/local/bin/install-plugins.sh git
RUN /usr/local/bin/install-plugins.sh timestamper
RUN /usr/local/bin/install-plugins.sh ansicolor github build-name-setter timestamper kubernetes:1.30.1 kubernetes-client-api:5.4.1
# some issue with service; 
RUN /usr/local/bin/install-plugins.sh maven-plugin
RUN /usr/local/bin/install-plugins.sh parallel-test-executor
RUN /usr/local/bin/install-plugins.sh junit-realtime-test-reporter blueocean
RUN /usr/local/bin/install-plugins.sh build-timeout config-file-provider rebuild junit copyartifact 
RUN /usr/local/bin/install-plugins.sh metrics
RUN /usr/local/bin/install-plugins.sh antisamy-markup-formatter slack github-oauth throttle-concurrents lockable-resources pipeline-github envinject build-environment schedule-build matrix-auth
RUN /usr/local/bin/install-plugins.sh gerrit-trigger gerrit-code-review http_request
RUN /usr/local/bin/install-plugins.sh docker-workflow command-launcher google-oauth-plugin jira blueocean-jira jdk-tool ssh-credentials
RUN /usr/local/bin/install-plugins.sh job-dsl
RUN /usr/local/bin/install-plugins.sh sonar
RUN /usr/local/bin/install-plugins.sh cloudbees-folder
RUN /usr/local/bin/install-plugins.sh branch-api
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
