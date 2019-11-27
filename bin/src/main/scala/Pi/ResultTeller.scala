package Pi

import akka.actor._
/**
 * @author Jeffrey
 */
class ResultTeller extends Actor{
  import ResultTeller._
  def receive = {
    case Answer(result) =>
      println(s"pi approximation is $result")
  } 
}
object ResultTeller{
  case class Answer(result: Double)
}