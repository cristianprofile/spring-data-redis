package com.mylab.cromero.springdataredis.repository;

import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.ScriptExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collections;

@Repository
public class PersonScript {


    private ScriptExecutor<String> scriptExecutor;


    private DefaultRedisScript<Boolean> booleanDefaultRedisScript;


    public PersonScript(DefaultRedisScript booleanDefaultRedisScript,ScriptExecutor<String> scriptExecutor) {
        this.booleanDefaultRedisScript = booleanDefaultRedisScript;
        this.scriptExecutor=scriptExecutor;
    }

    public Boolean isMoreThanThree(String key)
    {

        Boolean result = scriptExecutor.execute(booleanDefaultRedisScript, Collections.singletonList(key));
        return result;
    }



}
