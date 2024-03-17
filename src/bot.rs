use serenity::all::{Context, CreateEmbed, CreateMessage, EventHandler, Message};
use serenity::all::ReactionType::Unicode;
use serenity::async_trait;
use crate::patterns::match_digit_patterns;

pub struct DubsBot;

#[async_trait]
impl EventHandler for DubsBot {
    async fn message(&self, context: Context, message: Message) {
        let id = message.id.get();
        let matched_patterns = match_digit_patterns(&id);

        if matched_patterns.iter().any(|pattern| pattern.is_rare) {
            let embed = CreateEmbed::new()
                .title("Whoa! those are some pog digits!")
                .description(format!("`{}`", id.to_string()))
                .image("https://i.imgur.com/a31ZwOV.gif");

            let builder = CreateMessage::new()
                .embed(embed)
                .reference_message(&message);

            if let Err(reason) = message.channel_id.send_message(&context.http, builder).await {
                println!("Error responding with rare pattern message embed: {}", reason);
            }
        }

        if !matched_patterns.is_empty() {
            if let Err(reason) = message.react(&context.http, '🌸').await {
                println!("Error adding the default reaction: {}", reason);
                return;
            }

            for matched_pattern in matched_patterns {
                let reaction = Unicode(matched_pattern.emoji.to_string());

                if let Err(reason) = message.react(&context.http, reaction).await {
                    println!("Error adding the {} reaction: {}", matched_pattern.emoji, reason);
                    continue;
                }
            }
        }
    }
}