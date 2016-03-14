package anonymous.Mechanics;

/**
 * Created by joelvalentine on 07/03/2016.
 */
public class ScrollingText {

    public ScrollingText(){

    }

    public ScrollingText(String s){
        char[] scrollingArray = s.toCharArray();
        for(int i = 0; i < scrollingArray.length; i++){
            if(i==0){
                System.out.println();
            }
            try {
                Thread.sleep(190);
                System.out.print(scrollingArray[i]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
