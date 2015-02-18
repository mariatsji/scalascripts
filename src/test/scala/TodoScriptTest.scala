import TodoScript.Todo
import org.scalatest.{FlatSpec, Matchers}

class TodoScriptTest extends FlatSpec with Matchers {

  "TodoScript" should "add todo" in {
    val todoString = "feed the cat"
    val todos = TodoScript.add(todoString, List())
    todos.length should be (1)
    todos(0).toString should equal (todoString)
  }

  "TodoScript" should "handle adding empty string" in {
    val todoString = ""
    val todos = TodoScript.add(todoString, List())
    todos.length should be (0)
  }

  "TodoScript" should "handle processing empty string" in {
    val todoString = ""
    val todos = TodoScript.process(Right(todoString), List())
    todos.length should be (0)
  }

  "TodoScript" should "handle delete item nr" in {
    val todos = List(Todo("do"), Todo("some"), Todo("stuff"))
    val newTodos = TodoScript.process(Left(2), todos)
    newTodos.length should be (2)
    newTodos(0).toString should equal ("do")
    newTodos(1).toString should equal ("stuff")
  }

  "TodoScript" should "handle delete in both ends" in {
    val todos = List(Todo("do"), Todo("some"), Todo("stuff"))
    val asdfTodos = TodoScript.process(Left(3), todos)
    val newTodos = TodoScript.process(Left(1), asdfTodos)
    newTodos.length should be (1)
    newTodos(0).toString should equal ("some")
  }

  "TodoScript" should "handle delete outside of list" in {
    val todos = List(Todo("do"), Todo("some"), Todo("stuff"))
    val newTodos = TodoScript.process(Left(5), todos)
    newTodos.length should be (3)
  }

}
