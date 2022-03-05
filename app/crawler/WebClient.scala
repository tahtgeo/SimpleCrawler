package crawler

import com.google.inject.{ImplementedBy, Singleton}
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.model.Document
import org.jsoup.Connection

import scala.util.Try

@ImplementedBy(classOf[WebClientImpl])
trait WebClient {
  def getDocByUrl(url: UrlString): Option[Document]
}

@Singleton
class WebClientImpl extends WebClient {
  val CustomTimeoutMillis = 2000
  val browser = new JsoupBrowser {
    override def requestSettings(conn: Connection) =
      conn.timeout(CustomTimeoutMillis)
  }

  override def getDocByUrl(url: UrlString): Option[Document] = {
    Try(browser.get(url)).toOption
  }
}
