package crawler

import com.google.inject.{ImplementedBy, Inject, Singleton}
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._
import org.apache.commons.validator.routines.UrlValidator

import scala.concurrent.Future

@ImplementedBy(classOf[WebPageTitleParserImpl])
trait WebPageTitleParser {
  val PageTitleTag = "title"

  def fetchPageTitle(url: UrlString): Future[Option[PageTitle]]
}

@Singleton
class WebPageTitleParserImpl @Inject()(webClient: WebClient, cp: CustomContextProvider) extends WebPageTitleParser {
  private val validator = UrlValidator.getInstance()

  override def fetchPageTitle(url: UrlString): Future[Option[PageTitle]] = {
    if (validator.isValid(url)) {
      webClient.getDocByUrl(url)
        .map(_.flatMap(_ >?> text(PageTitleTag)))(cp.documentParsingContext)
    } else {
      Future.successful(Option.empty)
    }
  }
}