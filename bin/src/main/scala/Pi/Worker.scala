package Pi
import akka.actor._
/**
 * @author Jeffrey
 */
class Worker extends Actor{
  import Worker._
  def receive = {
    case Work(starting , nbElement) =>  
      var acc: Double = 0.0
      for (n <- starting until starting + nbElement){
        acc += 4.0 * (1 - (n%2)*2) / (2*n+1)
      }
      sender ! Result(acc)
  }
}
object Worker {
  case class Work(starting: Int,nbElement : Int)
  case class Result (partial: Double)
  }