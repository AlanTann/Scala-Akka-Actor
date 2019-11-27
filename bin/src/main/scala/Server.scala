
import akka.actor._

object HelloRemote extends App  {
  val system = ActorSystem("HelloRemoteSystem")
  val remoteActor = system.actorOf(Props[RemoteActor], name = "RemoteActor")
  remoteActor ! "The RemoteActor is alive"//have a remote contxt to refer ti srver
}

class RemoteActor extends Actor {
  def receive = {
    case msg: String =>
        println(s"RemoteActor received message '$msg'")
        sender ! "Hello from the RemoteActor"
  }
}
