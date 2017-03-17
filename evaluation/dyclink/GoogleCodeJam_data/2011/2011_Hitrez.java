import java.io.*;
import java.util.*;

public class Main
{
   public static void main(String[] args) throws IOException
   {
      new Main().run();
   }

   StreamTokenizer in;
   PrintWriter out;
   public double[] ver = new double[11];
   public double[] fact = new double[11];

   String nextString() throws IOException
   {
      in.nextToken();
      return (String)in.sval;
   }

   int nextInt() throws IOException
   {
      in.nextToken();
      return (int)in.nval;
   }
   
   long nextLong() throws IOException {
	   in.nextToken();
	   return (long)in.nval;
   }

   double nextDouble() throws IOException {
	   in.nextToken();
	   return (double)in.nval;
   }
   
   
   void run() throws IOException
   {
      in = new StreamTokenizer(new BufferedReader(new FileReader("A-large.in")));
      out = new PrintWriter(new FileWriter("A-large.out"));
      solve();
      out.flush();
   }

 
   void solve() throws IOException
   {
	   int num_t = nextInt();
	   for (int i_t=0; i_t<num_t; i_t++){
		   double W = nextDouble();
		   int L = nextInt();
		   int U = nextInt();
		   int G = nextInt();
		   double[][] l = new double[L][2];
		   double[][] u = new double[U][2];
		   for (int i=0; i<L; i++){
			   l[i][0] = nextDouble();
			   l[i][1] = nextDouble();
		   }
		   for (int i=0; i<U; i++){
			   u[i][0] = nextDouble();
			   u[i][1] = nextDouble();
		   }
		   out.println("Case #"+(i_t+1)+": ");
		   double s = 0;
		   double sl = 0;
		   double su = 0;
		   for (int i=1; i<L; i++){
			   sl += (l[i][0] - l[i-1][0]) * (l[i][1] + l[i-1][1]) / 2;
		   }
		   for (int i=1; i<U; i++){
			   su += (u[i][0] - u[i-1][0]) * (u[i][1] + u[i-1][1]) / 2;
		   }
		   s = su - sl;
		   for (int i=1; i<G; i++){
			   double st = ((double)i/(double)G) * s;
			   double scur = 0;
			   double xcur = 0;
			   double ycurl = l[0][1];
			   double ycuru = u[0][1];
			   int il = 0;
			   int iu = 0;
			   while (true){
				   double xnew = Math.min(l[il + 1][0], u[iu + 1][0]);
				   double kl = (l[il+1][1] - l[il][1]) / (l[il + 1][0] - l[il][0]);
				   double bl = l[il][1] - (kl * l[il][0]);
				   double ku = (u[iu+1][1] - u[iu][1]) / (u[iu + 1][0] - u[iu][0]);
				   double bu = u[iu][1] - (ku * u[iu][0]);
				   double ynewl = kl * xnew + bl;
				   double ynewu = ku * xnew + bu;
				   double ss = (xnew - xcur) * ((ycuru - ycurl) + (ynewu - ynewl)) / 2;
				   if (ss + scur >= st){
					   double sss = st - scur;
					   double a = ku - kl;
					   double b = (bu - bl + ycuru - ycurl) - ((ku - kl) * xcur);
					   double c = - xcur * (bu - bl + ycuru - ycurl);
					   c -= 2 * sss;
					   double g = 0;
					   if (Math.abs(a)>=1e-11){
					   double discr = b * b - 4 *a * c;
					   g = (-b + Math.sqrt(discr)) / (2*a);
					   } else {
						   g = - c / b;
					   }
					   
					   out.println(g);
					   break;
				   } else {
					   scur += ss;
					   xcur = xnew;
					   ycurl = ynewl;
					   ycuru = ynewu;
					   if (l[il + 1][0] == xnew){
						   il++;
					   }
					   if (u[iu + 1][0] == xnew){
						   iu++;
					   }
				   }
			   }
		   }
	   }
   } 
}