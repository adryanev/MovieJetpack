package dev.adryanev.dicoding.moviejetpack.utils

import dev.adryanev.dicoding.moviejetpack.data.entities.MovieEntity
import kotlinx.datetime.LocalDate

object DataDummy {

    fun generateMovies(): List<MovieEntity> {

        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                id = 0,
                title = "A Star is Born",
                overview = "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                releaseDate = "5/10/2018",
                userScore = 75,
                duration = 136,
                genre = arrayOf("Drama", "Romance", "Music"),
                language = "English",
                budget = 36_000_000,
                revenue = 433_888_866,
                rating = "R",
                poster = "poster_a_start_is_born"
            )
        )
        movies.add(
            MovieEntity(
                id= 1,
                title = "Alita: Battle Angel",
                overview = "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                releaseDate = "2/14/2019",
                userScore = 72,
                duration = 122,
                genre = arrayOf("Action", "Science Fiction", "Adventure"),
                language = "English",
                budget = 170_000_000,
                revenue = 404_852_543,
                rating = "PG-13",
                poster = "poster_alita"
            )
        )
        movies.add(
            MovieEntity(
                id = 2,
                title = "Aquaman",
                overview = "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                releaseDate = "12/21/2019",
                userScore = 69,
                duration = 163,
                genre = arrayOf("Action", "Adventure", "Fantasy"),
                language = "English",
                budget = 160_000_000,
                revenue = 1_148_461_807,
                rating = "PG-13",
                poster = "poster_aquaman"
            )
        )
        movies.add(
            MovieEntity(
                id = 3,
                title = "Bohemian Rhapsody",
                overview = "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                releaseDate = "11/2/2018",
                userScore = 80,
                duration = 135,
                genre = arrayOf("Music", "Drama", "History"),
                language = "English",
                budget = 52_000_000,
                revenue = 894_027_543,
                rating = "PG-13",
                poster = "poster_bohemian"
            )
        )
        movies.add(
            MovieEntity(
                id = 4,
                title = "Cold Pursuit",
                overview = "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                releaseDate = "2/8/2019",
                userScore = 57,
                duration = 119,
                genre = arrayOf("Action", "Crime", "Thriller"),
                language = "English",
                budget = 60_000_000,
                revenue = 76_419_755,
                rating = "R",
                poster = "poster_cold_persuit"
            )
        )
        movies.add(
            MovieEntity(
                id = 5,
                title = "Creed II",
                overview = "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                releaseDate = "11/21/2018",
                userScore = 69,
                duration = 130,
                genre = arrayOf("Drama"),
                language = "English",
                budget = 50_000_000,
                revenue = 214_215_889,
                rating = "PG-13",
                poster = "poster_creed"
            )
        )
        movies.add(
            MovieEntity(
                id = 6,
                title = "Fantastic Beasts: The Crimes of Grindelwald",
                overview = "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                releaseDate = "11/16/2018",
                userScore = 69,
                duration = 134,
                genre = arrayOf("Adventure","Fantasy","Drama"),
                language = "English",
                budget = 200_000_000,
                revenue = 633_355_901,
                rating = "PG-13",
                poster = "poster_crimes"
            )
        )
        movies.add(
            MovieEntity(
                id = 7,
                title = "Glass",
                overview = "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                releaseDate = "1/18/2019",
                userScore = 67,
                duration = 129,
                genre = arrayOf("Thriller","Drama","Science Fiction"),
                language = "English",
                budget = 20_000_000,
                revenue = 246_941_965,
                rating = "PG-13",
                poster = "poster_glass"
            )
        )
        movies.add(
            MovieEntity(
                id = 8,
                title = "How to Train Your Dragon: The Hidden World",
                overview = "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                releaseDate = "2/22/2019",
                userScore = 78,
                duration = 104,
                genre = arrayOf("Animation","Family","Adventure"),
                language = "English",
                budget = 129_000_000,
                revenue = 517_526_875,
                rating = "PG",
                poster = "poster_how_to_train"
            )
        )
        movies.add(
            MovieEntity(
                id = 9,
                title = "Avengers: Infinity War",
                overview = "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                releaseDate = "4/27/2018",
                userScore = 83,
                duration = 149,
                genre = arrayOf("Adventure","Action","Science Fiction"),
                language = "English",
                budget = 300_000_000,
                revenue = 2_046_239_637,
                rating = "PG-13",
                poster = "poster_infinity_war"
            )
        )


        return movies
    }

    fun generateTvShows(): List<MovieEntity> {

        val shows = ArrayList<MovieEntity>()

        shows.add(
            MovieEntity(
                id = 0,
                title = "Arrow",
                overview = "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                releaseDate = "10/10/2018",
                userScore = 66,
                duration = 42,
                genre = arrayOf("Crime","Drama","Mystery","Action","Adventure"),
                language = "English",
                rating = "TV-14",
                poster = "poster_arrow"
            )
        )
        shows.add(
            MovieEntity(
                id = 1,
                title = "Doom Patrol",
                overview = "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                releaseDate = "2/15/2019",
                userScore = 76,
                duration = 49,
                genre = arrayOf("Sci-Fi","Fantasy","Comedy","Drama"),
                language = "English",
                rating = "TV-MA",
                poster = "poster_doom_patrol"
            )
        )
        shows.add(
            MovieEntity(
                id = 2,
                title = "Dragon Ball",
                overview = "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku\'s home. Together, they set off to find all seven and to grant her wish.",
                releaseDate = "2/26/1986",
                userScore = 81,
                duration = 25,
                genre = arrayOf("Sci-Fi","Fantasy","Animation","Action","Adventure"),
                language = "Japanese",
                rating = "TV-PG",
                poster = "poster_dragon_ball"
            )
        )
        shows.add(
            MovieEntity(
                id = 3,
                title = "Game of Thrones",
                overview = "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                releaseDate = "4/17/2017",
                userScore = 84,
                duration = 60,
                genre = arrayOf("Sci-Fi","Fantasy","Drama","Action","Adventure"),
                language = "English",
                rating = "TV-MA",
                poster = "poster_god"
            )
        )
        shows.add(
            MovieEntity(
                id = 4,
                title = "Gotham",
                overview = "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                releaseDate = "9/22/2014",
                userScore = 75,
                duration = 43,
                genre = arrayOf("Sci-Fi","Fantasy","Drama","Crime"),
                language = "English",
                rating = "TV-14",
                poster = "poster_gotham"
            )
        )
        shows.add(
            MovieEntity(
                id = 5,
                title = "Grey\'s Anatomy",
                overview = "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                releaseDate = "3/27/2005",
                userScore = 82,
                duration = 43,
                genre = arrayOf("Drama"),
                language = "English",
                rating = "TV-14",
                poster = "poster_grey_anatomy"
            )
        )
        shows.add(
            MovieEntity(
                id = 6,
                title = "Hanna",
                overview = "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                releaseDate = "3/28/2019",
                userScore = 75,
                duration = 50,
                genre = arrayOf("Action","Adventure","Drama"),
                language = "English",
                rating = "TV-MA",
                poster = "poster_hanna"
            )
        )
        shows.add(
            MovieEntity(
                id = 7,
                title = "Marvel\'s Iron Fist",
                overview = "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                releaseDate = "3/17/2017",
                userScore = 66,
                duration = 55,
                genre = arrayOf("Action","Adventure","Drama", "Sci-Fi", "Fantasy"),
                language = "English",
                rating = "TV-MA",
                poster = "poster_iron_fist"
            )
        )
        shows.add(
            MovieEntity(
                id = 8,
                title = "NCIS",
                overview = "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                releaseDate = "9/23/2003",
                userScore = 74,
                duration = 45,
                genre = arrayOf("Action","Adventure","Drama", "Crime"),
                language = "English",
                rating = "TV-14",
                poster = "poster_ncis"
            )
        )
        shows.add(
            MovieEntity(
                id = 9,
                title = "The Walking Dead",
                overview = "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                releaseDate = "10/31/2010",
                userScore = 81,
                duration = 42,
                genre = arrayOf("Action","Adventure","Drama", "Sci-Fi","Fantasy"),
                language = "English",
                rating = "TV-Ma",
                poster = "poster_the_walking_dead"
            )
        )
        return shows
    }
}