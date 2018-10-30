package com.fh.api.util;

public class CacheManager {
    private BaseCache bc;
    private static CacheManager instance;
    private static Object lock = new Object();
    private CacheManager(){
         bc = new BaseCache("fh_shop", 24*60*60);
    }

    public static CacheManager getInstance(){
        //双重判定锁来保证线程安全的同时,效率相对较高,保证了只有一个实例被创建
        if (instance == null){
            synchronized( lock ){
                if (instance == null){
                    instance = new CacheManager();
                }
            }
        }
        return instance;
    }

    public void putObj(String ids,Object obj){
        bc.put(ids,obj);
    }
    
    public void putObj(String ids,Object obj, int expire){
        bc.put(ids,obj,expire);
    }
    
   

    public Object getObj(String ids){
        try {
            return bc.get(ids);

        } catch (Exception e) {


            return null;
        }
    }

    public void remove(String ids){
        bc.remove(ids);
    }
    public void removeAll(){
        bc.removeAll();
    }

}
