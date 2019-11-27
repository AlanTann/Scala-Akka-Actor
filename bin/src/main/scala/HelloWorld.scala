import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor._

// (1) changed the constructor here
//myName is a constructor that received...the constructor is called myname/...myname is not variable
/*class HelloActor extends Actor {
  def receive = {
    case "hello" => println("hello back at you")
    case _       => println("huh?")
  }
}*/


/*object Main extends App {
  val system = ActorSystem("HelloSystem")
  // default Actor pconstructor
  val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
  helloActor ! "hello"
  helloActor ! "buenos dias"
}*/

// (1) changed the constructp++++++++++++++++or here
class HelloActor(myName: String) extends Actor {
  import HelloActor.Ping
  def receive = {
    // (2) changed these println statements
    case "hello" => println(s"hello from $myName")
    case _       => println("'huh?', said %s".format(myName))
  }
}
object HelloActor{
  case class Ping()
}
object Main extends App {
  import HelloActor.Ping
  val system = ActorSystem("HelloSystem")
  // (3) changed this line of code
  val helloActor = system.actorOf(Props(new HelloActor("Fred")), name = "helloactor")
  helloActor ! "hello"//! is tell...fire and forget..is non-blockings
  helloActor !  Ping()
}

