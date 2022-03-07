package crawler

import akka.actor.ActorSystem
import com.google.inject.{ImplementedBy, Inject, Singleton}

import scala.concurrent.ExecutionContext

@ImplementedBy(classOf[CustomContextProviderImpl])
trait CustomContextProvider {
  def webLookupsContext: ExecutionContext

  def documentParsingContext: ExecutionContext
}

@Singleton
class CustomContextProviderImpl @Inject()(as: ActorSystem) extends CustomContextProvider {
  override val webLookupsContext: ExecutionContext = as.dispatchers.lookup("contexts.web-lookups")
  override val documentParsingContext: ExecutionContext = as.dispatchers.lookup("contexts.document-parsing")
}