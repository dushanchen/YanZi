package com.yanzi.common.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import com.yanzi.common.trace.MLogger;

/**
 * ParentDao 操作字符串redis缓存方法
 */
public class RedisBaseDao {

    private static final Logger logger = new MLogger(RedisBaseDao.class);
    @Autowired
    protected RedisTemplate<String, String> redisTemplate;

    protected boolean containsKey(String k) {
        try {
            return redisTemplate.hasKey(k);
        } catch (Throwable t) {
            logger.error("判断缓存存在失败key[" + k + ", error[" + t + "]");
        }
        return false;
    }
    
    protected boolean removeKey(String k) {
        try {
            redisTemplate.delete(k);
            return true;
        } catch (Throwable t) {
            logger.error("获取缓存失败key[" + k + ", error[" + t + "]");
        }
        return false;
    }

    protected boolean cacheValue(String k, String v, long time) {
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            valueOps.set(k, v);
            if (time > 0) {
                redisTemplate.expire(k, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + k + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    protected boolean cacheValue(String k, String v) {
        return cacheValue(k, v, -1);
    }

    protected String getValue(String k) {
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            return valueOps.get(k);
        } catch (Throwable t) {
            logger.error("获取缓存失败key[" + k + ", error[" + t + "]");
        }
        return null;
    }

    
    
    
    

    protected boolean cacheSet(String k, String v, long time) {
        try {
            SetOperations<String, String> valueOps = redisTemplate.opsForSet();
            valueOps.add(k, v);
            if (time > 0) {
                redisTemplate.expire(k, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + k + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    protected boolean cacheSet(String k, String v) {
        return cacheSet(k, v, -1);
    }

    protected boolean cacheSet(String k, Set<String> v, long time) {
        try {
            SetOperations<String, String> setOps = redisTemplate.opsForSet();
            setOps.add(k, v.toArray(new String[v.size()]));
            if (time > 0) {
                redisTemplate.expire(k, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + k + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    protected boolean cacheSet(String k, Set<String> v) {
        return cacheSet(k, v, -1);
    }

    protected boolean containsSetValue(String k, String v) {
        try {
            SetOperations<String, String> setOps = redisTemplate.opsForSet();
            return setOps.isMember(k, v);
        } catch (Throwable t) {
            logger.error("判断缓存存在失败[" + k + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    protected Set<String> getSet(String k) {
        try {
            SetOperations<String, String> setOps = redisTemplate.opsForSet();
            return setOps.members(k);
        } catch (Throwable t) {
            logger.error("获取set缓存失败key[" + k + ", error[" + t + "]");
        }
        return null;
    }

    protected long getSetSize(String k) {
        try {
            SetOperations<String, String> setOps = redisTemplate.opsForSet();
            return setOps.size(k);
        } catch (Throwable t) {
            logger.error("获取set长度失败key[" + k + "], error[" + t + "]");
        }
        return 0;
    }
    
    protected boolean removeElemOfSet(String k, String v) {
        try {
            SetOperations<String, String> setOps = redisTemplate.opsForSet();
            setOps.remove(k, v);
            return true;
        } catch (Throwable t) {
            logger.error("获取set长度失败key[" + k + "], value[" + v + "], error[" + t + "]");
        }
        return false;
    }

    protected boolean removeElemOfSet(String k, Set<String> v) {
        try {
            SetOperations<String, String> setOps = redisTemplate.opsForSet();
            setOps.remove(k, v.toArray(new Object[v.size()]));
            return true;
        } catch (Throwable t) {
            logger.error("获取set长度失败key[" + k + "], value[" + v + "], error[" + t + "]");
        }
        return false;
    }

    protected boolean cacheRightList(String k, List<String> v, long time) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.rightPushAll(k, v);
            if (time > 0) {
                redisTemplate.expire(k, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + k + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    protected boolean cacheRightList(String k, List<String> v) {
        return cacheRightList(k, v, -1);
    }

    protected boolean cacheRightList(String k, String v, long time) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.rightPush(k, v);
            if (time > 0) {
                redisTemplate.expire(k, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + k + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    protected boolean cacheRightList(String k, String v) {
        return cacheRightList(k, v, -1);
    }

    protected boolean cacheLeftList(String k, String v, long time) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.leftPush(k, v);
            if (time > 0) {
                redisTemplate.expire(k, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + k + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    protected boolean cacheLeftList(String k, String v) {
        return cacheLeftList(k, v, -1);
    }

    protected boolean cacheLeftList(String k, List<String> v, long time) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.leftPushAll(k, v);
            if (time > 0) {
                redisTemplate.expire(k, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + k + "]失败, value[" + v + "]", t);
        }
        return false;
    }
    
    protected boolean cacheLeftList(String k, List<String> v) {
        return cacheLeftList(k, v, -1);
    }

    protected List<String> getList(String k, long start, long end) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            return listOps.range(k, start, end);
        } catch (Throwable t) {
            logger.error("获取list缓存失败key[" + k + ", error[" + t + "]");
        }
        return null;
    }

    protected long getListSize(String k) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            return listOps.size(k);
        } catch (Throwable t) {
            logger.error("获取list长度失败key[" + k + "], error[" + t + "]");
        }
        return 0;
    }

    protected boolean removeOneOfListRight(String k) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.rightPop(k);
            return true;
        } catch (Throwable t) {
            logger.error("移除list缓存失败key[" + k + ", error[" + t + "]");
        }
        return false;
    }

    protected boolean removeOneOfListLeft(String k) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.leftPop(k);
            return true;
        } catch (Throwable t) {
            logger.error("移除list缓存失败key[" + k + ", error[" + t + "]");
        }
        return false;
    }

    protected boolean removeElemOfList(String k, String v) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.remove(k, 1, v);
            return true;
        } catch (Throwable t) {
            logger.error("移除list缓存失败key[" + k + ",value[" + v + "] error[" + t + "]");
        }
        return false;
    }

    protected boolean cacheHash(String k, Map<String, String> hkv, long time) {
        try {
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            hashOps.putAll(k, hkv);
            if (time > 0) {
                redisTemplate.expire(k, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + k + "]失败, value[" + hkv + "]", t);
        }
        return false;
    }

    protected boolean cacheHash(String k, Map<String, String> hkv) {
        return cacheHash(k, hkv, -1);
    }

    protected boolean cacheHash(String k, String hk, String hv, long time) {
        try {
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            hashOps.put(k, hk, hv);
            if (time > 0) {
                redisTemplate.expire(k, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + k + "]失败, hkey[" + hk + "], hvalue[" + hv + "]", t);
        }
        return false;
    }

    protected boolean cacheHash(String k, String hk, String hv) {
        return cacheHash(k, hk, hv, -1);
    }

    protected boolean containsHashKey(String k, String hk) {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        try {
            return hashOps.hasKey(k, hk);
        } catch (Throwable t) {
            logger.error("判断缓存存在失败key[" + k + ", hkey[" + hk + "], error[" + t + "]");
        }
        return false;
    }
    
    protected Map<String, String> getHash(String k) {
        try {
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            return hashOps.entries(k);
        } catch (Throwable t) {
            logger.error("获取 hash缓存失败key[" + k + ", error[" + t + "]");
        }
        return null;
    }

    protected List<String> getHash(String k, List<String> hk) {
        try {
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            return hashOps.multiGet(k, hk);
        } catch (Throwable t) {
            logger.error("获取 hash缓存失败key[" + k + ", error[" + t + "]");
        }
        return null;
    }
    
    protected String getHash(String k, String hk) {
        try {
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            return hashOps.get(k, hk);
        } catch (Throwable t) {
            logger.error("获取 hash缓存失败key[" + k + ", error[" + t + "]");
        }
        return null;
    }

    protected boolean removeElemOfHash(String k, String hk) {
        try {
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            hashOps.delete(k, hk);
            return true;
        } catch (Throwable t) {
            logger.error("删除 hash缓存失败key[" + k + "], hk[" + hk + "], error[" + t + "]");
        }
        return false;
    }

    protected void renameKey(String oldKey, String newKey) {
        if (redisTemplate.hasKey(oldKey)) {
            redisTemplate.delete(newKey);
            redisTemplate.rename(oldKey, newKey);
            redisTemplate.delete(oldKey);
        } else {
            redisTemplate.delete(newKey);
        }
    }
}
