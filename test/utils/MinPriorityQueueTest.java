package utils;


import static org.hamcrest.CoreMatchers.nullValue;

import org.junit.Test;
import utils.MinPriorityQueue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MinPriorityQueueTest {

  int smallSize = 2;
  MinPriorityQueue<Integer> priorityQueue = new MinPriorityQueue<>();
  MinPriorityQueue<Integer> fullQueue = new MinPriorityQueue<>();

  @Test
  public void InitialisationOfMinPriorityQueueCreatesAnEmptyList() {

    assertThat(priorityQueue.isEmpty(), is(true));
  }

  @Test
  public void addingANewElementToTheQueueWillIncreaseTheNoOfElementCountsByOne() {
    priorityQueue.add(1);
    assertThat(priorityQueue.size(), is(1));
  }

  @Test
  public void removingAnElementFromTheFrontOfQueueWillReturnTheFirstElementOfTheQueue() {
    priorityQueue.add(1);
    assertThat(priorityQueue.remove(), is(1));
  }

  @Test
  public void removingAnElementFromTheFrontOfQueueWillReduceTheNoOfElementCountsByOne() {
    priorityQueue.add(1);
    priorityQueue.remove();
    assertThat(priorityQueue.size(), is(0));
  }

  @Test
  public void removingAnElementFromAnEmptyQueueReturnsNull() {
    assertThat(priorityQueue.remove(), is(nullValue()));
  }

  @Test
  public void removingAnElementFromAnEmptyQueueDoesNotDecrementNoOfElementCount() {
    priorityQueue.remove();
    assertThat(priorityQueue.size(), is(0));
  }

  @Test
  public void addingNewElementSmallerThanPrecedingElementToQueueWillSetNewElementToFrontOfQueue() {
    priorityQueue.add(5);
    priorityQueue.add(2);

    assertThat(priorityQueue.remove(), is(2));
  }

  @Test
  public void shiftSecondElementOfQueueToFourthPositionAfterAddingSmallerElement() {
    priorityQueue.add(4);
    priorityQueue.add(6);
    priorityQueue.add(11);
    priorityQueue.add(5);

    assertThat(priorityQueue.getElem(1), is(5));
  }

  @Test
  public void arrayOrderShouldStayTheSameEvenIfNewestElementIsSmallerThanPrecedingElement() {
    priorityQueue.add(4);
    priorityQueue.add(6);
    priorityQueue.add(5);

    assertThat(priorityQueue.getElem(2), is(5));
  }

  @Test
  public void shiftNextSmallestElementToFirstPositionAfterRemovingFirstElement() {
    priorityQueue.add(4);
    priorityQueue.add(6);
    priorityQueue.add(5);
    priorityQueue.add(7);
    priorityQueue.remove();
    assertThat(priorityQueue.getElem(0), is(5));
  }

  @Test
  public void shiftTwoElementsUpTheQueueWhenFirstElementRemoved() {
    priorityQueue.add(4);
    priorityQueue.add(6);
    priorityQueue.add(5);
    priorityQueue.add(7);
    priorityQueue.add(10);
    priorityQueue.add(8);
    priorityQueue.add(9);
    priorityQueue.remove();
    assertThat(priorityQueue.getElem(0), is(5));
    assertThat(priorityQueue.getElem(2), is(8));
    assertThat(priorityQueue.getElem(5), is(9));
  }

  @Test
  public void shiftTwoElementsUpTheQueueWhenFirstElementRemovedOnLeftSide() {
    priorityQueue.add(4);
    priorityQueue.add(5);
    priorityQueue.add(6);
    priorityQueue.add(7);
    priorityQueue.add(10);
    priorityQueue.add(8);
    priorityQueue.add(9);
    priorityQueue.remove();
    assertThat(priorityQueue.getElem(0), is(5));
    assertThat(priorityQueue.getElem(1), is(7));
    assertThat(priorityQueue.getElem(3), is(9));
  }


  @Test
  public void addNewElementToQueueWhereQueueIsAtInitialCapacity() {
    fullQueue.add(2);
    fullQueue.add(1);
    fullQueue.add(3);
    assertThat(fullQueue.getElem(2), is(3));
  }

  @Test
  public void QueueCanTakeInTwoElementsOfTheSameValue() {
    priorityQueue.add(1);
    priorityQueue.add(3);
    priorityQueue.add(2);
    priorityQueue.add(1);
    assertThat(priorityQueue.getElem(1), is(1));
  }

  @Test
  public void QueueCanCompareTwoIdenticalElementsWhenFirstElementRemovedInQueue() {
    priorityQueue.add(1);
    priorityQueue.add(2);
    priorityQueue.add(2);
    priorityQueue.add(3);
    priorityQueue.remove();
    assertThat(priorityQueue.getElem(0), is(2));
    assertThat(priorityQueue.getElem(2), is(3));
  }
}
