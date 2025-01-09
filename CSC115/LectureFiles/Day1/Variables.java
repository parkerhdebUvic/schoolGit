public class Variables {

    public static void main(String[] args) {
        // Variable declaration:
        // type name = value;

        // Update:
        // name = newValue;

        int x = 3;
        System.out.println(x);

        x += 2;
        System.out.println(x);

        x++;
        System.out.println(x);



        double y = 9.89;
        System.out.println(y);


        // converting one type to another is called casting

        double z = x;
        System.out.println(z);

        // this will give an error (loss of precision)
        // int n = y;
        // System.out.println(n);

        //so, you have to explicitly make the cast.
        //this will truncate
        int n = (int)y;
        System.out.println(n);

    }




}
    
