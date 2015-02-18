import java.io.File

object FileScript {

  val lookingFor = ".*txt".r

  def walk(thisDir: File): Unit = {
    thisDir.listFiles()
      .filter(!_.isDirectory)
      .filter(matches)
      .map(process)
      .foreach(f => println("processed file " + f))

    thisDir.listFiles()
      .filter(_.isDirectory)
      .foreach(f => walk(f))
  }

  def process(file: File) = {
    println(s"I am process, and the file is %file")
  }

  private def matches(file : File) : Boolean = {
    lookingFor.findFirstIn(file.getName).isDefined
  }

  def main(args: Array[String]): Unit = {
    val currentPath = new File(".").getCanonicalPath
    walk(new File(currentPath))
  }

}
