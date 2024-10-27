version := "0.1"

licenseCheckFailBuildOnDisallowedLicense := false
licenseCheckDisallowedLicenses           := Seq("Apache-2.0")

TaskKey[Unit]("check") := {
  val lastLog: File = BuiltinCommands.lastLogFile(state.value).get
  val last: String  = IO.read(lastLog)
  if (!last.contains("warn"))
    sys.error("expected warning logging in the log")
}
