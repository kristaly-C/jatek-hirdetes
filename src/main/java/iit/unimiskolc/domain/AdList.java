package iit.unimiskolc.domain;

import iit.unimiskolc.exception.EmptyListException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class AdList {
    private HashSet<GameAd> adlist;

    public AdList(HashSet<GameAd> gameAds){
        adlist = gameAds;
    }

    public void addAd(GameAd gamead){
            this.adlist.add(gamead);
    }

    public GameAd getAd(long id ) throws EmptyListException {
        return findById(id);
    }

    public ArrayList<GameAd> getAllAd(){
       ArrayList<GameAd> list = new ArrayList<>();
        Iterator<GameAd> iter = adlist.iterator();
       while (iter.hasNext()){
           list.add(iter.next());
       }
       return list;
    }

    public void deleteAd(long id){

        try {
            this.adlist.remove(findById(id));
        }catch (Exception | EmptyListException e){
            System.out.println(e.getMessage());
        }


    }

    private GameAd findById(long id) throws EmptyListException {
       Iterator<GameAd> value = adlist.iterator();
       while (value.hasNext()){
           GameAd ad = value.next();
           if (ad.getId() == id){
               return ad;
           }
       }
       throw new EmptyListException();
    }

    public int sizeOfAds(){
        return this.adlist.size();
    }
}
