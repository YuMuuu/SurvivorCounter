import Main.redisClientRepository

class KiribanUseCase(redisCounterRepository: RedisCounterRepository) {
  val kiribanSet = Set(10, 20, 100, 400, 600, 90000, 11, 55, 222, 5555)

  //キリ番だったらkeyをきりばんにしてvalueにtokenを履く
  def setKiribanToken(): String= {

    redisClientRepository.counterIncr()
   val count = redisCounterRepository.countGet().get.toInt
    if(kiribanSet.contains(count)) {
      val token = java.util.UUID.randomUUID.toString
      redisClientRepository.tokenSet(token, count)
     "おめでとうございます。以下のtokenでキリ番を書き込んで下さい、token: " + token
    } else {
     "count:" + count
    }
  }


  //キリ番の名前とメッセージを入力したらtokenと同一か確認し同一なら上書きする
  def setKiriban(token: String, name: String, message: String) = {
    val a: Option[String] = redisClientRepository.tokenGet(token)
    if( a.isDefined) {
      redisCounterRepository.kiribanSet( a.get.toInt, "name:" + name + ", message: " + message)
      s"キリメッセージの投稿に成功しました。name:" + name +", message:"+token +" です。再投稿はできません"
    } else {
      "不正なトークンです"
    }
  }


  //マックスキリ版を取得sルウ
}
