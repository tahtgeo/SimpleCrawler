package crawler.services

import com.google.inject.ImplementedBy
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.model.Document
import org.jsoup.Connection

import scala.util.Try

@ImplementedBy(classOf[WebClientImpl])
trait WebClient {
  def getDocByUrl(url: String): Option[Document]
}

class WebClientImpl extends WebClient {
  val CustomTimeoutMillis = 3000

  override def getDocByUrl(url: String): Option[Document] = {

    val browser = new JsoupBrowser {
      override def requestSettings(conn: Connection) =
        conn.timeout(CustomTimeoutMillis)
    }

    Try(browser.get(url)).toOption
  }
}
