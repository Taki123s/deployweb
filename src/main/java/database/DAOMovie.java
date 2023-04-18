package database;

import model.*;
import org.jdbi.v3.core.Jdbi;

import java.util.*;

public class DAOMovie {
    public DAOMovie() {

    }
    public static boolean removeChapter(String idChapter) {
        String query="delete from chapters_movie where id=:id";
        Jdbi me = JDBiConnector.me();
        return me.withHandle(handle->{
            return handle.createUpdate(query).bind("id",idChapter).execute();
        })==1;
    }
    public static ChapterMovie updateChapter(String id,String index,String opening,String name){
        System.out.println(id+" "+index+" "+opening+" "+name);
        String query="UPDATE `chapters_movie` SET `index` =:index,opening=:opening,name=:name WHERE id=:id;";
        Jdbi me = JDBiConnector.me();
        me.useHandle(handle->{
            handle.createUpdate(query).bind("index",index).bind("opening",opening).bind("name",name).bind("id",id).execute();
        });
        return findChapter(Integer.parseInt(id));
    }
    public static ChapterMovie addChapter(String idMovie,String index,String opening,String name){
        String query="INSERT INTO `chapters_movie` (`idMovie`, `index`, `opening`, `name`) VALUES (:idMovie, :index, :opening,:name);";
        Jdbi me = JDBiConnector.me();

        int idChapter= me.withHandle(handle->{
            return handle.createUpdate(query).bind("idMovie",idMovie).bind("index",index).bind("opening",opening).bind("name",name).executeAndReturnGeneratedKeys("id").mapTo(Integer.class).one();
        });
        //can cap nhat lai total episode
        return findChapter(idChapter);

    }
    public static ChapterMovie findChapter(int id){
        String query="select id,idMovie,`index`,opening,name from chapters_movie where id=:id";
        Jdbi me = JDBiConnector.me();
        return me.withHandle(handle->{
           return handle.createQuery(query).bind("id",id).mapToBean(ChapterMovie.class).one();
        });
    }
    public static boolean isValidChapter(String idMovie, String index) {
        String query="SELECT EXISTS(SELECT 1 FROM chapters_movie WHERE idMovie = :idMovie AND `index` = :index) as exist;";
        Jdbi me = JDBiConnector.me();
        return me.withHandle(handle->{
           return handle.createQuery(query).bind("idMovie",idMovie).bind("index",index).mapTo(Boolean.class).one() ;
        });
    }
    public static List<ChapterMovie> getListChapter(String idMovie){
        String query="select id,idMovie,`index`,opening,name from chapters_movie where idMovie= :idMovie order by `index`";
        Jdbi me = JDBiConnector.me();
        return me.withHandle(handle->{
           return handle.createQuery(query).bind("idMovie",idMovie).mapToBean(ChapterMovie.class).list();
        });
    }
    public static List<Movie> listMovieAdmin(int limit,int index){
        List<Movie> result = new ArrayList<>();
        String query="select id,name,currentEpisode,totalEpisode,typeId,price,createAt from movies where status=1 limit :limit offset :index";
        Jdbi me = JDBiConnector.me();
        result= me.withHandle(handle->{
           return handle.createQuery(query).bind("limit",limit).bind("index",index).mapToBean(Movie.class).list();
        });
        for(Movie m : result){
            m.setMaxPercent(getMaxPercentSale(m.getId()));
            m.setMinSamePrice(getMinSamePrice(m.getId()));
            m.setMaxUnitPrice(getMaxUnitPrice(m.getId()));
            m.setType(getTypeMovie(m.getId()));
        }
        return result;
    }
    public static List<TypeMovie> getListTypeMovie(){
        String query="select id,description from types_movie where status=1";
        Jdbi me = JDBiConnector.me();
        return me.withHandle(handle->{
           return handle.createQuery(query).mapToBean(TypeMovie.class).list();
        });
    }
    public static void updateAvatarMovie(List<String> avatars,int idMovie){
        String query="INSERT INTO `avatars_movie` (`name`,`idMovie`) values(:name,:idMovie)";
        Jdbi me = JDBiConnector.me();
        me.useHandle(handle->{
            for(String nameAvt : avatars){
                handle.createUpdate(query).bind("name",nameAvt).bind("idMovie",idMovie).execute();
            }
        });
    }
    public static int addMovie(String name,String totalEpisode,String descriptionVN,String descriptionEN,String typeID,String price,String[] idGenres){

        Jdbi me = JDBiConnector.me();
        final int[] idMovieFinal = {-1};
        me.useHandle(handle->{
            handle.begin();
            try{
                String query;
                int idMovie;
                query="INSERT INTO `movies` (`name`, `totalEpisode`, `descriptionVN`, `descriptionEN`, `typeID`, `price`) VALUES (:name,:totalEpisode,:descriptionVN,:descriptionEN,:typeID,:price);";
                 idMovie= handle.createUpdate(query).bind("name",name).bind("totalEpisode",totalEpisode).bind("descriptionVN",descriptionVN).bind("descriptionEN",descriptionEN).bind("typeID",typeID).bind("price",price).executeAndReturnGeneratedKeys("id").mapTo(Integer.class).one();

                query ="INSERT INTO `movie_genres` (`idMovie`,`idGenre`) values(:idMovie,:idGenre)";
                for(String idGenre : idGenres){
                    handle.createUpdate(query).bind("idMovie",idMovie).bind("idGenre",idGenre).execute();
                }
                 handle.commit();
                idMovieFinal[0]=idMovie;
            }catch (Exception e){
                e.printStackTrace();
                handle.rollback();
            }
        });
        return idMovieFinal[0];
    }
    public static List<Genre> listGenreHeader(){
        String query="select id,description from genres where status=1";
        Jdbi me = JDBiConnector.me();
        return me.withHandle(handle->{
            return handle.createQuery(query).mapToBean(Genre.class).list();
        });
    }
    public static List<Producer> getProducers(int idMovie){
        String query="SELECT distinct pr.id,pr.name,pr.description FROM animeweb.movies m join movie_producers p on m.id = p.idMovie join animeweb.producers pr on p.idproducer = pr.id where pr.status=1 and m.id=:idMovie;";
        Jdbi me = JDBiConnector.me();
        return me.withHandle(handle->{
           return handle.createQuery(query).bind("idMovie",idMovie).mapToBean(Producer.class).list();
        });
    }
    public static Bonus getMaxPercentSale(int idMovie){
        String query="SELECT id,percent,status\n" +
                "FROM bonus_movies\n" +
                "WHERE (idProducer IN (SELECT idProducer FROM movie_producers WHERE idMovie =:idMovie and status=1)\n" +
                "    OR idGenre IN (SELECT idGenre FROM movie_genres WHERE idMovie =:idMovie and status=1)\n" +
                "    OR idMovie =:idMovie and status=1)   \n" +
                "    AND dayBegin <= CURDATE()\n" +
                "  AND dayEnd >= CURDATE() and status=1  and percent is not null\n" +
                "order by percent DESC limit 1;";
        Jdbi me = JDBiConnector.me();
        return me.withHandle(handle -> {
            return handle.createQuery(query).bind("idMovie", idMovie).mapToBean(Bonus.class).findFirst().orElse(new Bonus());
        });
    }
    public static Bonus getMaxUnitPrice(int idMovie){
        String query="SELECT id,unitPrice,status\n" +
                "FROM bonus_movies\n" +
                "WHERE (idProducer IN (SELECT idProducer FROM movie_producers WHERE idMovie =:idMovie and status=1)\n" +
                "    OR idGenre IN (SELECT idGenre FROM movie_genres WHERE idMovie =:idMovie and status=1)\n" +
                "    OR idMovie =:idMovie and status=1)   \n" +
                "    AND dayBegin <= CURDATE()\n" +
                "  AND dayEnd >= CURDATE() and status=1  and unitPrice is not null\n" +
                "order by unitPrice DESC limit 1;";
        Jdbi me = JDBiConnector.me();
        return me.withHandle(handle->{
            return handle.createQuery(query).bind("idMovie",idMovie).mapToBean(Bonus.class).findFirst().orElse(new Bonus());
        });
    }
    public static Bonus getMinSamePrice(int idMovie){
        String query="SELECT id,samePrice,status\n" +
                "FROM bonus_movies\n" +
                "WHERE (idProducer IN (SELECT idProducer FROM movie_producers WHERE idMovie =:idMovie and status=1)\n" +
                "    OR idGenre IN (SELECT idGenre FROM movie_genres WHERE idMovie =:idMovie and status=1)\n" +
                "    OR idMovie =:idMovie and status=1)   \n" +
                "    AND dayBegin <= CURDATE()\n" +
                "  AND dayEnd >= CURDATE() and status=1  and samePrice is not null\n" +
                "order by samePrice ASC limit 1;";
        Jdbi me = JDBiConnector.me();
        return me.withHandle(handle->{
            return handle.createQuery(query).bind("idMovie",idMovie).mapToBean(Bonus.class).findFirst().orElse(new Bonus());
        });
    }
    public static int getViewsMovie(int idMovie){
        String query="select count(*) from animeweb.user_views where idMovie =:idMovie;";
        Jdbi me = JDBiConnector.me();
        return me.withHandle(handle->{
           return handle.createQuery(query).bind("idMovie",idMovie).mapTo(Integer.class).findOne().orElse(0);
        });
    }
    public static Movie watchingMovie(String idMovie) {
        String query="select id,name from movies where id=:idMovie and status=1;";
        Jdbi me = JDBiConnector.me();
        Movie result;
        result = me.withHandle(handle -> {
            return handle.createQuery(query).bind("idMovie",idMovie).mapToBean(Movie.class).one();
        });
        result.setListChapter(getListChapter(idMovie));
        return result;

    }
    public static Movie getMoviebyId(int idMovie){
        String query="select id,name,currentEpisode,totalEpisode,descriptionVN,descriptionEN,price from movies where id=:idMovie and status=1;";
        Jdbi me = JDBiConnector.me();
        Movie result;
        result = me.withHandle(handle->{
           return handle.createQuery(query).bind("idMovie",idMovie).mapToBean(Movie.class).findFirst().orElse(new Movie());
        });

        result.setAvatars(getAvatarMovie(result.getId()));
        result.setGenres(getListGenre(result.getId()));
        result.setType(getTypeMovie(result.getId()));
        result.setViews(getViewsMovie(result.getId()));
        result.setMaxPercent(getMaxPercentSale(result.getId()));
        result.setMinSamePrice(getMinSamePrice(result.getId()));
        result.setMaxUnitPrice(getMaxUnitPrice(result.getId()));
        result.setListProducer(getProducers(result.getId()));
        return result;
    }

