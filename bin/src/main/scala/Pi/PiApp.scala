package Pi
import akka.actor._
/**
 * @author Jeffrey
 */
object PiApp extends App{
  val system = ActorSystem.create("PISystem")
  
  var resultT =
    system.actorOf(Props[ResultTeller], "ResultTeller")
    val master =
      system.actorOf(Props(new Master (numWorker = 5,nbElement =1000, resultTeller = resultT)))
      import Master._
      master ! Calculate
}