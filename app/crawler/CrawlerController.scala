package crawler

import com.google.inject.Inject
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}

class CrawlerController @Inject()(cc: ControllerComponents, titleParser: WebPageTitleParser)
                                 (implicit ec: ExecutionContext) extends AbstractController(cc) {

  def crawl: Action[AnyContent] = Action.async { request =>
    request.body.asJson.map(_.as[CrawlRequest]).map { crawlRequest =>
      Future.sequence(crawlRequest.urls.map(crawlForUrl))
        .map(results => Ok(Json.toJson(results)))
    }.getOrElse(Future.successful(BadRequest("Failed to parse JSON. Please, check your input")))
  }

  def crawlForUrl(url: UrlString): Future[CrawlResult] = {
    titleParser.fetchPageTitle(url).map(CrawlResult(url, _))
  }
}