    public static List<Genre> getListGenre(int idMovie){
        String query="select distinct g.id,g.description from animeweb.movie_genres mvg join animeweb.genres g on mvg.idGenre = g.id and mvg.idMovie =:idMovie and g.status=1;";
        Jdbi me = JDBiConnector.me();
        return me.withHandle(handle->{
            return handle.createQuery(query).bind("idMovie",idMovie).mapToBean(Genre.class).list();
        });
    }
    public static List<Movie> getPurchasedMovie(int idUser){
        String query="SELECT m.id,m.name,totalEpisode,m.currentEpisode,pc.purchaseAt,pc.purchasePrice FROM animeweb.movies_purchased pc join animeweb.movies m on pc.idMovie= m.id where idAccount=:idUser and m.status=1;";
        Jdbi me = JDBiConnector.me();
        List<Movie>result = me.withHandle(handle -> {
            return handle.createQuery(query).bind("idUser",idUser).mapToBean(Movie.class).list();
        });
        for(Movie m : result){
            m.setAvatars(getAvatarMovie(m.getId()));
            m.setGenres(getListGenre(m.getId()));
            m.setType(getTypeMovie(m.getId()));
            m.setViews(getViewsMovie(m.getId()));
        }
        return result;
    }
   public static int totalMovie(){
        String query="select count(*) from animeweb.movies;";
        Jdbi me = JDBiConnector.me();
        return me.withHandle(handle->{
           return handle.createQuery(query).mapTo(Integer.class).one();
        });
   }
    public static List<Movie> renderMovie(int index,int totalItem,String param){
        List<Movie> result;
        Map<String,String> nameOrder = new HashMap<>();
        nameOrder.put("isAtoZ","name");
        nameOrder.put("notAtoZ","name");
        nameOrder.put("isDescPrice","price");
        nameOrder.put("notDescPrice","price");
        nameOrder.put("isDescDate","createAt");
        nameOrder.put("notDescDate","createAt");
        Map<String,String> sort = new HashMap<>();
        sort.put("isAtoZ","asc");
        sort.put("notAtoZ","desc");
        sort.put("isDescPrice","desc");
        sort.put("notDescPrice","asc");
        sort.put("isDescDate","asc");
        sort.put("notDescDate","desc");
        String order =  "order by " + nameOrder.get(param)+" "+sort.get(param);
        String query="select id,name,currentEpisode,totalEpisode,descriptionVN,descriptionEN,typeID,price,status\n" +
                "from animeweb.movies\n" +
                "where status=1\n" +
                order+
        " limit :totalItem offset :index\n" +
                ";";

        Jdbi me = JDBiConnector.me();
        result = me.withHandle(handle->{
           return handle.createQuery(query).bind("totalItem",totalItem).bind("index",index).mapToBean(Movie.class).list();
        });
        for(Movie m : result){
            m.setAvatars(getAvatarMovie(m.getId()));
            m.setGenres(getListGenre(m.getId()));
            m.setType(getTypeMovie(m.getId()));
            m.setViews(getViewsMovie(m.getId()));
            m.setMaxPercent(getMaxPercentSale(m.getId()));
            m.setMinSamePrice(getMinSamePrice(m.getId()));
            m.setMaxUnitPrice(getMaxUnitPrice(m.getId()));

        }
        return result;
    }
    public static List<Movie> getMoviesFollow(int idUser){
        String query ="SELECT m.id,m.name,totalEpisode,m.currentEpisode,m.price,f.followAt FROM animeweb.follows f join animeweb.movies m on f.idMovie= m.id where idAccount=:idUser and m.status=1";
        Jdbi me = JDBiConnector.me();

        List<Movie> result = me.withHandle(handle ->{
            return handle.createQuery(query).bind("idUser",idUser).mapToBean(Movie.class).list();
        });
        for(Movie m : result){
            m.setAvatars(getAvatarMovie(m.getId()));
            m.setGenres(getListGenre(m.getId()));
            m.setType(getTypeMovie(m.getId()));
            m.setViews(getViewsMovie(m.getId()));
            m.setMaxPercent(getMaxPercentSale(m.getId()));
            m.setMinSamePrice(getMinSamePrice(m.getId()));
            m.setMaxUnitPrice(getMaxUnitPrice(m.getId()));

        }
        return result;
    }

