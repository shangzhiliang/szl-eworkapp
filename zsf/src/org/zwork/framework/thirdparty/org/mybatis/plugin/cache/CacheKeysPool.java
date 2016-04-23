package org.zwork.framework.thirdparty.org.mybatis.plugin.cache;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class CacheKeysPool
{
  private Map<String, Set<Object>> pool = new ConcurrentHashMap();

  public Set<Object> get(String key) {
    if (this.pool.get(key) == null) {
      this.pool.put(key, new HashSet());
    }
    return (Set)this.pool.get(key);
  }

  public Set<Object> put(String key, Set<Object> value) {
    return (Set)this.pool.put(key, value);
  }

  public void putElement(String key, Object element) {
    if (this.pool.get(key) == null) {
      this.pool.put(key, new HashSet());
    }
    ((Set)this.pool.get(key)).add(element);
  }

  public Set<Object> remove(String key) {
    return (Set)this.pool.remove(key);
  }

  public void clear() {
    this.pool.clear();
  }

  public Set<String> keySet() {
    return this.pool.keySet();
  }

  public Map<String, Set<Object>> getOriginalPool() {
    return this.pool;
  }

  public Set<Map.Entry<String, Set<Object>>> entrySet() {
    return this.pool.entrySet();
  }

  public void putAll(CacheKeysPool pool)
  {
    Iterator localIterator2;
    for (Iterator localIterator1 = pool.entrySet().iterator(); localIterator1.hasNext(); 
      localIterator2.hasNext())
    {
      Map.Entry entry = (Map.Entry)localIterator1.next();
      localIterator2 = ((Set)entry.getValue()).iterator(); 
      Object item = localIterator2.next();
      putElement((String)entry.getKey(), item);
    }
  }
}