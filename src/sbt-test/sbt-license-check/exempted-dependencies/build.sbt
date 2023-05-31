version := "0.1"

useCoursier := false

licenseCheckFailBuildOnDisallowedLicense := false
licenseCheckDisallowedLicenses := Seq("Apache-2.0")
licenseCheckExemptedDependencies := Seq(
  ("scala-library", "2.12.8"),
  ("scala-compiler", "2.12.8"),
  ("scala-reflect", "2.12.8")
)

TaskKey[Unit]("check") := {
  val lastLog: File = BuiltinCommands.lastLogFile(state.value).get
  val last: String = IO.read(lastLog)
  if (last.contains("[warn]"))
    sys.error("expected no warning logging in the log")
}
