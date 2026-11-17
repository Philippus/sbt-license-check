import sbtcompat.PluginCompat._

version := "0.1"
scalaVersion := "2.12.21"

licenseCheckFailBuildOnDisallowedLicense := false
licenseCheckDisallowedLicenses           := Seq("The BSD License", "The MIT License")
licenseCheckExemptedDependencies         := Seq(
  ("jline", "2.14.6"),
  ("jsoup", "1.17.2")
)

TaskKey[Unit]("check")                   := Def.uncached {
  val lastLog: File = BuiltinCommands.lastLogFile(state.value).get
  val last: String  = IO.read(lastLog)
  if (last.contains("[warn]"))
    sys.error("expected no warning logging in the log")
}
