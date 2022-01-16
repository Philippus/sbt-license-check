package nl.gn0s1s.licenciasplugin

import sbt._
import Keys._

object LicenciasPlugin extends AutoPlugin {
  case class License(name: String, url: Option[String])
  case class Dependency(organization: String, name: String, revision: String, licenses: Seq[License])

  override def trigger = allRequirements

  object autoImport {
    val licenciasReport = taskKey[Unit]("generate license report")
  }

  import autoImport._
  override lazy val projectSettings: Seq[Setting[_]] = Seq(
    licenciasReport := {
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
                s.log.info(s"  | +-${license.name} - ${license.url.getOrElse("")}")
              }
          }
        }
      }
    }
  )
}
