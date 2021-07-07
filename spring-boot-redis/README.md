# SpringBoot集成Redis

## 1 部署Redis服务端
### 1.1 windows下部署Redis
#### 1.1.1 下载redis
网址：https://github.com/MicrosoftArchive/redis/releases
#### 1.1.2  配置redis为服务
打开dos窗口，进入redis目录，输入如下命令：
```shell
redis-server --service-install redis.windows.conf
```
#### 1.1.3 启动服务
```shell
redis-server --service-start
```
### 1.2 linux下部署Redis
#### 1.2.1 下载redis
```shell
$ wget http://download.redis.io/releases/redis-2.8.17.tar.gz
```
#### 1.2.2 编译redis
```shell
$ tar xzf redis-2.8.17.tar.gz
$ cd redis-2.8.17
$ make
```
#### 1.2.3 启动redis
```shell
$ ./redis-server # 使用默认配置启动
$ ./redis-server redis.conf # 使用指定配置启动
```
## 2 新建项目访问Redis服务端
### 2.1 配置关键依赖
```
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-redis'
    implementation 'com.fasterxml.jackson.core:jackson-databind'
}
```
### 2.2 定义Redis配置类
```
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 配置连接工厂
        redisTemplate.setConnectionFactory(factory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 使用StringRedisSerializer来序列化和反序列redis的key
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 使用StringRedisSerializer来序列化和反序列redis的hash key
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        // 使用Jackson2JsonRedisSerializer来序列化和反序列redis的value
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // 使用Jackson2JsonRedisSerializer来序列化和反序列redis的hash value
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
```
### 2.3 定义Redis服务类
```
@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void set(final String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(final String key, Object value, Long expiredTime) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, expiredTime, TimeUnit.SECONDS);
    }

    public boolean hasKey(final String key) {
        return redisTemplate.persist(key);
    }

    public Long getExpiredTime(final String key) {
        return redisTemplate.getExpire(key);
    }

    public Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Set<String> listKeys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    public Set<String> listAllKey() {
        return listKeys("*");
    }

    public void del(final String key) {
        redisTemplate.delete(key);
    }

    public void delBatch(Collection<String> keys) {
        redisTemplate.delete(keys);
    }
}
```