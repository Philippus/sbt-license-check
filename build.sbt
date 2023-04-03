name := "sbt-license-check"
organization := "nl.gn0s1s"
startYear := Some(2022)
homepage := Some(url("https://github.com/philippus/sbt-license-check"))
licenses += ("MPL-2.0", url("https://www.mozilla.org/MPL/2.0/"))

developers := List(
  Developer(
    id = "philippus",
    name = "Philippus Baalman",
    email = "",
    url = url("https://github.com/philippus")
  )
)

enablePlugins(SbtPlugin)
sbtPlugin := true
pluginCrossBuild / sbtVersion := "1.3.0" // minimum version we target

versionScheme := Some("semver-spec")

Compile / packageBin / packageOptions += Package.ManifestAttributes(
  "Automatic-Module-Name" -> "nl.gn0s1s.licensecheck"
)

scalacOptions += "-deprecation"

scriptedLaunchOpts := {
  scriptedLaunchOpts.value ++ Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
}

scriptedBufferLog := false
