package cn.daxiaobiao.redis.impl;

import cn.daxiaobiao.redis.cache.JedisPoolCache;
import cn.daxiaobiao.redis.model.RedisNameSpace;
import cn.daxiaobiao.redis.exception.PoolCacheException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Redis implements JedisCommands {

    private static Logger logger = LoggerFactory.getLogger(Redis.class);
    
    private JedisPool pool;
    private RedisNameSpace nameSpace;
    private String pName;
    
    public Redis(RedisNameSpace nameSpace, String pName){
        this.nameSpace = nameSpace;
        this.pName = pName;
        pool = JedisPoolCache.getPool(nameSpace);
        logger.info("redis-tool pool is " + pool.toString());
        logger.info("redis-tool namespace is " + nameSpace);
    }

    public String set(String key, String value) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "set", key);
            String result = resource.set(key, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public String set(String key, String value, String nxxx, String expx, long time) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "set", key);
            String result = resource.set(key, value, nxxx, expx, time);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public String get(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "get", key);
            String result = resource.get(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Boolean exists(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "exists", key);
            Boolean result = resource.exists(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long persist(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "persist", key);
            Long result = resource.persist(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public String type(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "type", key);
            String result = resource.type(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Long expire(String key, int seconds) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "expire", key);
            Long result = resource.expire(key, seconds);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Long expireAt(String key, long unixTime) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "expireAt", key);
            Long result = resource.expireAt(key, unixTime);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long ttl(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "ttl", key);
            Long result = resource.ttl(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Boolean setbit(String key, long offset, boolean value) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "setbit", key);
            Boolean result = resource.setbit(key, offset, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Boolean setbit(String key, long offset, String value) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "setbit", key);
            Boolean result = resource.setbit(key, offset, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Long setrange(String key, long offset, String value) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "setrange", key);
            Long result = resource.setrange(key, offset, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public String getSet(String key, String value) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "getSet", key);
            String result = resource.getSet(key, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long setnx(String key, String value) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "setnx", key);
            Long result = resource.setnx(key, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public String setex(String key, int seconds, String value) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "setex", key);
            String result = resource.setex(key, seconds, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Long decrBy(String key, long integer) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "decrBy", key);
            Long result = resource.decrBy(key, integer);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Long decr(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "decr", key);
            Long result = resource.decr(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long incrBy(String key, long integer) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "incrBy", key);
            Long result = resource.incrBy(key, integer);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long incr(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "incr", key);
            Long result = resource.incr(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long append(String key, String value) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "append", key);
            Long result = resource.append(key, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public String substr(String key, int start, int end) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "substr", key);
            String result = resource.substr(key, start, end);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long hset(String key, String field, String value) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "hset", key);
            Long result = resource.hset(key, field, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public String hget(String key, String field) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "hget", key);
            String result = resource.hget(key, field);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long hsetnx(String key, String field, String value) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "hsetnx", key);
            Long result = resource.hsetnx(key, field, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public String hmset(String key, Map<String, String> hash) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "hmset", key);
            String result = resource.hmset(key, hash);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public List<String> hmget(String key, String... fields) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "hmget", key);
            List<String> result = resource.hmget(key, fields);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long hincrBy(String key, String field, long value) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "hincrBy", key);
            Long result = resource.hincrBy(key, field, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Boolean hexists(String key, String field) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "hexists", key);
            Boolean result = resource.hexists(key, field);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Long hdel(String key, String... field) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "hdel", key);
            Long result = resource.hdel(key, field);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Long hlen(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "hlen", key);
            Long result = resource.hlen(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Set<String> hkeys(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "hkeys", key);
            Set<String> result = resource.hkeys(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public List<String> hvals(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "hvals", key);
            List<String> result = resource.hvals(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Map<String, String> hgetAll(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "hgetAll", key);
            Map<String, String> result = resource.hgetAll(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Long rpush(String key, String... string) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "rpush", key);
            Long result = resource.rpush(key, string);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Long lpush(String key, String... string) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "lpush", key);
            Long result = resource.lpush(key, string);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Long llen(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "llen", key);
            Long result = resource.llen(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public List<String> lrange(String key, long start, long end) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "lrange", key);
            List<String> result = resource.lrange(key, start, end);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public String ltrim(String key, long start, long end) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "ltrim", key);
            String result = resource.ltrim(key, start, end);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public String lindex(String key, long index) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "lindex", key);
            String result = resource.lindex(key, index);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public String lset(String key, long index, String value) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "lset", key);
            String result = resource.lset(key, index, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Long lrem(String key, long count, String value) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "lrem", key);
            Long result = resource.lrem(key, count, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public String lpop(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "lpop", key);
            String result = resource.lpop(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public String rpop(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "rpop", key);
            String result = resource.rpop(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long sadd(String key, String... member) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "sadd", key);
            Long result = resource.sadd(key, member);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Set<String> smembers(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "smembers", key);
            Set<String> result = resource.smembers(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long srem(String key, String... member) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "srem", key);
            Long result = resource.srem(key, member);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public String spop(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "spop", key);
            String result = resource.spop(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Long scard(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "scard", key);
            Long result = resource.scard(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Boolean sismember(String key, String member) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "sismember", key);
            Boolean result = resource.sismember(key, member);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public String srandmember(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "srandmember", key);
            String result = resource.srandmember(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public List<String> srandmember(String key, int count) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "srandmember", key);
            List<String> result = resource.srandmember(key, count);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
  
    public Long strlen(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "strlen", key);
            Long result = resource.strlen(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Long zadd(String key, double score, String member) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "zadd", key);
            Long result = resource.zadd(key, score, member);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long zadd(String key, Map<String, Double> scoreMembers) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "zadd", key);
            Long result = resource.zadd(key, scoreMembers);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Set<String> zrange(String key, long start, long end) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "zrange", key);
            Set<String> result = resource.zrange(key, start, end);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long zrem(String key, String... member) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "zrem", key);
            Long result = resource.zrem(key, member);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Double zincrby(String key, double score, String member) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "zincrby", key);
            Double result = resource.zincrby(key, score, member);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long zrank(String key, String member) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "zrank", key);
            Long result = resource.zrank(key, member);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long zrevrank(String key, String member) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "zrevrank", key);
            Long result = resource.zrevrank(key, member);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long zcard(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "zcard", key);
            Long result = resource.zcard(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Double zscore(String key, String member) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "zscore", key);
            Double result = resource.zscore(key, member);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public List<String> sort(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "sort", key);
            List<String> result = resource.sort(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public List<String> sort(String key, SortingParams sortingParameters) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "sort", key);
            List<String> result = resource.sort(key, sortingParameters);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long zcount(String key, double min, double max) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "zcount", key);
            Long result = resource.zcount(key, min, max);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long zcount(String key, String min, String max) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "zcount", key);
            Long result = resource.zcount(key, min, max);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Set<String> zrangeByScore(String key, double min, double max) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "zrangeByScore", key);
            Set<String> result = resource.zrangeByScore(key, min, max);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "zrangeByScore", key);
            Set<String> result = resource.zrangeByScore(key, min, max, offset, count);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "zrangeByScore", key);
            Set<String> result = resource.zrangeByScore(key, min, max, offset, count);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "zrangeByScoreWithScores", key);
            Set<Tuple> result = resource.zrangeByScoreWithScores(key, min, max);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Set<String> zrangeByScore(String key, String min, String max) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "zrangeByScore", key);
            Set<String> result = resource.zrangeByScore(key, min, max);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
 
    public Long zremrangeByRank(String key, long start, long end) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "zremrangeByRank", key);
            Long result = resource.zremrangeByRank(key, start, end);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Long zremrangeByScore(String key, double start, double end) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "zremrangeByScore", key);
            Long result = resource.zremrangeByScore(key, start, end);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long zremrangeByScore(String key, String start, String end) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "zremrangeByScore", key);
            Long result = resource.zremrangeByScore(key, start, end);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "linsert", key);
            Long result = resource.linsert(key, where, pivot, value);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long lpushx(String key, String... string) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "lpushx", key);
            Long result = resource.lpushx(key, string);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long rpushx(String key, String... string) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "rpushx", key);
            Long result = resource.rpushx(key, string);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public List<String> blpop(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "blpop", key);
            List<String> result = resource.blpop(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public List<String> blpop(int seconds, String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "blpop", key);
            List<String> result = resource.blpop(seconds, key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    @Override
    public List<String> brpop(int seconds, String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "brpop", key);
            List<String> result = resource.brpop(seconds, key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public List<String> brpop(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "brpop", key);
            List<String> result = resource.brpop(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    
    public Long del(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "del", key);
            Long result = resource.del(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    public Long bitcount(String key) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "bitcount", key);
            Long result = resource.bitcount(key);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }

    public Long bitcount(String key, long start, long end) {
        check(key);
        Jedis resource = null;
        boolean success = false;
        try {
            resource = pool.getResource();
            debugLog(resource, "bitcount", key);
            Long result = resource.bitcount(key, start, end);
            success = true;
            return result;
        } finally {
            returnResource(resource, success);
        }
    }
    
    @Override
    public Long move(String key, int dbIndex) {
        throw new UnsupportedOperationException("Unsupported");
    }
    
    @Override
    public Long zlexcount(String s, String s1, String s2) {
        throw new UnsupportedOperationException("Unsupported");
    }
    
    @Override
    public Long zremrangeByLex(String s, String s1, String s2) {
        throw new UnsupportedOperationException("Unsupported");
    }
    
    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        throw new UnsupportedOperationException("Unsupported");
    }
    
    @Override
    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        throw new UnsupportedOperationException("Unsupported");
    }
    
    @Override
    public Set<String> zrevrange(String key, long start, long end) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
        throw new UnsupportedOperationException("Unsupported");
    }
    
    @Override
    public Set<String> zrangeByLex(String key, String min, String max) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
        throw new UnsupportedOperationException("Unsupported");
    }
    
    @Override
    public Boolean getbit(String key, long offset) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public String getrange(String key, long startOffset, long endOffset) {
        throw new UnsupportedOperationException("Unsupported");
    }
    
    @Override
    public String echo(String str) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public Long pfadd(String arg0, String... arg1) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public long pfcount(String arg0) {
        throw new UnsupportedOperationException("Unsupported");
    }
    
    @Override
    public ScanResult<Tuple> zscan(String arg0, int arg1) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public ScanResult<Tuple> zscan(String arg0, String arg1) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public ScanResult<Entry<String, String>> hscan(String arg0, int arg1) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public ScanResult<Entry<String, String>> hscan(String arg0, String arg1) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public ScanResult<String> sscan(String arg0, int arg1) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public ScanResult<String> sscan(String arg0, String arg1) {
        throw new UnsupportedOperationException("Unsupported");
    }
    
    private void check(String key){
        boolean valid = StringUtils.isBlank(pName) ? true : key.startsWith(pName);
        if(!valid){
            throw new IllegalArgumentException("The key " + key + " does not startWith " + pName);
        }

        if (pool == null) {
            String errmsg = "Not available JedisPool, nameSpace is " + nameSpace;
            logger.error(errmsg);
            throw new PoolCacheException(errmsg);
        }
    }
    
    private void returnResource(Jedis resource, boolean success) {
        if (success) {
            if (resource != null) {
                pool.returnResourceObject(resource);
            }
        } else {
            if (resource != null) {
                pool.returnBrokenResource(resource);
            }
        }
    }
    
    private void debugLog(Jedis resource, String command, String key){
        String host = resource.getClient().getHost();
        int port = resource.getClient().getPort();
        int number = pool.getNumActive();
//        RedisLog.log(host, port, nameSpace, command, key, number);
    }
}
