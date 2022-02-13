package object crawler {
  type PageTitle = String
  type UrlString = String
  type CrawlResult = (UrlString, Option[PageTitle])
}
