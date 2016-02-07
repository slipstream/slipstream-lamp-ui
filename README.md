slipstream-lamp-ui
==================

Simple UI build for the LAMP++ example, written in ClojureScript

Release
-------

To create a new release, do the following:

 * Compile the sources: `lein cljsbuild once`
 * Create tarball: `tar zcf lamp-ui-1.1.tgz index.html style.css lamp.js out/`
 * Tag a new release with git.
 * Create a new release in [GitHub](https://github.com/slipstream/slipstream-lamp-ui/releases).
 * Update LAMP definition on [nuv.la](https://nuv.la).
 