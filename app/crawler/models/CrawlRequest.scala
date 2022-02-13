package crawler.models

import crawler.UrlString
import io.circe._
import io.circe.generic.semiauto._

final case class CrawlRequest(urls: Vector[UrlString])

object CrawlRequest {
  implicit val crawlRequestDecoder: Decoder[CrawlRequest] = deriveDecoder
  implicit val crawlRequestEncoder: Encoder[CrawlRequest] = deriveEncoder
}

