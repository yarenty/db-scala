resolvers += Resolver.url("scalasbt", new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)

resolvers += Classpaths.typesafeResolver

resolvers += "gseitz@github" at "http://gseitz.github.com/maven/"

resolvers += "bigtoast-github" at "http://bigtoast.github.com/repo/"

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.3.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "0.6.3")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.11.2")

