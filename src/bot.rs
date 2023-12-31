use serenity::all::{Context, EventHandler, Message};
use serenity::async_trait;
use crate::patterns::match_digit_patterns;

pub struct DubsBot;

#[async_trait]
impl EventHandler for DubsBot {
    async fn message(&self, context: Context, message: Message) {
        let id = message.id.get();
        let matched_patterns = match_digit_patterns(&id);

        if !matched_patterns.is_empty() {
            message.react(&context.http, '🍀').await.expect("Error reacting to message.");

            for matched_pattern in matched_patterns {
                message.react(&context.http, matched_pattern.emoji).await.expect("Error reacting to message.");
            }
        }
    }
}