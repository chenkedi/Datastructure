package graph.structure;

public class Edge implements IEdge, Comparable<IEdge>{

   private final int v;
   private final int w;
   private final double weight;

   public Edge (int v, int w, double weight) {
       this.v = v;
       this.w = w;
       this.weight = weight;
   }

   @Override
   public int either() {
      return v;
   }

   @Override
   public int other(int vertex) {
     if (vertex == v)
         return w;
     else if (vertex == w)
         return v;
     else
         throw new RuntimeException("Inconsistent edge");
   }

   @Override
    public double weight() {
       return weight;
   }

   @Override
    public int compareTo(IEdge that) {
     if (this.weight > that.weight())
         return 1;
     else if (this.weight < that.weight())
         return -1;
     else
         return 0;
   }

   @Override
    public String toString() {
       return String.format("%d-%d %.2f", v, w, weight);
   }


}
