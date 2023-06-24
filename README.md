## Movie recommendation algorithm
Final practice of the subject Data Structures that implements a movie recommendation algorithm using different structures like graphs, hashmaps and binary trees.

#### Motivation
This project uses the dataset provided from the Netflix's competition https://www.kaggle.com/datasets/netflix-inc/netflix-prize-data.

#### Project structure
- `src/exceptions`: exceptions raised in some cases by the program.
- `src/io`: methods implemented to load the information contained on the movies' dataset.
- `src/models`: models used for training and prediction of movie recommendations to a determined user.

- `movie_titles.txt`: contains the information about nearly 18000 movies. Is in the following format:

    MovieID,YearOfRelease,Title

- MovieID do not correspond to actual Netflix movie ids or IMDB movie ids.
- YearOfRelease can range from 1890 to 2005 and may correspond to the release of
corresponding DVD, not necessarily its theaterical release.
- Title is the Netflix movie title and may not correspond to
titles used on other sites. Titles are in English.

- `training_set/movie_users_20_50.txt`:  contains the information about diferent customers ratings and dates. Is in the following format:

    CustomerID,Rating,Date

- MovieIDs range from 1 to 17770 sequentially.
- CustomerIDs range from 1 to 2649429, with gaps. There are 480189 users.
- Ratings are on a five star (integral) scale from 1 to 5.
- Dates have the format YYYY-MM-DD.

#### How it works?
- Type "id" from any user/customer on movie_users_20_50.txt

- Then, the program will load the information using diferent data structures and then categorize users by their similarity to the     user we want to recommend through different parameters. 
- Finally the program will recommend between 0 and 3 movies to the user indicated.  

