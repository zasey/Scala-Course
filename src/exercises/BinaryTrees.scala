package exercises

import scala.annotation.tailrec

object BinaryTrees extends App {

  abstract class BinaryTree[+T] {
    def value: T
    def leftChild: BinaryTree[T]
    def rightChild: BinaryTree[T]

    def isEmpty:  Boolean
    def isLeaf: Boolean
    def collectLeaves: List[BinaryTree[T]]
    def countLeaves: Int
    def nodesAtLevel(level: Int): List[BinaryTree[T]]
    def collectNodes(): List[T]
  }

  case class Node[+T](
                       override val value: T,
                       override val leftChild: BinaryTree[T],
                       override val rightChild: BinaryTree[T])
    extends BinaryTree[T] {

    override def isEmpty: Boolean = false
    override def isLeaf: Boolean = leftChild.isEmpty && rightChild.isEmpty

    override def collectLeaves: List[BinaryTree[T]] = {
      @tailrec
      def loop(toInspect: List[BinaryTree[T]] = List(this), leaves: List[BinaryTree[T]] = List()): List[BinaryTree[T]] = {
        if(toInspect.isEmpty) leaves
        else if(toInspect.head.isLeaf)
          loop(toInspect.tail, leaves :+ toInspect.head)
        else {
          if(toInspect.head.leftChild.isEmpty)
            loop(toInspect.tail :+ toInspect.head.rightChild, leaves)
          else if(toInspect.head.rightChild.isEmpty)
            loop(toInspect.tail :+ toInspect.head.leftChild, leaves)
          else
            loop(toInspect.tail :+ toInspect.head.leftChild :+ toInspect.head.rightChild, leaves)
        }
      }
      loop()
    }

    override def countLeaves: Int = {
      this.collectLeaves.size
    }

    override def nodesAtLevel(level: Int): List[BinaryTree[T]] = {
      @tailrec
      def loop(toInspect: List[BinaryTree[T]] = List(this), currentLevel: Int = 0, nextLevelNotes: List[BinaryTree[T]] = List()): List[BinaryTree[T]] = {
        if(toInspect.isEmpty && nextLevelNotes.isEmpty)
          toInspect
        else if(toInspect.isEmpty)
          loop(nextLevelNotes, currentLevel + 1, List())
        else if(currentLevel == level)
          toInspect
        else {
          if(toInspect.head.rightChild.isEmpty && toInspect.head.leftChild.isEmpty)
            loop(toInspect.tail, currentLevel, nextLevelNotes)
          else if(!toInspect.head.rightChild.isEmpty && !toInspect.head.leftChild.isEmpty)
            loop(toInspect.tail, currentLevel, nextLevelNotes :+ toInspect.head.leftChild :+ toInspect.head.rightChild)
          else if (toInspect.head.rightChild.isEmpty)
            loop(toInspect.tail, currentLevel, nextLevelNotes :+ toInspect.head.leftChild)
          else
            loop(toInspect.tail, currentLevel, nextLevelNotes :+ toInspect.head.rightChild)
        }
      }
      loop()
    }

    override def collectNodes(): List[T] = {
      @tailrec
      def loop(toInspect: List[BinaryTree[T]] = List(this), acc: List[T] = List()): List[T] = {
        if(toInspect.isEmpty) acc
        else if(!toInspect.head.isLeaf)
          if(toInspect.head.leftChild.isEmpty)
            loop(toInspect.tail :+ toInspect.head.rightChild, acc :+ toInspect.head.value)
          else if(toInspect.head.rightChild.isEmpty)
            loop(toInspect.tail :+ toInspect.head.leftChild, acc :+ toInspect.head.value)
          else
            loop(toInspect.tail :+ toInspect.head.rightChild :+ toInspect.head.leftChild, acc :+ toInspect.head.value)
        else
          loop(toInspect.tail, acc :+ toInspect.head.value)
      }
      loop()
    }
  }

  case object TreeEnd extends BinaryTree[Nothing] {
    override def value: Nothing = throw new NoSuchElementException
    override def leftChild: BinaryTree[Nothing] = throw new NoSuchElementException
    override def rightChild: BinaryTree[Nothing] = throw  new NoSuchElementException

    override def isEmpty: Boolean = true
    override def isLeaf: Boolean = false

    override def collectLeaves: List[BinaryTree[Nothing]] = List()

    override def collectNodes(): List[Nothing] = List()

    override def countLeaves: Int = 0

    override def nodesAtLevel(level: Int): List[BinaryTree[Nothing]] = List()
  }

  val tree = Node(1,
    Node(2,
      Node(4,
        TreeEnd,
        TreeEnd),
      Node(5,
        TreeEnd,
        Node(8,
          TreeEnd,
          TreeEnd))),
    Node(3,
      Node(6,
        TreeEnd,
        TreeEnd),
      Node(7,
        TreeEnd,
        TreeEnd)))

  println(tree.nodesAtLevel(5).map(node=>node.value).sorted)
  println(tree.collectNodes())
}
