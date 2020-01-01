import java.util.*;
import java.io.*;

public class Main {
     public static void main(String[] args) throws Exception {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

          PriorityQueue<Query> queries = new PriorityQueue<Query>();

          String line;
          while (!(line = br.readLine()).equals("#")) {
               String params[] = line.split(" ", 0);
               queries.add(new Query(Integer.parseInt(params[1]), Integer.parseInt(params[2])));
          }

          int k = Integer.parseInt(br.readLine());
          for (int i = 0; i < k; i++) {
               Query q = queries.poll();
               System.out.println(q.id);
               q.updateTime();
               queries.add(q);
          }
     }
}

class Query implements Comparable<Query> {
     int currentTime, intervalTime, id;

     public Query(int id, int time) {
          this.currentTime = time;
          this.intervalTime = time;
          this.id = id;
     }

     public void updateTime() {
          this.currentTime += this.intervalTime;
     }

     @Override
     public int compareTo(Query q) {
          if (this.currentTime == q.currentTime)
               return this.id - q.id;
          return this.currentTime - q.currentTime;
     }
}
