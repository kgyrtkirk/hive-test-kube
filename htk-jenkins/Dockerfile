FROM jenkins/jenkins:lts

RUN /usr/local/bin/install-plugins.sh git ansicolor github build-name-setter timestamper kubernetes
# some issue with service; 
RUN /usr/local/bin/install-plugins.sh maven-plugin:3.4
RUN /usr/local/bin/install-plugins.sh parallel-test-executor
RUN /usr/local/bin/install-plugins.sh junit-realtime-test-reporter blueocean
RUN /usr/local/bin/install-plugins.sh build-timeout config-file-provider rebuild junit copyartifact 

ADD jenkins_home /var/jenkins_home1

USER jenkins
