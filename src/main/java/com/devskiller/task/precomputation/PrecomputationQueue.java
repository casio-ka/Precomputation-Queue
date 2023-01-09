package com.devskiller.task.precomputation;

import com.devskiller.task.product.ProductSet;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PrecomputationQueue {

    private BlockingQueue<PrecomputationTask> queue;

    public PrecomputationQueue() {
        this.queue = new PriorityBlockingQueue<>(11, new PrecomputationTaskComparator());
    }

    public void scheduleComputing(ProductSet productSet, PrecomputationReason reason) {
        queue.add(new PrecomputationTask(productSet, reason));
    }

    public PrecomputationTask blockAndTakeNextPrioritizedTask() throws InterruptedException {
        return queue.take();
    }

    public int getQueueSize() {
        return queue.size();
    }

    private static class PrecomputationTaskComparator implements Comparator<PrecomputationTask> {
        @Override
        public int compare(PrecomputationTask t1, PrecomputationTask t2) {
            if (t1.getReason() != t2.getReason()) {
                return t1.getReason().compareTo(t2.getReason());
            }
            if (t1.getProductSet().getImportance() != t2.getProductSet().getImportance()) {
                return t1.getProductSet().getImportance() - t2.getProductSet().getImportance();
            }
            return t1.getSchedulingOrder() - t2.getSchedulingOrder();
        }
    }
}