    public static List<AvartarMovie> getAvatarMovie(int idMovie){
        String query ="SELECT name FROM animeweb.avatars_movie where idMovie=:idMovie;";
        Jdbi me = JDBiConnector.me();
        List<AvartarMovie> result = me.withHandle(handle->{
            return handle.createQuery(query).bind("idMovie",idMovie).mapToBean(AvartarMovie.class).list();
        });
        return result;
    }
    public static TypeMovie getTypeMovie(int idMovie){
        String query="select t.id,t.description from animeweb.movies m join animeweb.types_movie t on m.typeID = t.id where m.id=:idMovie;";
        Jdbi me = JDBiConnector.me();
        return me.withHandle(handle->{
           return handle.createQuery(query).bind("idMovie",idMovie).mapToBean(TypeMovie.class).one();
        });
    }
    public static int getDetailMoviePurchased(int idAccount,int idMovie){
        int check;
        String query = "select  idMovie FROM animeweb.movies_purchased where idMovie=:idMovie and idAccount=:idAccount;";
        Jdbi me = JDBiConnector.me();
        return me.withHandle(handle->{
            return handle.createQuery(query).bind("idMovie",idMovie).bind("idAccount",idAccount).mapTo(Integer.class).findOne().orElse(0);
        });

    }

    public void insertPurchasedMovie(int idUser, int idMovie, double price) {
        String query = "INSERT INTO movies_purchased (idAccount, idMovie, purchasePrice) VALUES (:idUser, :idMovie, :priceVar)";
        Jdbi me = JDBiConnector.me();
        me.withHandle(handle -> {
            return handle.createUpdate(query)
                    .bind("idUser", idUser)
                    .bind("idMovie", idMovie)
                    .bind("priceVar", price)
                    .execute();
        });
    }

