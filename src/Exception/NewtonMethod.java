package Exception;

/**
 *
 * @author tadaki
 */
public class NewtonMethod {

    /**
     * Newton法で平方根を求める
     * @param c 正の実数
     * @param n iterationの回数
     * @return
     * @throws IllegalArgumentException cが負の場合には例外を発生
     */
    public static double sqr(double c,int n) throws IllegalArgumentException{
        if(c<0){
           throw new IllegalArgumentException("no root for negative values"); 
        }
        double xn=0.;
        while(xn*xn<c){
            xn++;
        }
        for(int i=0;i<n;i++){
            xn = (xn+c/xn)/2.;
        }
        return xn;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double x[]={0.5,2,4,8,-7};
        int n=10;
        for(double d:x){
            try{
            double y = sqr(d,n);
            System.out.println("root of "+d+"="+y);
            } catch(IllegalArgumentException e){
                System.err.println(e);
            }
        }
    }
    
}
