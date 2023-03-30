package com.example.rma_spirala

fun getGames(): List<Game> {
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
                UserReview("ninja123",21232,"i like it it's good"),
                UserRating("ninja123", 12342, 9.5)
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
            "The plot of Deus Ex depicts a society on a slow spiral into chaos. There is a massive division between the rich and the poor, not only socially, but in some cities physically. A lethal pandemic, known as the \"Gray Death\", ravages the world's population, especially within the United States, and has no cure. A synthetic vaccine, \"Ambrosia\", manufactured by the company VersaLife, nullifies the effects of the virus but is in critically short supply.",
            listOf(
                UserReview("ninja123",21232,"i like it it's good"),
                UserRating("ninja123", 12342, 9.5)
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
            "The Forgotten Realms, the high fantasy campaign setting in which Baldur's Gate II is set, is a fictional world similar to a medieval Earth, but with its own peoples, geography, and history. Baldur's Gate II is set in the year 1369 DR (Dale Reckoning),[44] and thus takes place not long after the Time of Troubles (1358 DR), when the Tablets of Fate, powerful magic items which maintain a balance between good and evil, were stolen.",
            listOf(
                UserReview("ninja123",21232,"i like it it's good"),
                UserRating("ninja123", 12342, 9.5)
            )
        ),
        Game(
            "Heroes of Might & Magic 3",
            "Windows/Macintosh/Linux",
            "03/03/1999",
            7.8,
            "hmm3",
            "Everyone",
            "New World Computing",
            "3DO",
            "Turn-based Strategy",
            "Following the disappearance of King Roland Ironfist of Enroth, his wife, Queen Catherine, is left to rule the realm. In the meantime, her father, King Gryphonheart of Erathia, is assassinated. Without their beloved King, the kingdom of Erathia falls to the dark forces of Nighon and Eeofol. Queen Catherine returns home to Antagarich seeking to rally the people of her homeland and lead them against the evil that has ravaged their nation.",
            listOf(
                UserReview("ninja123",21232,"i like it it's good"),
                UserRating("ninja123", 12342, 9.5)
            )
        ),
        Game(
            "Fallout 2",
            "Windows/macOS",
            "29/10/1998",
            8.1,
            "fallout2",
            "Mature",
            "Black Isle Studios",
            "Interplay Productions",
            "RPG",
            "In 2241, the primitive town of Arroyo suffers the worst drought on record. The player, assuming the role of the Chosen One, is given nothing more than the Vault Dweller's jumpsuit, a RobCo PIPBoy 2000, a Vault 13 water flask, a spear and some cash to start on their mission.",
            listOf(
                UserReview("ninja123",21232,"i like it it's good"),
                UserRating("ninja123", 12342, 9.5)
            )
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
            "Factorio is a construction and management simulation game focused on resource-gathering with real-time strategy and survival elements and influences from the BuildCraft and IndustrialCraft mods for the video game Minecraft. The player survives by locating and harvesting resources to craft various tools and machines, which in turn create more advanced materials that allow for the progression to more sophisticated technologies and machines.",
            listOf(
                UserReview("ninja123",21232,"i like it it's good"),
                UserRating("ninja123", 12342, 9.5)
            )
        ),
        Game(
            "The Elder Scrolls III: Morrowind",
            "PC/Xbox",
            "01/05/2002",
            9.8,
            "morrowind",
            "Teen",
            "Bethesda Studios",
            "Bethesda Studios",
            "Action RPG",
            "Morrowind's central plot revolves around The Tribunal, a triumvirate of god-like beings ruling over Morrowind, and their struggle against a former ally, the demigod Dagoth Ur and his Sixth House – a cult of followers stretching out from Red Mountain, the island on which the game is set. Dagoth Ur has used the Heart of Lorkhan, an artifact of great power, to make himself immortal and now seeks to drive the Imperial Legion occupiers from Morrowind.",
            listOf(
                UserReview("ninja123",21232,"i like it it's good"),
                UserRating("ninja123", 12342, 9.5)
            )
        ),

        Game(
            "Bloodborne",
            "PC",
            "24/03/2014",
            7.4,
            "bloodborne",
            "Mature",
            "FromSoftware",
            "Sony Interactive Entertainment",
            "Action RPG",
            "Bloodborne takes place in Yharnam, a decrepit Gothic city known for its medical advances around the practice of blood ministration. The player's character journeys to Yharnam seeking the cure, something known as Paleblood, for an unspecified illness. Upon arriving in the city, it is discovered that Yharnam is plagued with an illness that has transformed most of its citizens into bestial creatures.",
            listOf(
                UserReview("ninja123",21232,"i like it it's good"),
                UserRating("ninja123", 12342, 9.5)
            )
        )
    )
}