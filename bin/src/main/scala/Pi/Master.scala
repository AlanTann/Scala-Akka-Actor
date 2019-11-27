package Pi
import akka.actor._
import akka.routing._

// need to know the number of worker and number of element
class Master(numWorker:Int, nbElement:Int, resultTeller: ActorRef) extends Actor{
  import Master._
  import Worker._
  import ResultTeller._
  var acc: Double = 0.0
  var resultReceive: Int = 0
  
  //nned to use router to create worker
  val workerRouter = {
    val routees = Vector.fill(numWorker){
      val r = context.actorOf(Props[Worker])
      context watch r
      ActorRefRoutee(r)
    }
    Router(RoundRobinRoutingLogic(),routees)
  }
  def receive = {
    //start the worker send out the work
    //how many worker?
    //how many element?
    case Calculate =>
      for (i <- 0 until numWorker)
        workerRouter.route(Work(i * nbElement, nbElement), self)
    
    //accumulate result into total  
    case Result(partial) =>
      acc = acc + partial
      resultReceive = resultReceive + 1
      if (resultReceive == numWorker){
        resultTeller ! Answer(acc)
        
      }
  }
  
}
object Master {
  case object Calculate
}