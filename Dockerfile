FROM clojure:lein-2.7.1-alpine

COPY . /usr/src/app
WORKDIR /usr/src/app
RUN lein deps && lein exec -ep "(use 'solo-wiki.models) (create-db)"

EXPOSE 3000
CMD ["lein", "ring", "server-headless"]
