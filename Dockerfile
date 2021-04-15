FROM confluentinc/cp-kafka-connect:3.2.0

WORKDIR /upwork-connect
COPY config config
COPY target target

VOLUME ./config
VOLUME ./offsets

CMD CLASSPATH="$(find target/ -type f -name '*.jar'| grep '\-package' | tr '\n' ':')" connect-standalone config/worker.properties config/MySourceConnector.properties
