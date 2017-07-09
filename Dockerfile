FROM clojure:lein-2.7.1-alpine

COPY . /usr/src/app
WORKDIR /usr/src/app
RUN lein deps && lein exec -ep "(use 'solo-wiki.models) (create-db)"

EXPOSE 3000

RUN apk update && apk add curl && rm -rf /var/cache/apk/*
HEALTHCHECK --timeout=3s \
      CMD curl -f http://localhost:3000 || exit 1

CMD ["lein", "ring", "server-headless"]