    public void updateBalance(int idUser, double balance) {
        String query = "UPDATE accounts SET balance = :balance WHERE id = :idUser";
        Jdbi me = JDBiConnector.me();
        me.withHandle(handle -> {
            return handle.createUpdate(query)
                    .bind("balance", balance)
                    .bind("idUser", idUser)
                    .execute();
        });
    }

    public List<AvartarMovie> getFirstAvt(int idMovie) {
        Jdbi me = JDBiConnector.me();
        String query = "SELECT name,id FROM animeweb.avatars_movie where idMovie=:idMovie LIMIT 1";
        return me.withHandle(handle -> {
            return handle.createQuery(query).bind("idMovie", idMovie).mapToBean(AvartarMovie.class).list();
        });
    }

    public List<Movie> searchMovie(String name) {
        Jdbi me = JDBiConnector.me();
        String query = "SELECT mv.id, mv.name, COUNT(*) AS view_count FROM animeweb.movies mv left JOIN animeweb.user_views uv ON mv.id = uv.idMovie  where mv.name LIKE :name GROUP BY mv.id, mv.name ORDER BY view_count DESC LIMIT 10";

        List<Movie> res = me.withHandle(handle -> {
            return handle.createQuery(query).bind("name", "%" + name + "%")
                    .mapToBean(Movie.class)
                    .list();
        });
        for (Movie mv : res) {
            mv.setAvatars(getFirstAvt(mv.getId()));
        }
        return res;

    }

