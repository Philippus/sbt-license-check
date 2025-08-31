name         := "sbt-license-check"
organization := "nl.gn0s1s"
startYear    := Some(2022)
homepage     := Some(url("https://github.com/philippus/sbt-license-check"))
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

scalaVersion := "2.12.20"
crossScalaVersions += "3.7.2"

pluginCrossBuild / sbtVersion := {
  scalaBinaryVersion.value match {
    case "2.12" => "1.5.8"
    case _      => "2.0.0-RC4"
  }
}

versionScheme := Some("semver-spec")

Compile / packageBin / packageOptions += Package.ManifestAttributes(
  "Automatic-Module-Name" -> "nl.gn0s1s.licensecheck"
)

scalacOptions += "-deprecation"

scriptedLaunchOpts := {
  scriptedLaunchOpts.value ++ Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
}

scriptedBufferLog := false
