package com.example.v1.semojo.proc;

import java.util.Deque;
import java.util.LinkedList;


public class ProcPool {
    private volatile static ProcPool procPool = null;
    private Deque<Proc> procQueue = new LinkedList<>();
    private int poolSize;

    private ProcPool(){

    }

    public ProcPool getProcPool(){
        if (procPool == null){
            synchronized (ProcPool.class){
                procPool = new ProcPool();
            }
        }
        return procPool;
    }
}
