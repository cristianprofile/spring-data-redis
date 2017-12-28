# spring-data-redis
Spring Data Redis is a respository for Testing Repositories and Script purpose.


# Testing typical Spring data @Repository feature

Working with Redis Repositories allows to seamlessly convert and store domain objects in Redis Hashes, apply custom mapping strategies and make use of secondary indexes.

To access domain entities stored in a Redis you can leverage repository support that eases implementing those quite significantly.

See test folder with custom repository methods implementation with in memory redis.



# Testing Redis Script (to be able to test transactions without race condition problem delegate redis Scripts)

Redis Scripting
Redis versions 2.6 and higher provide support for execution of Lua scripts through the eval and evalsha commands. Spring Data Redis provides a high-level abstraction for script execution that handles serialization and automatically makes use of the Redis script cache.

See test folder with custom test of a simple lua file with a script returning true when is called more than 3 times with the same key

