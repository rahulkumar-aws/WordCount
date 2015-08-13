import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
object SparkWordCount {

	def main(args: Array[String]): Unit = {
	  
	  val sc = new SparkContext("local","SparkWordCount")

	  val wordsCounted = sc.textFile(args(0)).map(line=> line.toLowerCase)
	  										 .flatMap(line => line.split("""\W+"""))
	  										 .groupBy(word => word)
	  										 .map{ case(word, group) => (word, group.size)}

	  wordsCounted.saveAsTextFile(args(1))
	  sc.stop()						
	}
}