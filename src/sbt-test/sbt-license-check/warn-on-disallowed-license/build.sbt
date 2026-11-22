import sbtcompat.PluginCompat._

version := "0.1"
scalaVersion := "2.12.21"

licenseCheckFailBuildOnDisallowedLicense := false
licenseCheckDisallowedLicenses           := Seq("Apache-2.0")

TaskKey[Unit]("check") := Def.uncached {
  val lastLog: File = BuiltinCommands.lastLogFile(state.value).get
  val last: String  = IO.read(lastLog)
  if (!last.contains("warn"))
    sys.error("expected warning logging in the log")
}
