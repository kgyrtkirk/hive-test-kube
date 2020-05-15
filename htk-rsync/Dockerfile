FROM instrumentisto/rsync-ssh:latest
ADD rsyncd.conf /etc/rsyncd.conf
RUN mkdir /data  && chown nobody /data
VOLUME /data
CMD rsync --daemon --no-detach --config=/etc/rsyncd.conf 
#--port 9873
