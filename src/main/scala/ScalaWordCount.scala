import java.io._
object ScalaWordCount {
	def main(args: Array[String]) = {
		val wordsCounted = scala.io.Source.fromFile(args(0))
						   .getLines.map(line => line.toLowerCase)
						   .flatMap(line => line.split("""\W+""")).toSeq
						   .groupBy(word => word)
						   .map{ case (word, group) => (word, group.size)}

	    val out = new PrintStream(new File(args(1)))

	    wordsCounted foreach(word_count => out.println(word_count))					
	}
}