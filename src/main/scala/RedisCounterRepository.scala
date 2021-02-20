import com.redis.RedisClient

class RedisCounterRepository(redisClient: RedisClient) {
  // パフォーマンスを考慮しないのでconnection使わない方のAPIにする

  //countを初期化する
  def countInit(): Boolean = redisClient.set("count", 0)


  //countを1incrする
  def counterIncr(): Option[Int] = redisClient.incr("count").map(_.toInt)

  def countGet(): Option[String] = redisClient.get("count")

  //デバッグ用
  def countSet(value: Int) = redisClient.set("count", value)


  def kiribanSet(key: Int, value: String): Boolean = redisClient.set(key, value)

  def tokenSet(key: String, value: Int) = redisClient.set(key, value)

  def tokenGet(key: String) = redisClient.get(key)

  def kirimanGet(key: String, value: String): Option[String] = redisClient.get(key, value)


  def maxKiribanSet(value: Int): Boolean = redisClient.set("maxKiriban", value)

  def maxKiribanGet(): Option[String] = redisClient.get("maxKiriban")


  //時間外労働
  val kiribanSet = Set(10, 20, 100, 400, 600, 90000, 11, 55, 222, 5555)
  def list() = kiribanSet.map(redisClient.get(_)).flatten
}
