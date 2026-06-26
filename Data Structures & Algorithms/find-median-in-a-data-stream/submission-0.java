public class MedianFinder {

    // Max heap that stores the smaller half of the numbers
    private Queue<Integer> smallHeap;
    // Min heap that stores the larger half of the numbers
    private Queue<Integer> largeHeap;

    public MedianFinder() {
        // Largest element is at the top
        smallHeap = new PriorityQueue<>((a, b) -> b - a);
        // Smallest element is at the top
        largeHeap = new PriorityQueue<>((a, b) -> a - b);
    }

    public void addNum(int num) {
        // Always insert into the max heap first
        smallHeap.add(num);
        // Move the largest element from the smaller half to the larger half if:
        // 1. The max heap has more than one extra element, or
        // 2. The ordering is incorrect (an element in the smaller half is larger
        //    than an element in the larger half)
        if (
            smallHeap.size() - largeHeap.size() > 1 ||
            !largeHeap.isEmpty() &&
            smallHeap.peek() > largeHeap.peek()
        ) {
            largeHeap.add(smallHeap.poll());
        }
        // If the min heap becomes too large, move its smallest element back
        // to the max heap to rebalance the sizes
        if (largeHeap.size() - smallHeap.size() > 1) {
            smallHeap.add(largeHeap.poll());
        }
    }

    public double findMedian() {
        // If both heaps have the same number of elements,
        // the median is the average of their top elements
        if (smallHeap.size() == largeHeap.size()) {
            return (double) (largeHeap.peek() + smallHeap.peek()) / 2;
        }
        // Otherwise, the heap with one extra element contains the median
        else if (smallHeap.size() > largeHeap.size()) {
            return (double) smallHeap.peek();
        } else {
            return (double) largeHeap.peek();
        }
    }
}