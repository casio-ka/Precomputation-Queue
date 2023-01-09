package com.devskiller.task.precomputation;

public class PrecomputationNode implements Runnable {

    private PrecomputationQueue precomputationQueue;

    public PrecomputationNode(PrecomputationQueue precomputationQueue) {
        this.precomputationQueue = precomputationQueue;
    }

    @Override
    public void run() {
        try {
            PrecomputationTask task = precomputationQueue.blockAndTakeNextPrioritizedTask();
            task.compute();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
