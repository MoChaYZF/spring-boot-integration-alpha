package top.yangzefeng.integration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yangzefeng.integration.carousel.domain.entity.Carousel;
import top.yangzefeng.integration.carousel.mapper.CarouselMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IntegrationAlphaApplication.class)
public class IntegrationAlphaApplicationTests {
    @Autowired
    private CarouselMapper carouselMapper;

    @Test
    public void contextLoads() {
    }

    /**
     * 测试QueryWrapper的select方法使用
     */
    @Test
    public void testSelect() {
        LambdaQueryWrapper<Carousel> carouselDTOQueryWrapper = Wrappers.lambdaQuery();
        // QueryWrapper的select方法就是用于指定要查询的数据列
        // info.getProperty().equals("createTime") 表示 实体类 字段名
        // info.getColumn().equals("modify_time") 表示 数据表 数据列
        Predicate<TableFieldInfo> selectPredicate = info -> !info.getProperty().equals("createTime") && !info.getColumn().equals("modify_time");
        carouselDTOQueryWrapper.select(Carousel.class, selectPredicate);
        List<Carousel> carouselList = carouselMapper.selectList(carouselDTOQueryWrapper);
        log.info("carousel = {}", carouselList);
    }

    /**
     * MyBatis-Plus代码生成器，注意要记得加velocity-engine-core依赖
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 1. 设置全局配置
        GlobalConfig gc = new GlobalConfig();
        // 获取当前项目路径
        String projectPath = System.getProperty("user.dir");
        // 设置生成文件的输出目录，默认为D盘
        gc.setOutputDir(projectPath + "/src/main/java");
        // 设置作者
        gc.setAuthor("MoCha");
        // 是否打开输出目录，默认为true
        gc.setOpen(false);
        // 是否开启AR模式，默认为false
        gc.setActiveRecord(false);
        // 是否覆盖已有文件，默认为false
        gc.setFileOverride(true);
        // 指定生成的主键的ID类型
        gc.setIdType(IdType.AUTO);
        //  设置生成的service接口的名字的首字母是否为I，如果注释该行代码则会生成如IEmployeeService这样的文件
        gc.setServiceName("%sService");
        gc.setBaseColumnList(true);
        gc.setBaseResultMap(true);
        // 将全局配置添加到代码生成器中
        mpg.setGlobalConfig(gc);

        // 2. 设置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        // mysql驱动6以上
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        // TODO 替换为自己的真实数据库连接信息
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/integration?characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");
        mpg.setDataSource(dataSourceConfig);

        // 3. 数据库表配置
        StrategyConfig strategyConfig = new StrategyConfig();
        // 数据库表映射到实体的命名策略，下划线转驼峰命名
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true); // 是否为lombok模型
        strategyConfig.setCapitalMode(true); // 是否大写命名
        // 如果不设置，那么生成出来的Java文件会带有Tbl前缀
        strategyConfig.setTablePrefix("tbl_"); //设置表前缀
        // 需要包含的表名，允许正则表达式(也就是指定将对应的数据表进行代码生成)
        strategyConfig.setInclude("tbl_carousel");
        mpg.setStrategy(strategyConfig);

        // 4. 包配置
        PackageConfig packageConfig = new PackageConfig();
        // 设置模块名称
        packageConfig.setModuleName("carousel");
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();

        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ，如果 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + packageConfig.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        // 设置父包名称，这样一来设置其他层的包名时就只需要设置该层名字即可
        // TODO 替换为自己的真实项目顶级包名
        packageConfig.setParent("top.yangzefeng.integration");
        packageConfig.setController("controller");
        packageConfig.setService("service");
        packageConfig.setMapper("mapper");
        packageConfig.setEntity("entity");

        cfg.setFileOutConfigList(focList);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);

        mpg.setTemplate(templateConfig);
        mpg.setCfg(cfg);
        mpg.setPackageInfo(packageConfig);
        mpg.execute();
    }
}
