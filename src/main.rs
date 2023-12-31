mod bot;

use std::env;
use serenity::all::GatewayIntents;
use serenity::Client;
use crate::bot::DubsBot;

#[tokio::main]
async fn main() {
    let token = env::var("DISCORD_TOKEN").expect("The DISCORD_TOKEN env variable is not configured!");
    let intents = GatewayIntents::GUILDS
        | GatewayIntents::GUILD_MESSAGES
        | GatewayIntents::GUILD_MESSAGE_REACTIONS;

    let mut client = Client::builder(&token, intents)
        .event_handler(DubsBot)
        .await
        .expect("");

    if let Err(reason) = client.start().await {
        println!("Error starting the bot: {}", reason)
    }
}
