import sbtcompat.PluginCompat._

version := "0.1"
scalaVersion := "2.12.21"

TaskKey[Unit]("check") := Def.uncached {
  val lastLog: File = BuiltinCommands.lastLogFile(state.value).get
  val last: String  = IO.read(lastLog)
  if (!last.contains("| +-"))
    sys.error("expected a report to be generated in the log")
}
