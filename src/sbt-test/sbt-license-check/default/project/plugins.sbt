sys.props.get("plugin.version") match {
  case Some(x) => addSbtPlugin("nl.gn0s1s" %% "sbt-license-check" % x)
  case _       => sys.error("""|The system property 'plugin.version' is not defined.
                         |Specify this property using the scriptedLaunchOpts -D.""".stripMargin)
}

addSbtPlugin("com.github.sbt" % "sbt2-compat" % "0.2.0")
