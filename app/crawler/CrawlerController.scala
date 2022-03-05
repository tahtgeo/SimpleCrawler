package crawler

import com.google.inject.Inject
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

import scala.collection.parallel.CollectionConverters._

class CrawlerController @Inject()(cc: ControllerComponents, titleParser: WebPageTitleParser) extends AbstractController(cc) {
  def crawl = Action { request =>
    request.body.asJson.map(_.as[CrawlRequest]).map { crawlRequest =>
      Ok(Json.toJson(crawlRequest.urls.par.map(crawlForUrl).toVector))
    }.getOrElse(BadRequest("Failed to parse JSON. Please, check your input"))
  }

  def crawlForUrl(url: UrlString): CrawlResult =
    CrawlResult(url, titleParser.fetchPageTitle(url))
}