    public static int  getFollow(int idAccount, int idMovie) {
        int check;
        String query = "select  idMovie FROM animeweb.follows where idMovie=:idMovie and idAccount=:idAccount;";
        Jdbi me = JDBiConnector.me();
        return me.withHandle(handle -> {
            return handle.createQuery(query).bind("idMovie", idMovie).bind("idAccount", idAccount).mapTo(Integer.class).findOne().orElse(0);
        });
    }

    public static void addFollow(int idAccount, int idMovie) {
        Jdbi me = JDBiConnector.me();
        me.useHandle(handle -> {
            handle.begin();
            try {
                String query = "insert into animeweb.follows(idAccount,idMovie) values(:idAccount,:idMovie)";
                handle.createUpdate(query).bind("idMovie", idMovie).bind("idAccount", idAccount).execute();
                handle.commit();
            } catch (Exception e) {
                e.printStackTrace();
                handle.rollback();

            }
        });

    }
    public static void removeFollow(int idAccount, int idMovie) {
        Jdbi me = JDBiConnector.me();
        me.useHandle(handle -> {
            handle.begin();
            try {
                String query = "delete  from animeweb.follows where idMovie=:idMovie and idAccount =:idAccount";
                handle.createUpdate(query).bind("idMovie", idMovie).bind("idAccount", idAccount).execute();
                handle.commit();
            } catch (Exception e) {
                e.printStackTrace();
                handle.rollback();

            }
        });

    }


    public static void main(String[] args) {
//        System.out.println(DAOMovie.views(10));
//          System.out.println(new DAOMovie().getMaxPercentSale(15));
//        System.out.println(new DAOMovie().getMinSamePrice(10));
//        System.out.println(new DAOMovie().getMaxUnitPrice(10));
//        System.out.println(new DAOMovie().getMaxUnitPrice(11));
//        System.out.println(new DAOMovie().getMaxUnitPrice(14));
//        System.out.println(new DAOMovie().getMaxUnitPrice(15));
//        System.out.println(new DAOMovie().getMaxUnitPrice(16));
//        System.out.println(new DAOMovie().getMaxUnitPrice(17));
//        System.out.println(new DAOMovie().getMaxUnitPrice(18));
//        System.out.println(new DAOMovie().getMaxUnitPrice(19));
//        System.out.println(new DAOMovie().renderMovie(0,8,true,false,false));
//        System.out.println(new DAOMovie().renderMovie(8,8,true,false,false));
//        System.out.println(new DAOMovie().getMoviebyId(14));
//        System.out.println(new DAOMovie().getMoviebyId(14).getRenderPrice());
//        System.out.println(new DAOMovie().getProducers(10));
//        System.out.println(new DAOMovie().totalMovie());

//        System.out.println(new DAOMovie().renderMovie(0,9,"isAtoZ"));
//        System.out.println(DAOMovie.getListGenreHeader());
//        System.out.println(new DAOMovie().getPurchasedMovie(1));
//        System.out.println(DAOMovie.getListChapter("10"));
//        System.out.println(DAOMovie.isValidChapter("10","1"));
//        System.out.println(DAOMovie.isValidChapter("10","2"));
//        DAOMovie.addChapter("10","2","30","/anime-main//storage/chapters/7year.mp4");
//        System.out.println(DAOMovie.isValidChapter("10","35"));
        System.out.println(findChapter(57));
    }



}
