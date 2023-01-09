package com.devskiller.task.precomputation;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PrecomputationNodeManager {

    private PrecomputationQueue precomputationQueue;

    public PrecomputationNodeManager(PrecomputationQueue precomputationQueue) {
        this.precomputationQueue = precomputationQueue;
    }

    public void startPrecomputationNodes(int nodesAmount) {

        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(nodesAmount, nodesAmount,
                0L, TimeUnit.MILLISECONDS, queue);
        for (int i = 0; i < nodesAmount; i++) {
            threadPoolExecutor.execute(new PrecomputationNode(precomputationQueue));
        }
    }
}
