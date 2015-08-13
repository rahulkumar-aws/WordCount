import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * Created by rahul on 11/08/15.
 */
object StreamingWordCount extends App{

  val sparkConf = new SparkConf().setMaster("local[2]").setAppName("StreamingWordCount")
  val ssc = new StreamingContext(sparkConf, Seconds(10))

  val lines = ssc.socketTextStream("localhost", 9999)

  val words = lines.flatMap(_.split(" "))

  // Count each word in each batch
  val pairs = words.map(word => (word, 1))
  val wordCounts = pairs.reduceByKey(_ + _)

  // Print the first ten elements of each RDD generated in this DStream to the console
  wordCounts.print()

  ssc.start()             // Start the computation
  ssc.awaitTermination()  // Wait for the computation to terminate

}
