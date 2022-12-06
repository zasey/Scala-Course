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

  def hasPath(tree: BinaryTree[Int], target: Int): Boolean = {
    @tailrec
    def loop(toInspect: List[BinaryTree[Int]] = List(tree), savedNote: List[BinaryTree[Int]] = List(), acc: Int = 0, savedAcc:List[Int] = List(tree.value)): Boolean = {
      if(toInspect.isEmpty) false
      else if(toInspect.head.isLeaf)
        if(toInspect.head.value + acc == target) true
        else if(savedNote.isEmpty) false
        else loop(List(savedNote.head), savedNote.tail, savedAcc.head, savedAcc.tail)
      else
        if(toInspect.head.value + acc < target)
          if(toInspect.head.leftChild.isEmpty)
            loop(List(toInspect.head.rightChild), savedNote, acc + toInspect.head.value, savedAcc)
          else if(toInspect.head.rightChild.isEmpty)
            loop(List(toInspect.head.leftChild), savedNote, acc + toInspect.head.value, savedAcc)
          else
            loop(List(toInspect.head.leftChild), toInspect.head.rightChild +: savedNote, acc + toInspect.head.value, (toInspect.head.value + acc) +: savedAcc)
        else
          if(savedNote.isEmpty) false
          else
            loop(List(savedNote.head), savedNote.tail, savedAcc.head, savedAcc.tail)
    }
    if(tree.isEmpty) false
    else loop()
  }

  def findAllPaths(tree: BinaryTree[String], target: String): List[List[String]] = {
    @tailrec
    def loop(toInspect: List[BinaryTree[String]] = List(tree), savedNote: List[BinaryTree[String]] = List(), acc: Int = 0, savedAcc:List[Int] = List(tree.value.toInt), pathsList:List[List[BinaryTree[String]]] = List(), tempPathsList: List[List[BinaryTree[String]]] = List(), currentPath: List[BinaryTree[String]] = List()): List[List[BinaryTree[String]]] = {
      if(toInspect.isEmpty) pathsList
      else if(toInspect.head.isLeaf)
        if(toInspect.head.value.toInt + acc == target.toInt)
          if(savedNote.nonEmpty)
            loop(List(savedNote.head), savedNote.tail, savedAcc.head, savedAcc.tail, pathsList :+ (currentPath :+ toInspect.head), tempPathsList.tail, tempPathsList.head)
          else
            pathsList :+ (currentPath :+ toInspect.head)
        else if(savedNote.isEmpty) pathsList
        else loop(List(savedNote.head), savedNote.tail, savedAcc.head, savedAcc.tail, pathsList, tempPathsList.tail, tempPathsList.head)
      else
        if(toInspect.head.value.toInt + acc < target.toInt)
          if(toInspect.head.leftChild.isEmpty)
            loop(List(toInspect.head.rightChild), savedNote, acc + toInspect.head.value.toInt, savedAcc, pathsList, tempPathsList, currentPath :+ toInspect.head)
          else if(toInspect.head.rightChild.isEmpty)
            loop(List(toInspect.head.leftChild), savedNote, acc + toInspect.head.value.toInt, savedAcc, pathsList, tempPathsList, currentPath :+ toInspect.head)
          else
            loop(List(toInspect.head.leftChild), toInspect.head.rightChild +: savedNote, acc + toInspect.head.value.toInt, (toInspect.head.value.toInt + acc) +: savedAcc, pathsList, (currentPath :+ toInspect.head) +: tempPathsList, currentPath :+ toInspect.head)
        else
          if(savedNote.isEmpty) pathsList
          else
            loop(List(savedNote.head), savedNote.tail, savedAcc.head, savedAcc.tail, pathsList, tempPathsList.tail, tempPathsList.head)
    }
    if(tree.isEmpty) List()
    else loop().map(node => node.map(n => n.value))
  }

//  val tree = Node(1,
//    Node(2,
//      Node(4,
//        TreeEnd,
//        TreeEnd),
//      Node(5,
//        TreeEnd,
//        Node(8,
//          TreeEnd,
//          TreeEnd))),
//    Node(3,
//      Node(6,
//        TreeEnd,
//        TreeEnd),
//      Node(7,
//        TreeEnd,
//        TreeEnd)))

  val tree = Node("1",
    Node("2",
      Node("4",
        TreeEnd,
        TreeEnd),
      Node("5",
        TreeEnd,
        Node("8",
          TreeEnd,
          TreeEnd))),
    Node("3",
      Node("6",
        TreeEnd,
        TreeEnd),
      Node("7",
        TreeEnd,
        TreeEnd))
  )


  //println(tree.nodesAtLevel(5).map(node=>node.value).sorted)
  //println(tree.collectNodes())
  //println(hasPath(tree, 7))
  println(findAllPaths(tree, "11"))
}
