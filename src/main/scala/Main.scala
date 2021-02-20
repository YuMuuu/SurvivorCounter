import com.redis.RedisClient

object Main extends App {
  val redisclient = new RedisClient("localhost", 6379)
  val redisClientRepository = new RedisCounterRepository(redisclient)
  val kiribanUseCase = new KiribanUseCase(redisClientRepository)
  redisClientRepository.countInit()

  val a = redisClientRepository.countGet()
  redisClientRepository.countSet(10)

  while(true) {
    val strAry = io.StdIn.readLine()

    strAry  match {
      case "count" => {
        println(kiribanUseCase.setKiribanToken)
      }
      case a if {a.split(" ")(0) == "register" && a.split(" ").length == 4} => {
        val token = a.split(" ")(1).toString()
        val name = a.split(" ")(2).toString()
        val message = a.split(" ")(3).toString()
        println(kiribanUseCase.setKiriban(token, name, message))
      }
      case "list" => {
//        println("未実装")
        //時間外労働
        println(redisClientRepository.list())
      }
      case _ => {
        println("不正なコマンドです")
      }
    }

  }

}