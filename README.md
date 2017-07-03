# solo-wiki [![Build Status](https://travis-ci.org/Wilfred/solo-wiki.svg?branch=master)](https://travis-ci.org/Wilfred/solo-wiki)

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

Shorcuts (when JS is enabled):

* When viewing, press <Enter> to start editing.
* When editing, press <Tab><Enter> to save.
* When editing, press <Escape> to go back to viewing (when no changes
  have been made).

TODO: set a maximum number of lines of code, suckless.org style.

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

Create a database:

    $ lein exec -ep "(use 'solo-wiki.models) (create-db)"

Start the dev server:

    $ lein ring server

## Docker

You can start a demo instance in Docker with:

    $ docker run -P --name mywiki wilfred/solo-wiki

Note that this **does not preserve data**. This is intended for demos,
and doesn't use volumes. If you kill the container, your data is gone.
