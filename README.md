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
-- java and budget
 select TITLE  from t_feeds where REGEXP_EXTRACT( '(java){1}', lcase(TITLE)  ) != 'null' and REGEXP_EXTRACT(lcase(description), 'budget') != 'null'   emit changes limit 100;
-- title with budget and courty
select title,  REGEXP_EXTRACT('(Budget</b>:\\s+\\$\\d+)', DESCRIPTION),  REGEXP_EXTRACT('(Country</b>:\\s+)\\w+', DESCRIPTION)  from t_feeds emit changes limit 10;

-- title with budget not null and country
select title  from t_feeds   where REGEXP_EXTRACT('(Budget</b>:\\s+\\$\\d+)', DESCRIPTION) !='null' and 'null' !=  REGEXP_EXTRACT('(Country</b>:\\s+)\\w+', DESCRIPTION) emit  changes limit 10;


-- title with budget and courty both not null
select title, split(REGEXP_EXTRACT('(Budget</b>:\\s+\\$\\d+)', DESCRIPTION),' ')[2] as budget   from t_feeds   where REGEXP_EXTRACT('(Budget</b>:\\s+\\$\\d+)', DESCRIPTION) !='null' and 'null' !=  REGEXP_EXTRACT('(Country</b>:\\s+)\\w+', DESCRIPTION)  emit  changes limit 10;


-- title with budget and courty both not null

select title, split(REGEXP_EXTRACT('(Budget</b>:\\s+\\$\\d+)', DESCRIPTION),' ')[2] as budget, split(REGEXP_EXTRACT('(Country</b>:\\s+)\\w+', DESCRIPTION),' ')[2] as Country  from t_feeds   where REGEXP_EXTRACT('(Budget</b>:\\s+\\$\\d+)', DESCRIPTION) !='null' and 'null' !=  REGEXP_EXTRACT('(Country</b>:\\s+)\\w+', DESCRIPTION)  emit  changes limit 10;

-- title, budget, country from, link
select title, split(REGEXP_EXTRACT('(Budget</b>:\\s+\\$\\d+)', DESCRIPTION),' ')[2] as budget, split(REGEXP_EXTRACT('(Country</b>:\\s+)\\w+', DESCRIPTION),' ')[2] as Country, link  from t_feeds   where REGEXP_EXTRACT('(Budget</b>:\\s+\\$\\d+)', DESCRIPTION) !='null' and 'null' !=  REGEXP_EXTRACT('(Country</b>:\\s+)\\w+', DESCRIPTION)  emit  changes limit 10;       

-- create table t_feed_filtered  from 
-- create table t_feed_filtered  (title VARCHAR, budget VARCHAR, country varchar, link VARCHAR primary key, pubDate varchar) ; as   \
-- select title, split(REGEXP_EXTRACT('(Budget</b>:\\s+\\$\\d+)', DESCRIPTION),' ')[2] as budget, split(REGEXP_EXTRACT('(Country</b>:\\s+)\\w+', DESCRIPTION),' ')[2] as Country, link, pubDate  from t_feeds   where REGEXP_EXTRACT('(Budget</b>:\\s+\\$\\d+)', DESCRIPTION) !='null' and 'null' !=  REGEXP_EXTRACT('(Country</b>:\\s+)\\w+', DESCRIPTION)  emit  changes limit 10;      

```