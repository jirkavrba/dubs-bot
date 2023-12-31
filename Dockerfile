# Big builder image with all the rust tooling and shit
FROM rust:1.75 as build
WORKDIR /usr/src/dubs_bot
COPY . .
RUN cargo install --path .

# Final image
FROM debian:bookworm-slim
COPY --from=build /usr/local/cargo/bin/dubs_bot /usr/local/bin/dubs_bot
CMD ["dubs_bot"]