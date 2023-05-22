plugins {
    alias(libs.plugins.node)
}

node {
    download.set(true)
    version.set("18.16.0")
    npmVersion.set("9.6.5")
}
