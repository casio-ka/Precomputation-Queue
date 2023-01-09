package com.devskiller.task.precomputation;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

public class PrecomputationIntegrationTest {

    private PrecomputationQueue precomputationQueue;

    private PrecomputationTask precomputationTask;

    private PrecomputationNodeManager precomputationNodeManager;

    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    @Before
    public void setUp() throws InterruptedException {
        precomputationQueue = mock(PrecomputationQueue.class);
        precomputationTask = mock(PrecomputationTask.class);
        precomputationNodeManager = new PrecomputationNodeManager(precomputationQueue);

        when(precomputationQueue.blockAndTakeNextPrioritizedTask()).thenAnswer((Answer<PrecomputationTask>) invocationOnMock -> {
            Thread.sleep(20);
            return precomputationTask;
        });
    }

    @Test
    public void shouldStopPrecomputingIfQueueThrowsInterruptedException() throws InterruptedException {
        // given
        doThrow(new InterruptedException("stopped")).when(precomputationQueue).blockAndTakeNextPrioritizedTask();
        // when
        new PrecomputationNode(precomputationQueue).run();
    }

    @Test
    public void shouldTakeTaskFromQueueAndPrecompute() {
        // when
        precomputationNodeManager.startPrecomputationNodes(1);

        // then
        verify(precomputationTask, timeout(200).atLeastOnce()).compute();
    }
}