package crawler.models

import crawler.CrawlResult
import io.circe._
import io.circe.generic.semiauto._

final case class CrawlResponse(results: Vector[CrawlResult])

object CrawlResponse {
  implicit val crawlResponseDecoder: Decoder[CrawlResponse] = deriveDecoder
  implicit val crawlResponseEncoder: Encoder[CrawlResponse] = deriveEncoder
}
