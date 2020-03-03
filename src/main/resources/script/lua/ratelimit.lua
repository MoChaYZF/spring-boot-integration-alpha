-- KEYS[1] == 默认为ip地址
-- ARGV[1] == limitCount == 10
-- ARGV[2] == limitPeriod == 100(s)
local c
c = redis.call('get', KEYS[1])
-- 调用超过最大值，则直接返回
if c and tonumber(c) > tonumber(ARGV[1]) then
    return c;
end
-- 执行自加
-- 如果指定的key不存在，那么在执行incr操作之前，会先将它的值设定为0
c = redis.call('incr', KEYS[1])
if tonumber(c) == 1 then
    -- 从第一次调用开始限流，设置对应键值的过期
    -- 设置一个key的过期秒数
    redis.call('expire', KEYS[1], ARGV[2])
end
return c;
