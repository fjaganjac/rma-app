/*
package ba.etf.rma23.projekat

class GameData {
    companion object {
        fun getAll(): List<Game> {
            return listOf(
                Game(
                    "Final Fantasy VII",
                    "Playstation/Windows/Nintendo Switch",
                    "31/01/1997",
                    9.7,
                    "ff7",
                    "Teen",
                    "Square",
                    "Square Enix",
                    "Japanese RPG",
                    "The game's story follows Cloud Strife, a mercenary who joins an eco-terrorist organization to stop a world-controlling megacorporation from using the planet's life essence as an energy source.",
                    listOf(
                        UserReview("User1", 21232, "i like it it's good"),
                        UserRating("User1", 12342, 5.5),
                        UserReview("User2", 21232, "i don't like it it's very bad >:("),
                        UserRating("User2", 12342, 0.5),
                        UserRating("User3", 321123, 7.6)
                    )
                ),
                Game(
                    "Deus Ex",
                    "Windows/Mac OS/Playstation 2",
                    "23/06/2000",
                    8.2,
                    "deusex",
                    "Mature",
                    "Ion Storm",
                    "Eidos Interactive",
                    "Action RPG/Immersive Sim",
                    "The year is 2052 and the world is a dangerous and chaotic place. Terrorists operate openly - killing thousands; drugs, disease and pollution kill even more. The world's economies are close to collapse and the gap between the insanely wealthy and the desperately poor grows ever wider.",
                    listOf(
                        UserReview("User4", 21232, "this game predicted the future :o"),
                        UserReview("User5", 12342, "runs poorly on mac unfortunately"),
                        UserRating("User5", 12342, 6.4),
                        UserReview("User6", 12342, "great game!"),
                        UserRating("User7", 12342, 9.5),

                        )

                ),
                Game(
                    "Baldur's Gate 2",
                    "Windows/Mac OS",
                    "29/09/2000",
                    9.4,
                    "baldursgate",
                    "Teen",
                    "BioWare",
                    "Black Isle Studios",
                    "RPG",
                    "Rediscover the beloved RPG classic— now enhanced for modern adventurers! Gather your party of heroes and continue the legendary adventure in this story-rich fantasy epic where every choice matters.",
                    listOf()
                ),
                Game(
                    "Heroes of Might & Magic 3",
                    "Windows/Macintosh/Linux",
                    "03/03/1999",
                    8.8,
                    "hmm3",
                    "Everyone",
                    "New World Computing",
                    "3DO",
                    "Turn-based Strategy",
                    "Rediscover the epic tale of Queen Catherine Ironfist, as she re-embarks on her critically acclaimed quest to unite her ravaged homeland and re-conquer the kingdom of Erathia.",
                    listOf(
                    )
                ),
                Game(
                    "Fallout 2",
                    "Windows/macOS",
                    "29/10/1998",
                    7.3,
                    "fallout2",
                    "Mature",
                    "Black Isle Studios",
                    "Interplay Productions",
                    "RPG",
                    "Fallout® 2 is the sequel to the critically acclaimed game that took RPG'ing out of the dungeons and into a dynamic, apocalyptic retro-future. It's been 80 long years since your ancestors trod across the wastelands.",
                    listOf()
                ),
                Game(
                    "Factorio",
                    "Windows/Linux/macOS",
                    "25/01/2016",
                    8.2,
                    "factorio",
                    "Teen",
                    "Wube Software",
                    "Wube Software",
                    "Factory simulation",
                    "Factorio is a game about building and creating automated factories to produce items of increasing complexity, within an infinite 2D world. Use your imagination to design your factory, combine simple elements into ingenious structures, and finally protect it from the creatures who don't really like you.",
                    listOf()
                ),
                Game(
                    "The Elder Scrolls III: Morrowind",
                    "PC/Xbox",
                    "01/05/2002",
                    6.8,
                    "morrowind",
                    "Teen",
                    "Bethesda Studios",
                    "Bethesda Studios",
                    "Action RPG",
                    "Morrowind's central plot revolves around The Tribunal, a triumvirate of god-like beings ruling over Morrowind, and their struggle against a former ally, the demigod Dagoth Ur and his Sixth House – a cult of followers stretching out from Red Mountain, the island on which the game is set.",
                    listOf()
                ),

                Game(
                    "Dragon's Dogma",
                    "Playstation 3/Xbox 360/Windows",
                    "22/03/2012",
                    9.9,
                    "dragonsdogma",
                    "Mature",
                    "Capcom",
                    "Capcom",
                    "Action RPG",
                    "Set in a huge open world, Dragon’s Dogma: Dark Arisen presents a rewarding action combat experience.",
                    listOf()
                ),
                Game(
                    "Pathfinder: Kingmaker",
                    "Windows/Linux/macOS/Playstation 4",
                    "25/09/2018",
                    7.4,
                    "pkingmaker",
                    "Teen",
                    "Owlcat Games",
                    "Deep Silver",
                    "RPG",
                    "Pathfinder: Kingmaker is the first isometric party-based computer RPG set in the Pathfinder fantasy universe. Enjoy a classic RPG experience inspired by games like Baldur's Gate, Fallout 1 and 2 and Arcanum. Explore and conquer the Stolen Lands and make them your kingdom!",
                    listOf()
                ),
                Game(
                    "Mount & Blade",
                    "Windows/Linux/macOS",
                    "24/03/2014",
                    8.9,
                    "mnb",
                    "Mature",
                    "TaleWorlds Entertainment",
                    "Paradox Interactive",
                    "Action RPG/Real-time Strategy",
                    "Calradia is a land at war, offering great riches and even greater dangers to adventurers and mercenaries that flock to shed their blood on its soil. With courage and a strong sword, an unknown stranger can make a name as a warrior. Free-form sand-box gameplay.",
                    listOf()
                )

            )
        }

        fun getDetails(title: String): Game {
            val games = getAll()
            val game = games.find { game -> title == game.title }
            return game ?: Game(
                "Test",
                "Test",
                "Test",
                0.0,
                "Test",
                "Test",
                "Test",
                "Test",
                "Test",
                "Test",
                listOf(UserReview("Test", 0, "Test"))
            )
        }
    }
}*/
