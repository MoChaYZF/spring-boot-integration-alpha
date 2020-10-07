# Spring Boot 工具集成箱项目说明
`JDK版本：JDK8，Spring Boot 版本：2.2.6`
## 1. 实现Redis分布式限流功能
## 2. 实现LocalDateTime全局序列化与反序列化功能
## 3. 实现MyBatis-Plus性能分析插件集成
- (1) 利用@Profile注解
## 4. 实现MyBatis-Plus分页插件集成
- (1) 实现分页封装功能
## 5. 实现p6spy第三方sql执行分析集成
- (1) 执行sql日志输出到日志文件中
- (2) 自定义sql日志输出格式
## 6. 使用LambdaQueryWrapper条件构造器代替“普通条件构造器”
## 7. 集成Druid
- (1) 解决Druid不兼容LocalDateTime类型问题
## 8. 集成EasyExcel导出报表功能
## 9. 集成简单的Web相关工具方法（WebUtils）
- (1) 获取ip地址
- (2) 获取请求上下文
- (3) 获取请求uri
- (4) 获取响应对象
## 10. 集成Swagger文档
## 11. 集成LogBack日志文件输出
- (1) 异步输入提升性能
## 12. 集成个性化自定义Banner
- (1) 方便分布式系统各模块引用
## 13. 实现对数据参数的校验
- (1) 普通参数校验 开启@Validated
- (2) 对象参数校验 使用@Valid
## 14. 集成两种方案获取IP详细城市地址
- (1) 通过调用第三方接口，需要注意的是当使用Nginx作为反向代理时，不能直接通过request.getRemoteAddr()来获取真实请求地址
需要在Nginx配置文件中配置proxy_set_header X-Forwarded-For $remote_addr;
- (2) 利用开源库ip2region实现ip详细地址的获取
## 15. 添加IPV4与long类型互转工具类
## 16. 添加UserAgentUtils工具
## 17. 解决请求url携带了特殊符号的参数，导致出现状态码400的错误

## 版本说明

| 组件           | 使用版本      |
| -------------- | ------------- |
| Spring Boot    | 2.2.6.RELEASE |
| MyBatis Plus   | 3.3.1         |
| druid          | 1.1.21        |
| fastjson       | 1.2.73        |
| jedis          | 3.2.0         |
| aliyun-sdk-oss | 3.10.2        |

