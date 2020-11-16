public class Body {
//     public int demofunc() {  //被测模块
//           int n1 = 4, n2 = 5;
//           int a;
//           a = sumFunc(n1, n2);
//           System.out.println(a);
//           return a;
//       } //模块A

//     public int sumFunc(int a, int b){
//    	  return(144);
//     }    //模块A的桩模块

//        public int sumFunc(int a, int b) {
//           int c1, c2;
//           c1 = factorial(a);
//           c2 = factorial(b);
//           return (c1 + c2);
//      }//模块B  A的子模块

//       public int factorial(int n) {
//      	if (n==4)
//      		return 24;
//      	else
//      		return 120;
//      }   //模块B的桩模块

    public int factorial(int n) {
        int rtn = 1;
        int i;
        for (i = 1; i <= n; i++) {
            rtn *= i;
        }
        return (rtn);
    }//模块C

}
