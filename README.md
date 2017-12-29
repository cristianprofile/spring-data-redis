# spring-data-redis
Spring Data Redis is a respository for Testing Repositories and Script purpose.


# Testing typical Spring data @Repository feature

Working with Redis Repositories allows to seamlessly convert and store domain objects in Redis Hashes, apply custom mapping strategies and make use of secondary indexes.

To access domain entities stored in a Redis you can leverage repository support that eases implementing those quite significantly.

See test folder with custom repository methods implementation with in memory redis.


Redis Repository support allows to convert, store, retrieve and index entities within Redis native data structures. To do, besides the `HASH` containing the actual properties several [Secondary Index](http://redis.io/topics/indexes) structures are set up and maintained.

```java
@RedisHash("persons")
public class Person {

    @Id
    private String id;
    @Indexed
    private String firstname;
    @Indexed
    private String lastname;

    @Reference
    List<Address> adress;

    @TimeToLive
    private Long expiration;
```


```java
@RedisHash("addresses")
public class Address {


    @Id
    private String id;

    private  String city;

    @Indexed
    private String country;

```

The above entity would for example then be stored in a Redis [HASH](http://redis.io/topics/data-types#hashes) with key `persons:9b0ed8ee-14be-46ec-b5fa-79570aadb91d`.

```properties
_class=com.mylab.cromero.springdataredis.domain.Person                <1>
id=9b0ed8ee-14be-46ec-b5fa-79570aadb91d
firstname=cristian                                             <2>
lastname=romero                                                <2>
phone.number=343433443                                      <3>
phone.description=cell phone                                <3>
address.[0]=addresses:41436096-aabe-42fa-bd5a-9a517fbf0260    <4>
address.[1]=addresses:1973d8e7-fbd4-4f93-abab-a2e3a00b3f53    <4>
```


```properties
_class=com.mylab.cromero.springdataredis.domain.Address                <1>
id=41436096-aabe-42fa-bd5a-9a517fbf0260
city=Mostoles                                                           <2>
country=Madrid                                                          <2>
```
```properties
_class=com.mylab.cromero.springdataredis.domain.Address                <1>
id=1973d8e7-fbd4-4f93-abab-a2e3a00b3f53
city=Leganes                                                            <2>
country=Madrid                                                          <2>
```

```
<1> The _class attribute is used to store the actual type and is required for object/hash conversion.
<2> Values are also included in Secondary Index when annotated with @Indexed.
<3> Complex types are flattened out of the box and embedded into the HASH as long as there is no explicit Converter registered or a @Reference annotation present.
<4> Using @Reference stores only the key of a referenced object without embedding values like in <3>.
```


# Testing Redis Script (to be able to test transactions without race condition problem delegate redis Scripts)

Redis Scripting
Redis versions 2.6 and higher provide support for execution of Lua scripts through the eval and evalsha commands. Spring Data Redis provides a high-level abstraction for script execution that handles serialization and automatically makes use of the Redis script cache.

See test folder with custom test of a simple lua file with a script returning true when is called more than 3 times with the same key

