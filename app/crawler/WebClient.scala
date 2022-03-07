package crawler

import com.google.inject.{ImplementedBy, Inject, Singleton}
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.model.Document
import org.jsoup.Connection

import scala.concurrent.Future
import scala.util.Try

@ImplementedBy(classOf[WebClientImpl])
trait WebClient {
  def getDocByUrl(url: UrlString): Future[Option[Document]]
}

@Singleton
class WebClientImpl @Inject()(cp: CustomContextProvider) extends WebClient {

  val CustomTimeoutMillis = 2000
  val browser = new JsoupBrowser {
    override def requestSettings(conn: Connection) =
      conn.timeout(CustomTimeoutMillis)
  }

  override def getDocByUrl(url: UrlString): Future[Option[Document]] = {
    Future {
      Try(browser.get(url)).toOption
    }(cp.webLookupsContext)
  }
}


