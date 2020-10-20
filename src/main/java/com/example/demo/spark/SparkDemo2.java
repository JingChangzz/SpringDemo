package com.example.demo.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

/**
 * 统计包含"spark"的所有行的字符数
 */
public class SparkDemo2 {
    private static String appName = "spark.demo";
    private static String master = "local[*]";

    public static void main(String[] args) {
        JavaSparkContext sc = null;
        try {
            //初始化 JavaSparkContext
            SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
            sc = new JavaSparkContext(conf);

            //从test.txt 构建rdd，test.txt 放在项目根目录下
            JavaRDD<String> rdd = sc.textFile("/Users/Jing/Projects/spring/src/main/java/com/example/demo/spark/test.txt");

            //过滤
            rdd = rdd.filter(new Function<String, Boolean>() {
                public Boolean call(String s) throws Exception {
                    return s.contains("spark");
                }
            });

            //map && reduce
            Integer result = rdd.map(new Function<String, Integer>() {
                public Integer call(String s) throws Exception {
                    return s.length();
                }
            }).reduce(new Function2<Integer, Integer, Integer>() {
                public Integer call(Integer integer, Integer integer2) throws Exception {
                    return integer + integer2;
                }
            });

            System.out.println("执行结果：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }
}
