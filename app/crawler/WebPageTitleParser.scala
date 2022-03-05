package crawler

import com.google.inject.{ImplementedBy, Inject, Singleton}
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._
import org.apache.commons.validator.routines.UrlValidator

@ImplementedBy(classOf[WebPageTitleParserImpl])
trait WebPageTitleParser {
  val PageTitleTag = "title"
  def fetchPageTitle(url: UrlString): Option[PageTitle]
}

@Singleton
class WebPageTitleParserImpl @Inject()(webClient: WebClient) extends WebPageTitleParser {
  private val validator = UrlValidator.getInstance()

  override def fetchPageTitle(url: UrlString): Option[PageTitle] = {
    Some(url).filter(validator.isValid)
      .flatMap(webClient.getDocByUrl)
      .flatMap(x => x >?> text(PageTitleTag))
  }
}