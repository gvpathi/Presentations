def receive = {
  ...
  case Stop  => managedActors foreach (_ ! Stop)
  case Start => managedActors foreach (_ ! Start)
  case Ping => 
    val futures = for {     // alternative syntax
      actor <- managedActors
    } yield(actor !!! Ping)
    Futures.awaitAll(futures)
    futures map { _.result match 
      case Some(x) => x
      case None => "Error!"
    }
}
