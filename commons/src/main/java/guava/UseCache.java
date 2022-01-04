package guava;

import com.google.common.cache.*;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.ListenableFuture;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * <see>https://www.cnblogs.com/rickiyang/p/11074159.html</see>
 */
public class UseCache {

    @Test
    public void testLoadingCache() throws ExecutionException {
        // 创建缓存（容器）
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                                                         // 数据写入1分钟后失效
                                                         .expireAfterWrite(1, TimeUnit.MINUTES)
                                                         // 支持缓存项的数据最大为3个
                                                         .maximumSize(3)
                                                         // 实现CacheLoader，当在缓存中未找到所需的缓存项时，会执行CacheLoader的load方法加载缓存。
                                                         .build(new CacheLoader<String, String>() {
                    // 方法的返回值会被缓存，注意，返回null时，会抛异常
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println("key：" + key + " 未找到，开始加载....");
                        return key + "-" + key;
                    }
                });

        // 加入缓存
        cache.put("Java", "spring");
        cache.put("PHP", "swoole");

        // 从缓存中获取值
        String res1 = cache.get("Java"); // get方法需要抛出ExecutionException异常
        // cache.getUnchecked("Java"); // 功能和get一样，但是不会throw异常
        System.out.println(res1);
        // 输出：spring

        // 缓存中为存放key为Golang的缓存项，所以进行加载
        String res2 = cache.get("Golang");
        System.out.println(res2);
        // key：Golang 未找到，开始加载....
        // Golang-Golang

        // Golang的缓存项前面已经加载过了，且没有被清除，所以这次直接获取到对应的value
        String res3 = cache.get("Golang");
        System.out.println(res3);
        // Golang-Golang

        // 前面添加了3个缓存项（已经达到设置上限，此时再添加缓存项，将会触发淘汰）
        cache.put("Node", "KOA");
        System.out.println(cache.get("PHP")); // PHP在cache中已被淘汰
        // key：PHP 未找到，开始加载....
        // PHP-PHP

        // 查询缓存，如果未找到，不会触发加载操作，而是范围null。
        String res4 = cache.getIfPresent("key");
        System.out.println(res4); // null
    }

    /**
     * guava cache支持批量添加、查询、清除操作
     * @throws ExecutionException
     */
    @Test
    public void testMultiple() throws ExecutionException {
        // 创建缓存
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(2) // 最大缓存项数量为2
                .expireAfterWrite(10, TimeUnit.SECONDS) // 数据写入10秒过期
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println("key：" + key + " 未找到，开始加载....");
                        return key + "-" + key;
                    }
                });

        Map<String, String> map = new HashMap<>();
        map.put("one", "111111");
        map.put("two", "222222");
        map.put("three", "3333333");
        // 批量添加
        loadingCache.putAll(map);

        List<String> keys = new ArrayList<>();
        keys.add("one");
        keys.add("two");
        // 批量获取
        ImmutableMap<String, String> allData = loadingCache.getAll(keys);
        System.out.println(allData);
        // {one=one-one, two=222222}

        // 批量清除
        loadingCache.invalidateAll(keys);
        loadingCache.invalidateAll(); // 全量清除
    }

    /**
     * 移除监听器，是指监听缓存项，当缓存项被清除时，执行指定对操作。
     */
    @Test
    public void testRemoveListender() {

        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                // 绑定"移除监听器"，当元素被清除时，执行onRemoval方法
                .removalListener(new RemovalListener<String, String>() {
                    @Override
                    public void onRemoval(RemovalNotification removalNotification) {
                        Object key = removalNotification.getKey();
                        Object val = removalNotification.getValue();
                        System.out.println("被清除的元素，key:" + key + ", val:" + val);

                    }
                })
                // 当在缓存中未找到key时，执行load操作。
                .build(new CacheLoader<String, String>() {
                    // 当key未找到时，执行的加载操作
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println("未找到key:" + key + "，开始加载...");
                        return key + key;
                    }
                });

        cache.put("one", "111111");
        cache.put("two", "123456");

        // 继续添加元素，会触发清理操作，触发移除监听器
        cache.put("three", "2233333");
        // 输出：被清除的元素，key:one, val:111111

        String res = cache.getUnchecked("four");
        System.out.println(res);
        // 输出
        // 未找到key:four，开始加载...
        // 被清除的元素，key:two, val:123456
        // fourfour
    }

    /**
     * 刷新缓存，是指一个缓存项写入缓存后，经过一段时间（设置的刷新间隔），再次访问该缓存项的时候，会刷新该缓存项，可以理解为将该项的key取出，执行CacheLoader的load方法，然后将返回值替换旧的值。并不是设置写入2秒后，就会被刷新，而是当写入2秒后，且再次被访问时，才会被刷新；如果一个缓存项写入的时间超过2秒，但是一直没有访问该项，那么这一项是不会被刷新的。这与Memecache和Redis的原理类似。
     * @throws InterruptedException
     * @throws ExecutionException
     */
     @Test
    public void testRefresh() throws InterruptedException, ExecutionException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                // 设置刷新的时机
                .refreshAfterWrite(2, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println("key：" + key + " 未找到，开始加载....");
                        return key + "-" + key;
                    }
                });

        cache.put("one", "11111");
        cache.put("two", "22222");

        // 休眠3秒
        Thread.sleep(3000L);

        System.out.println(cache.get("one"));
        //key：one 未找到，开始加载....
        //one-one

        System.out.println(cache.get("two"));
        //key：two 未找到，开始加载....
        //two-two
    }

    /**
     *  刷新操作自定义
     * @throws InterruptedException
     */
     @Test
    public void testReload() throws InterruptedException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                // 设置刷新的时机
                .refreshAfterWrite(2, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println("key：" + key + " 未找到，开始加载....");
                        return key + "-" + key;
                    }

                    // 刷新缓存时，执行的操作
                    @Override
                    public ListenableFuture<String> reload(String key, String oldValue) throws Exception {
                        System.out.println("刷新缓存项，key:" + key + ", oldValue:" + oldValue);
                        return super.reload(key, oldValue);
                    }
                });

        cache.put("hello", "world");
        Thread.sleep(3000L);

        System.out.println(cache.getUnchecked("hello"));
        // 刷新缓存项，key:hello, oldValue:world
        // key：hello 未找到，开始加载....
        // hello-hello
    }
}
