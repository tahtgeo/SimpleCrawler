import play.api.libs.json._

package object crawler {
  type PageTitle = String
  type UrlString = String

  final case class CrawlResult(urlString: UrlString, pageTitle: Option[PageTitle])

  object CrawlResult {
    implicit val writes = Json.writes[CrawlResult]
    implicit val reads = Json.reads[CrawlResult]
  }

  final case class CrawlRequest(urls: Vector[UrlString])

  object CrawlRequest {
    implicit val writes = Json.writes[CrawlRequest]
    implicit val reads = Json.reads[CrawlRequest]
  }

  final case class CrawlResponse(results: Vector[CrawlResult])

  object CrawlResponse {
    implicit val writes = Json.writes[CrawlResponse]
    implicit val reads = Json.reads[CrawlResponse]
  }

}
