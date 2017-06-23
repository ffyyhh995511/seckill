# seckill
商品抢购项目<br>
核心是锁，采用redis的watch乐观锁<br>
把请求放到redis的list队列<br>
异步的多线程去消费队列消息<br>
每个线程每次都会被watch库存和抢购记录防止超卖、保证数据安全<br>
单个redis实例支持watch和事物（多个操作的原子性）<br>
切片和集群环境下redis的java客户端没有watch方法<br>

q&a<br>
q:redis不是有个decrease(原子性)方法吗，把前面的N个请求就执行N的decrease次就可以了，后面的可以不接受
a:这个方法严重的不严谨，会出现N的请求都是同一个账号发过来的，那其他人就没机会了，导致商品不均匀
