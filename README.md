# one-man-wiki

A tiny wiki implementation designed to scale down to a single user.

Target feature set:

* Editing
* Automatic linkification of http:// strings
* Automatic internal links using WikiWords
* XSS protection
* CSRF protection
* Poor man's WYSIWYG using monospaced fonts (preferably an attractive one)

Features deliberately missing:

* User accounts
* Authentication (use an intranet or HTTP auth)
* Additional wiki syntax

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

Copyright © 2012 Wilfred Hughes
