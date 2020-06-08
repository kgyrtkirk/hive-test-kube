FROM debian:buster
RUN apt-get update && apt-get install -y rsync nocache
ADD rsyncd.conf /etc/rsyncd.conf
RUN mkdir /data  && chown nobody /data
VOLUME /data
CMD nocache rsync --daemon --no-detach --config=/etc/rsyncd.conf 
#--port 9873
