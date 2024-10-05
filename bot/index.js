import {Client, EmbedBuilder, GatewayIntentBits} from 'discord.js';

const getEnvVariable = (variable) => {
    const value = process.env[variable];

    if (!value) {
        console.error(`The env variable ${variable} is not defined!`);
        process.exit(1);
    }

    return value;
};

const DISCORD_TOKEN = getEnvVariable("DISCORD_TOKEN");
const API_ENDPOINT = getEnvVariable("API_ENDPOINT");

const client = new Client({intents: [GatewayIntentBits.Guilds, GatewayIntentBits.GuildMessages]});

client.on('ready', () => {
    console.log(`Logged in as ${client.user.tag}!`);
});

client.on("messageCreate", (event) => {
    if (!event.inGuild()) {
        return;
    }

    const request = {
        message_id: event.id.toString(),
        user: {
            id: event.author.id.toString(),
            name: event.author.username,
            avatar_url: event.author.avatarURL()
        },
        channel: {
            id: event.channelId.toString(),
            name: event.channel.name,
        },
        guild: {
            id: event.guildId.toString(),
            name: event.guild.name,
            icon_url: event.guild.iconURL()
        }
    };

    fetch(API_ENDPOINT, {
        method: "POST",
        credentials: "omit",
        body: JSON.stringify(request),
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": "Basic " + btoa(`${API_USERNAME}:${API_PASSWORD}`)
        }
    })
        .then(response => response.json())
        .then(async response => {
            console.log({request, response});

            if (!response.matches) {
                return;
            }

            if (response.is_rare) {
                await event.reply({
                    embeds: [
                        new EmbedBuilder()
                            .setTitle("Whoa! those are some pog digits!")
                            .setDescription("`" + event.id.toString() + "`")
                            .setImage("https://i.imgur.com/a31ZwOV.gif")
                    ]
                });
            }

            await event.react("🍂");

            for (const pattern of response.patterns) {
                await event.react(pattern.emoji);
            }
        });
});

client.login(DISCORD_TOKEN);
