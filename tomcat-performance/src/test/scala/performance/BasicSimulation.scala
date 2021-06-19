package performance

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt

class LoadSimulation extends Simulation {

  private val baseUrl = "http://localhost:8090"
  private val contentType = "application/json"
  private val requestCount = 4
  private val delay = 40
  private val blockingEndpoint = "/blocking/" + delay
  private val completableEndpoint = "/completable-future/" + delay
  private val webfluxEndpoint = "/webflux/" + delay


  private val httpConf = http
    .baseUrl(baseUrl)
    .inferHtmlResources()
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
    .connectionHeader("keep-alive")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")
    .silentResources // Silence all resources like css or css so they don't clutter the results

  val headers_http = Map(
    "Accept" -> """application/json"""
  )

  val scn = scenario("LoadSimulation")
    .repeat(requestCount) {
      exec(http("load-test")
        .get(blockingEndpoint) //change this to test other endpoints...
        .header("Content-Type", contentType)
        .check(status.is(200)))
        .pause(1 seconds, 2 seconds)
    }

  val users = scenario("Users").exec(scn)

  setUp(
    users.inject(rampUsers(Integer.getInteger("users", 10000)) during (Integer.getInteger("ramp", 1) minutes))
  ).protocols(httpConf)


  //  setUp(loadScenario.inject(rampUsersPerSec(200) to (1000) during(1.minutes)))
  //    .protocols(httpConf)

  //  setUp(loadScenario.inject(heavisideUsers(15000) during(1.minutes)))
  //    .protocols(httpConf)

  //  setUp(loadScenario.inject(constantUsersPerSec(500) during(30.seconds)))
  //    .protocols(httpConf)

  //  setUp(loadScenario.inject(atOnceUsers(5000)))
  //    .protocols(httpConf)
}