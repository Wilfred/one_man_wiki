# one-man-wiki

A tiny wiki implementation designed to scale down to a single user.

Features:

* Editing
* Automatic linkification of http:// strings
* Automatic internal links using WikiWords
* XSS protection
* CSRF protection
* Identical fonts in editor to give poor man's WYSIWYG
* Monospaced font so code snippets can be included verbatim
* Old revisions saved

Additional planned features:

* Viewing of old revisions
* Links to nonexistent pages highlighted

Features deliberately missing:

* User accounts
* Authentication (use an intranet or HTTP auth)
* Additional wiki syntax

Shorcuts:

* When viewing, press <Enter> to start editing.
* When editing, press <Tab><Enter> to save.

TODO: set a maximum number of lines of code, suckless.org style.

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

Create a database:

    $ lein repl
    user=> (use 'one-man-wiki.models)
    user=> (create-db)

Start the dev server:

    $ lein ring server

## License

GPL v2 or later. Copyright © 2012 Wilfred Hughes
