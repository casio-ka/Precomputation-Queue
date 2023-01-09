package com.devskiller.task.precomputation;

import com.devskiller.task.product.ProductSet;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static com.devskiller.task.precomputation.PrecomputationReason.PRODUCT_SET_NEVER_COMPUTED;
import static com.devskiller.task.precomputation.PrecomputationReason.PRODUCT_SET_ALREADY_COMPUTED;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class PrecomputationQueueTest {

    private PrecomputationQueue queue;

    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    @Before
    public void setUp() {
        queue = new PrecomputationQueue();
    }

    @Test
    public void shouldGetQueueSize() throws Exception {
        // given
        queue.scheduleComputing(mock(ProductSet.class), PRODUCT_SET_NEVER_COMPUTED);
        queue.scheduleComputing(mock(ProductSet.class), PRODUCT_SET_NEVER_COMPUTED);
        queue.blockAndTakeNextPrioritizedTask();

        // when
        int queueSize = queue.getQueueSize();

        //then
        assertEquals(1, queueSize);
    }

    @Test
    public void shouldDispatchTasksByIncomingOrder() throws Exception {
        // given
        ProductSet productSet1 = mock(ProductSet.class, "set1");
        ProductSet productSet2 = mock(ProductSet.class, "set2");

        // when
        queue.scheduleComputing(productSet1, PRODUCT_SET_NEVER_COMPUTED);
        queue.scheduleComputing(productSet2, PRODUCT_SET_NEVER_COMPUTED);

        // then
        assertEquals(productSet1, queue.blockAndTakeNextPrioritizedTask().getProductSet());
        assertEquals(productSet2, queue.blockAndTakeNextPrioritizedTask().getProductSet());
    }

    @Test
    public void shouldDispatchTasksWithProperPriorityOfCause() throws Exception {
        // given
        ProductSet productSet1 = mock(ProductSet.class, "set1");
        ProductSet productSet2 = mock(ProductSet.class, "set2");

        // when
        queue.scheduleComputing(productSet1, PRODUCT_SET_NEVER_COMPUTED);
        queue.scheduleComputing(productSet2, PRODUCT_SET_ALREADY_COMPUTED);

        // then
        assertEquals(productSet2, queue.blockAndTakeNextPrioritizedTask().getProductSet());
        assertEquals(productSet1, queue.blockAndTakeNextPrioritizedTask().getProductSet());
    }
}