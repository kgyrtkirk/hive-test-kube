FROM jenkins/jenkins:lts

RUN /usr/local/bin/install-plugins.sh git ansicolor github build-name-setter timestamper kubernetes
# some issue with service; 
RUN /usr/local/bin/install-plugins.sh maven-plugin:3.4
RUN /usr/local/bin/install-plugins.sh parallel-test-executor
RUN /usr/local/bin/install-plugins.sh junit-realtime-test-reporter blueocean
RUN /usr/local/bin/install-plugins.sh build-timeout config-file-provider rebuild junit copyartifact 
RUN /usr/local/bin/install-plugins.sh metrics
RUN apt-get update && apt-get install -y nano curl nmap make git less && apt-get clean
#USER root
# FIXME: this should be jenkins!
USER jenkins
ADD jenkins_home /var/jenkins_home_default
#RUN chown -R jenkins /var/jenkins_home/
#RUN ls -l /var/jenkins_home
ADD entrypoint /entrypoint
ENTRYPOINT ["/entrypoint"]
