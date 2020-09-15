package utils;

import java.util.ArrayList;

public class MinPriorityQueue<T extends Comparable<T>> {

  private ArrayList<T> binHeap;
  private int binSize;
  private int noOfElem = 0;
  /** Creates an empty queue. */
  public MinPriorityQueue() {
    binHeap = new ArrayList<>();
    binSize = 0;
  }

  /** Swaps elements in the binary heap */
  private void swap(int swapTo, int swapFrom) {
    T tempHolder = binHeap.get(swapTo);
    binHeap.set(swapTo, binHeap.get(swapFrom));
    binHeap.set(swapFrom, tempHolder);
  }

  /** Shifts child index up the heap if smaller. */
  private void swapUpHeap(int childIndex, int parentIndex) {

    if (parentIndex < 0) return;

    if (binHeap.get(parentIndex).compareTo(binHeap.get(childIndex)) > 0) {
      swap(parentIndex, childIndex);

      if (parentIndex != 0) {
        swapUpHeap(parentIndex, (parentIndex + 1) / 2 - 1);
      }
    }
  }

  /** Shifts the smaller of the left or right child up the heap once first element is removed. */
  private void SwapDownHeap(int parentIndex, int leftChildIndex, int rightChildIndex) {

    if (leftChildIndex > noOfElem - 1 || rightChildIndex > noOfElem - 1) {
      return;
    }

    T parent = binHeap.get(parentIndex);
    T leftChild = binHeap.get(leftChildIndex);
    T rightChild = binHeap.get(rightChildIndex);

    if (parent.compareTo(leftChild) > 0 || parent.compareTo(rightChild) > 0) {
      if (rightChild.compareTo(leftChild) > 0) {
        swap(parentIndex, leftChildIndex);
        SwapDownHeap(leftChildIndex, (leftChildIndex + 1) * 2 - 1, (leftChildIndex + 1) * 2);
      } else {
        swap(parentIndex, rightChildIndex);
        SwapDownHeap(rightChildIndex, (rightChildIndex + 1) * 2 - 1, (rightChildIndex + 1) * 2);
      }
    }
  }

  /** Returns the number of elements currently in the queue. */
  public int size() {
    return noOfElem;
  }

  /** Adds elem to the queue. */
  public void add(T elem) {
    binHeap.add(elem);
    swapUpHeap(noOfElem, (noOfElem + 1) / 2 - 1);
    noOfElem++;
  }

  /** Removes, and returns, the element at the front of the queue. */
  public T remove() {

    T firstElem;

    if (noOfElem > 0) {
      firstElem = binHeap.get(0);
      binHeap.set(0, binHeap.get(noOfElem - 1));
      SwapDownHeap(0, 1, 2);
      binHeap.remove(noOfElem - 1);
      noOfElem--;
      return firstElem;
    }
    return null;
  }

  /** Returns true if the queue is empty, false otherwise. */
  public boolean isEmpty() {
    return noOfElem == 0;
  }

  /** returns element of heap at index i. */
  public T getElem(int i) {

    return binHeap.get(i);
  }
}
