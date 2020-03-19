FROM jenkins/jenkins:lts

# Plugins for better UX (not mandatory)

RUN /usr/local/bin/install-plugins.sh git ansicolor github build-name-setter timestamper kubernetes
RUN /usr/local/bin/install-plugins.sh parallel-test-executor
RUN /usr/local/bin/install-plugins.sh junit-realtime-test-reporter blueocean
#RUN /usr/local/bin/install-plugins.sh greenballs

# Plugin for scaling Jenkins agents
#RUN /usr/local/bin/install-plugins.sh kubernetes

USER jenkins
