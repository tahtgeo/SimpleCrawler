package crawler.controllers

import crawler.models.{CrawlRequest, CrawlResponse}
import crawler.services.WebPageTitleParser
import crawler.{CrawlResult, UrlString}
import io.circe
import io.circe.parser
import io.circe.syntax._
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

import javax.inject.Inject
import scala.collection.parallel.CollectionConverters.VectorIsParallelizable


class CrawlerController @Inject()(cc: ControllerComponents, titleParser: WebPageTitleParser) extends AbstractController(cc) {

  def crawl = Action { request: Request[AnyContent] =>
    val bodyJsonString = request.body.asJson.map(_.toString()).getOrElse("")

    parser.decode[CrawlRequest](bodyJsonString) match {
      case Left(_: circe.Error) => BadRequest(s"Failed to parse json, check your input.")
      case Right(crawlRequest) =>
        val results = crawlRequest.urls.par.map(crawlForUrl).toVector
        Ok(CrawlResponse(results).asJson.toString())
    }
  }

  private def crawlForUrl(url: UrlString): CrawlResult = {
    (url, titleParser.fetchPageTitle(url))
  }
}