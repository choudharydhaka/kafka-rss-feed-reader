Welcome to your new Kafka Connect connector!

# Running in development

```
mvn clean package
export CLASSPATH="$(find target/ -type f -name '*.jar'| grep '\-package' | tr '\n' ':')"
$CONFLUENT_HOME/bin/connect-standalone $CONFLUENT_HOME/etc/schema-registry/connect-avro-standalone.properties config/MySourceConnector.properties
```


select word java
```sql

select TITLE from t_feeds  where  array_contains( split(TITLE,' ') , 'Java') emit changes limit 100;
```