docker run -d --hostname my-rabbit --name some-rabbit -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin rabbitmq:latest

重复创建exchange、queue不会返回错误
重复将queue绑定到exchange不会报错
**1、消费一个不存在的队列会报错**