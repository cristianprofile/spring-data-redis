package com.mylab.cromero.springdataredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.DefaultScriptExecutor;
import org.springframework.data.redis.core.script.ScriptExecutor;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
@EnableRedisRepositories

public class SpringDataRedisApplication {

	@Bean
	public DefaultRedisScript<Boolean> booleanDefaultRedisScript(ResourceLoader
			resourceLoader) {

		DefaultRedisScript<Boolean> script = new DefaultRedisScript<>();
		Resource resourceLua = resourceLoader.getResource("classpath:/script.lua");
		script.setLocation(resourceLua);
		script.setResultType(Boolean.class);
		return script;
	}

	@Bean
	public ScriptExecutor<String> scriptExecutor(RedisTemplate redisTemplate, ResourceLoader
			resourceLoader) throws URISyntaxException, IOException {
		ScriptExecutor<String> scriptExecutor = new DefaultScriptExecutor<String>(redisTemplate);
		return scriptExecutor;
	}



	public static void main(String[] args) {
		SpringApplication.run(SpringDataRedisApplication.class, args);
	}
}
