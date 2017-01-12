FROM jboss/wildfly
RUN /opt/jboss/wildfly/bin/add-user.sh admin pass --silent
ADD docker/scripts /tmp
ADD docker/jars/postgresql-9.4.1212.jar /tmp/postgresql-9.4.1212.jar
USER root
RUN chmod -R 777 /tmp
USER jboss
RUN /tmp/execute.sh
USER root
RUN rm -rf /opt/jboss/wildfly/standalone/configuration/standalone_xml_history
USER jboss
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]