https://blog.csdn.net/m0_58709145/article/details/127319766?spm=1001.2101.3001.6650.2&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-2-127319766-blog-131683181.235%5Ev38%5Epc_relevant_anti_t3_base&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-2-127319766-blog-131683181.235%5Ev38%5Epc_relevant_anti_t3_base&utm_relevant_index=3

消息丢失：
在异常情况下，比如交换机挂了，消费者挂了，都会导致消息丢失的情况
1.生产者到rabbitmq消息丢失，这个可以用手动ack确认来解决
2.消息到达rabbitmq中丢失，这个可以用rabbit中的持久化来解决
3.rabbitmq到消费者过程丢失，不能判断是否被消费，这个也可以用回调的方式来解决
消息生产者到rabbitMq手动ACK确认
在原有的生产者代码的application.yml文件中增加如下配置
版权声明：本文为CSDN博主「于京京9909」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/m0_58709145/article/details/127319766

