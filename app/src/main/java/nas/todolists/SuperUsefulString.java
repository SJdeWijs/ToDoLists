package nas.todolists;

/**
 * Created by Sander de Wijs on 28-11-2016.
 */
public class SuperUsefulString {
    private static SuperUsefulString ourInstance = new SuperUsefulString();
    private static String superUsefulString;

    // zelfde als SuperUsefulString manager = new SuperUsefulString()
    public static SuperUsefulString getInstance() {
        return ourInstance;
    }

    // constructor is private
    private SuperUsefulString() {
        superUsefulString = "this is the initial string";
    }


    public void setSuperUsefulString(String newString){
        superUsefulString = newString;
    }

    public String getSuperUsefulString(){
        return superUsefulString;
    }
}
