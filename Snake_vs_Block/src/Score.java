public class Score {
    private int position;
    private String date;
    private int points;

    Score(int pos, String d,int sc){
        date=d;
        points=sc;
        position=pos;
    }

    public String getDate(){
        return date;
    }

    public int getPoints(){
        return points;
    }
    public int getPosition(){
        return position;
    }
}
