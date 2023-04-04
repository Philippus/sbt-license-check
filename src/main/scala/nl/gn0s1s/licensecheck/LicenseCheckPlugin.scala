package nl.gn0s1s.licensecheck

import sbt._
import Keys._

object LicenseCheckPlugin extends AutoPlugin {
  case class License(name: String, url: Option[String])
  case class Dependency(organization: String, name: String, revision: String, licenses: Seq[License])

  override def trigger = allRequirements

  object autoImport {
    lazy val licenseCheckFailBuildOnDisallowedLicense =
      settingKey[Boolean]("Sets whether disallowed licenses fail the build")
    lazy val licenseCheckDisallowedLicenses = settingKey[Seq[String]]("Sets the disallowed licenses")
    lazy val licenseCheckExemptedDependencies =
      settingKey[Seq[(String, String)]]("Sequence of dependency names and revisions whose licenses will be allowed")

    lazy val licenseCheck = taskKey[Unit]("Runs license check")
  }

  import autoImport._

  override lazy val globalSettings = Seq(
    licenseCheckFailBuildOnDisallowedLicense := false,
    licenseCheckDisallowedLicenses := Seq.empty[String],
    licenseCheckExemptedDependencies := Seq.empty[(String, String)]
  )

  override lazy val projectSettings: Seq[Setting[_]] = Seq(
    licenseCheck := {
      val licenses = sbt.Keys.updateFull.value.configurations.flatMap { configuration =>
        configuration.details.flatMap { detail =>
          detail.modules.filterNot(_.evicted).map { module =>
            Dependency(
              module.module.organization,
              module.module.name,
              module.module.revision,
              module.licenses.map(x => License(x._1, x._2))
            )
          }
        }
      }

      var throwOnDisallowedLicense = false
      val s = streams.value
      licenses.distinct.groupBy(_.organization).foreach { groupedDependencies =>
        s.log.info(groupedDependencies._1)
        groupedDependencies._2.foreach { dependency =>
          s.log.info(s"  +-${dependency.name}:${dependency.revision}")
          dependency.licenses match {
            case Nil =>
              s.log.info("  | +-no license specified")
            case licenses =>
              licenses.foreach { license =>
                if (
                  licenseCheckDisallowedLicenses.value.contains(license.name) && !licenseCheckExemptedDependencies.value
                    .contains((dependency.name, dependency.revision))
                )
                  if (licenseCheckFailBuildOnDisallowedLicense.value) {
                    throwOnDisallowedLicense = true
                    s.log.error(s"  | +-${license.name} - ${license.url.getOrElse("")}")
                  } else
                    s.log.warn(s"  | +-${license.name} - ${license.url.getOrElse("")}")
                else
                  s.log.info(s"  | +-${license.name} - ${license.url.getOrElse("")}")
              }
          }
        }
      }
      if (throwOnDisallowedLicense)
        throw DisallowedLicenseException
    }
  )
}
