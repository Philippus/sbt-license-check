version := "0.1"

TaskKey[Unit]("check") := {
  val lastLog: File = BuiltinCommands.lastLogFile(state.value).get
  val last: String  = IO.read(lastLog)
  if (!last.contains("| +-"))
    sys.error("expected a report to be generated in the log")
}
