object FileMatcher {
    private def filesHere = (new java.io.File(".")).listFiles
    private def filesMatching(matcher: String => Boolean): Array[java.io.File] = 
        for (file <- filesHere; if matcher(file.getName))
            yield file

    def filesEnding(query: String) =
        filesMatching(_.endsWith(query))

    def filesContaining(query: String) =
        filesMatching(_.contains(query))

    def filesRegex(query: String) =
        filesMatching(_.matches(query))

    def main(args: Array[String]) {
        def outputFileArray(files: Array[java.io.File]) {
            println(files.map(_.getName).mkString(";"))
        }

        outputFileArray(filesEnding("scala"))
        outputFileArray(filesContaining("sc"))
        outputFileArray(filesRegex(".*scala.*~"))
    }
}
