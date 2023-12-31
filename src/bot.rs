use serenity::all::{Context, EventHandler, Message};
use serenity::async_trait;

pub struct DubsBot;

#[async_trait]
impl EventHandler for DubsBot {
    async fn message(&self, context: Context, message: Message) {
        message.react(&context.http, '🎅').await;
    }
}