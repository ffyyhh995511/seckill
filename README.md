# seckill
商品抢购项目
核心是锁，采用redis的watch乐观锁
把请求放到redis的list队列
异步的多线程去消费队列消息
结合thrift、rpc协议，作为服务端的时间
