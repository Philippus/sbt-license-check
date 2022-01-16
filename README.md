# sbt-licencias

[![build](https://github.com/Philippus/sbt-licencias/workflows/build/badge.svg)](https://github.com/Philippus/sbt-licencias/actions/workflows/scala.yml?query=workflow%3Abuild+branch%3Amain)
![Current Version](https://img.shields.io/badge/version-0.0.1-brightgreen.svg?style=flat "0.0.1")
[![Scala Steward badge](https://img.shields.io/badge/Scala_Steward-helping-blue.svg?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAQCAMAAAARSr4IAAAAVFBMVEUAAACHjojlOy5NWlrKzcYRKjGFjIbp293YycuLa3pYY2LSqql4f3pCUFTgSjNodYRmcXUsPD/NTTbjRS+2jomhgnzNc223cGvZS0HaSD0XLjbaSjElhIr+AAAAAXRSTlMAQObYZgAAAHlJREFUCNdNyosOwyAIhWHAQS1Vt7a77/3fcxxdmv0xwmckutAR1nkm4ggbyEcg/wWmlGLDAA3oL50xi6fk5ffZ3E2E3QfZDCcCN2YtbEWZt+Drc6u6rlqv7Uk0LdKqqr5rk2UCRXOk0vmQKGfc94nOJyQjouF9H/wCc9gECEYfONoAAAAASUVORK5CYII=)](https://scala-steward.org)
[![License](https://img.shields.io/badge/License-MPL%202.0-blue.svg?style=flat "MPL 2.0")](LICENSE)

This plugin reports the licenses used in your sbt project. It is a limited version of the no-longer maintained
[sbt-licence-report](https://github.com/sbt/sbt-license-report).

Add the moment it has only one command `licenciasReport` which returns a tree of dependencies along with the licenses
found, grouped by organization. If a dependency has no licence, or it cannot be found it returns `no licence
specified`. Setting `useCoursier` to `false` before running the command yields in some cases different/better results. 

To find out the license(s) of the current project itself, the sbt command `licenses` can be used.

## Installation

sbt-licencias is published for sbt 1.3.0 and above. To start using it add the following to your `plugins.sbt`:

```
addSbtPlugin("nl.gn0s1s" % "sbt-licencias" % "0.1.0")
```

## Example usage

Below the output for the `scala-isbn`-project is shown:
```
sbt:scala-isbn> set useCoursier := false
[info] Defining useCoursier
[info] The new value will be used by csrCacheDirectory, dependencyResolution and 9 others.
[info]  Run `last` for details.
[info] Reapplying settings...
[info] set current project to scala-isbn (in build ***)
sbt:scala-isbn> licenciasReport
[info] org.scala-lang
[info]   +-scala-library:2.13.8
[info]   | +-Apache-2.0 - https://www.apache.org/licenses/LICENSE-2.0
[info]   +-scala-compiler:2.13.8
[info]   | +-Apache-2.0 - https://www.apache.org/licenses/LICENSE-2.0
[info]   +-scala-reflect:2.13.8
[info]   | +-Apache-2.0 - https://www.apache.org/licenses/LICENSE-2.0
[info] org.scalameta
[info]   +-munit-scalacheck_2.13:0.7.29
[info]   | +-Apache-2.0 - http://www.apache.org/licenses/LICENSE-2.0
[info]   +-munit_2.13:0.7.29
[info]   | +-Apache-2.0 - http://www.apache.org/licenses/LICENSE-2.0
[info]   +-junit-interface:0.7.29
[info]   | +-Apache-2.0 - http://www.apache.org/licenses/LICENSE-2.0
[info] junit
[info]   +-junit:4.13.1
[info]   | +-Eclipse Public License 1.0 - http://www.eclipse.org/legal/epl-v10.html
[info] org.jline
[info]   +-jline:3.21.0
[info]   | +-The BSD License - https://opensource.org/licenses/BSD-3-Clause
[info] org.scala-sbt
[info]   +-test-interface:1.0
[info]   | +-BSD - https://github.com/sbt/test-interface/blob/master/LICENSE
[info] net.java.dev.jna
[info]   +-jna:5.9.0
[info]   | +-LGPL-2.1-or-later - https://www.gnu.org/licenses/old-licenses/lgpl-2.1
[info]   | +-Apache-2.0 - https://www.apache.org/licenses/LICENSE-2.0.txt
[info] org.scalacheck
[info]   +-scalacheck_2.13:1.15.4
[info]   | +-BSD 3-clause - https://opensource.org/licenses/BSD-3-Clause
[info] org.scala-lang.modules
[info]   +-scala-xml_2.13:2.0.1
[info]   | +-Apache-2.0 - https://www.apache.org/licenses/LICENSE-2.0
[info] org.hamcrest
[info]   +-hamcrest-core:1.3
[info]   | +-New BSD License - http://www.opensource.org/licenses/bsd-license.php
```

## Resources
- [sbt-licence-report](https://github.com/sbt/sbt-license-report)

## License
The code is available under the [Mozilla Public License, version 2.0](LICENSE).
