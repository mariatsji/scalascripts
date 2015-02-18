import scala.util.Try
import scala.io.Source
import java.io.{PrintWriter, File}

object TodoScript {

  case class Todo(text: String) {
    override def toString = text
  }

  def main(args: Array[String]) {
    var databaseFile = "/home/sjur/shellscripts/todo.db"
    if (args.size > 0 && !args(0).isEmpty && !args(0).equals("")) {
      databaseFile = args(0)
    }
    val todos = loadTodos(databaseFile)
    val newTodos = inputPrintLoop(todos)
    write(newTodos, databaseFile)
    System.exit(0)
  }

  private def write(todos: List[Todo], file: String): Unit = {
    val pw: PrintWriter = new PrintWriter(new File(file))
    try {
      todos.foreach(pw.println)
    } finally {
      pw.close()
    }
  }

  private def inputPrintLoop(todos: List[Todo]): List[Todo] = {
    printTodo(todos)
    val input: Either[Int, String] = readInput
    val newTodos = process(input, todos)
    printTodo(newTodos)
    newTodos
  }

  def add(s: String, todos: List[Todo]): List[Todo] = {
    if (s.trim().nonEmpty)
      todos ::: List(Todo(s))
    else
      todos
  }

  def process(line: Either[Int, String], todos: List[Todo]): List[Todo] = {
    line.fold(i => process(i, todos), s => add(s, todos))
  }

  def process(i: Int, todos: List[Todo]) : List[Todo] = {
    todos.take(i - 1) ::: todos.drop(i)
  }

  private def readInput: Either[Int, String] = {
    val line = scala.io.StdIn.readLine()
    try {
      Left(line.trim().toInt)
    } catch {
      case _: NumberFormatException => Right(line.trim())
    }
  }

  def loadTodos(filename: String) : List[Todo] =
    Try(Source.fromFile(filename).getLines().toList.map(s => Todo(s)))
    .getOrElse(List())

  private def printTodo(todos: List[Todo]) {
    val lineNumber = (1 to todos.size).toList
    val zipped = lineNumber.map(_ + " ") zip todos
    printHeading()
    printBody(zipped)
  }

  private def printHeading() {
    println(
    """TODO
      |###""".stripMargin)
  }

  private def printBody(zipped: List[(String, Todo)]) {
    zipped.foreach(println)
    if (zipped.isEmpty)
      println("<EMPTY>")
  }
}
