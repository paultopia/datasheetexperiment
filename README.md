Just experimenting with using a JS component from NPM in [react-datasheet](https://nadbm.github.io/react-datasheet/) in a reagent project. It's about time I learned how to use and interact with js react components from npm in a cljs project.

Broadly based on [this tutorial](http://blob.tomerweller.com/reagent-import-react-components-from-npm), but with a slightly different import style, plucking the relevant CSS out of the node-modules folder for the component and sticking it directly in my public folder, and using `reagent/adapt-react-class` rather than `:>`.

Usage: 

- Build javascript with `webpack -d` (see below)
- lein figwheel

and then a datasheet will appear on the page, which will only take integers; when it's edited the edits should be stably reflected.

Issues:
- I'm not very good at webpack yet, so I'm having some js processing glitches. Right now, this will compile correctly in dev mode (with webpack -d) but not in prod mode (webpack -p).  It looks like whatever minimization webpack does is munging the files in some way that changes the names, and makes them unfindable on the clojurescript end, I'm getting "TypeError: r is not a function" with a pointer to the massive bundle in prod mode."  I also tried going down to webpack 1, but the version of uglify that webpack 1 uses choked on the es6 syntax that apparently shows up in react-datasheet or somewhere.  I'm sure someone who is better at webpack can sort this all out.  

- I don't date try to compile the cljs in advanced mode yet, god only knows what will have to happen with externs.  

- Really, the ideal would just be to get all the generated javascript and feed it all to the closure compiler.  I might try and make the bundle.js file spit out a goog.provide or something as described [here](https://github.com/clojure/clojurescript/wiki/Dependencies), but if anyone else has any bright ideas on how to make that work with inserting react into the window object, I'd love to hear it... 
