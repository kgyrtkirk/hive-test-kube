FROM jenkins/jenkins:lts

#RUN jenkins-plugin-cli --plugins  github-api:1.111
RUN jenkins-plugin-cli --plugins git ansicolor github build-name-setter timestamper kubernetes
# some issue with service; 
RUN jenkins-plugin-cli --plugins maven-plugin
RUN jenkins-plugin-cli --plugins parallel-test-executor
RUN jenkins-plugin-cli --plugins junit-realtime-test-reporter blueocean
RUN jenkins-plugin-cli --plugins build-timeout config-file-provider rebuild junit copyartifact 
RUN jenkins-plugin-cli --plugins metrics
RUN jenkins-plugin-cli --plugins antisamy-markup-formatter slack github-oauth throttle-concurrents lockable-resources pipeline-github envinject build-environment schedule-build matrix-auth
USER root
RUN apt-get update && apt-get install -y nano curl nmap make git less && apt-get clean
#USER root
# FIXME: this should be jenkins!
USER jenkins
ADD jenkins_home /var/jenkins_home_default
#RUN chown -R jenkins /var/jenkins_home/
#RUN ls -l /var/jenkins_home
ADD entrypoint /entrypoint
ENTRYPOINT ["/entrypoint"]
