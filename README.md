## Introduction

You are going to asynchronously precompute some statistics about product sets. Whenever a product set is scheduled for precomputation, it produces a new `PrecomputationTask` which is inserted into the queue.
There are several precomputation nodes which should **endlessly** poll queue for new tasks and consume them by running a given precomputation task.

## Problem statement
- Finish implementation of `PrecomputationNode` and `PrecomputationNodeManager`. Precomputation should be done **in the background** on several `PrecomputationNodes`. Exact amount of nodes is defined by `nodesAmount` parameter in the `startPrecomputationNodes` method.
- Finish implementation of the `PrecomputationQueue` class. Precomputation tasks should be always processed in order, **prioritized by cause**:
    1) `ProductSet` which was **computed at least once**
    1) `ProductSet` which was **never computed**

    If both product sets have the same cause, you should break ties by:
    1) Importance of a product set - lower value means that a product set should be processed earlier.
    2) If importance is also equal, you should follow the order of scheduling (First In, First Out).


### Good Luck!


## Hints
- Consider thread safety.
- Producer and consumer share queue as a buffer, but you don't have to worry about the buffer size, because you can assume that consuming can be scaled-up to be fast enough, as the number of consumers is configurable.

