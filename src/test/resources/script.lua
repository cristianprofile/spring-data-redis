if redis.call("EXISTS",KEYS[1]) == 1 then
     local ocurrences=redis.call("INCR",KEYS[1])
     if ocurrences>3 then
        return true
     else
        return false
     end

   else
     redis.call("SET",KEYS[1],1)
     return false
   end