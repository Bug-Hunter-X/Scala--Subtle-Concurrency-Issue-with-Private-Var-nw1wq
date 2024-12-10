```scala
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

class MyClass(val name: String) {
  private var _age: Int = 0
  private val lock = new Object() // Add a lock for synchronization

  def age: Int = lock.synchronized { _age }

  def age_=(newAge: Int): Unit = lock.synchronized {
    if (newAge >= 0) {
      _age = newAge
    } else {
      throw new IllegalArgumentException("Age cannot be negative")
    }
  }
}

// Example usage with concurrent access
implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.global

val myInstance = new MyClass("Alice")

val future1 = Future {
  myInstance.age = 30
}

val future2 = Future {
  println(myInstance.age)
}

Future.sequence(Seq(future1, future2)).onComplete {
  case Success(_) => println("Concurrent access completed successfully")
  case Failure(exception) => println(s"Error during concurrent access: ${exception.getMessage}")
}
```