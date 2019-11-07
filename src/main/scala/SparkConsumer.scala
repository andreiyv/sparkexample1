import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{explode, split}

object SparkConsumer {

    def main(args:Array[String]): Unit = {

      val spark: SparkSession = SparkSession.builder().master("local[1]")
        .appName("SparkByExamples.com")
        .getOrCreate()

      val df = spark
        .readStream
        .format("kafka")
        .option("kafka.bootstrap.servers", "lepus:9092")
        .option("subscribe", "test_topic")
        .load()

      val consoleOutput1 = df.writeStream
        .outputMode("update")
        .format("console")
        .start().awaitTermination()

    }

}
