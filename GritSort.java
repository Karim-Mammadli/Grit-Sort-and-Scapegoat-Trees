import java.util.ArrayList;
import java.util.HashMap;

public class GritSort<Item extends Comparable<Item>> {

    /**
     *
     * Default Constructor
     *
     */
    public GritSort() {
    }

    public ArrayList<Item> grit(ArrayList<Item> list) {

        return mergeChunk(makeChunks(list));


    }
    public ArrayList<ArrayList<Item>> makeChunks(ArrayList<Item> list) {
        int index = 0;
        int check_sorting = -1;
        int c = 0;
        ArrayList<ArrayList<Item>> chunk_fin = new ArrayList<ArrayList<Item>>();

        while (index != list.size()) {
            for (int i = index; i < list.size(); i++) {
                if (i+1 == list.size()){
                    break;
                }
                if (list.get(i).compareTo(list.get(i + 1)) <= 0) {
                    check_sorting = i+1;

                }
                else {
                    c = i;
                    i = list.size();
                }
            }
            if (check_sorting == -1) {
                ArrayList<Item> aue = new ArrayList<Item>();
                aue.add(list.get(c));
                chunk_fin.add(aue);

                index += 1;

            }
            if (check_sorting != -1) {

                index = check_sorting + 1;
                int count = 0;
                for (int z = 0; z < chunk_fin.size(); z ++){
                    for (int p = 0; p < chunk_fin.get(z).size(); p++) {
                        count += 1;
                    }
                }
                ArrayList<Item> aue = new ArrayList<Item>();
                for (int x =  count ; x <= check_sorting; x++ ) {
                    aue.add(list.get(x));
                }
                chunk_fin.add(aue);
            }
            check_sorting = -1;
        }

        return chunk_fin;
    }
    public HashMap<Integer, ArrayList<ArrayList<Item>>> makeBuckets(ArrayList<ArrayList<Item>> chunks) {
        HashMap<Integer, ArrayList<ArrayList<Item>>> buckets = new HashMap<Integer, ArrayList<ArrayList<Item>>>();

        ArrayList<Integer> guclu = new ArrayList<>();

        int count  = 0;

//        System.out.println("salam");

        for (int z = 0; z < chunks.size(); z++) {
            for (int i = 0; i < guclu.size(); i++) {
                if (guclu.get(i) == chunks.get(z).size()){
                    count  = 1;
                    break;
                }
            }

            ArrayList<ArrayList<Item>> chunk_fin = new ArrayList<ArrayList<Item>>();


            if (count != 1) {
                buckets.put(chunks.get(z).size(), chunk_fin);
                guclu.add(chunks.get(z).size());
            }
            count = 0;

        }

        for (int z  = 0; z < chunks.size(); z++) {
            if (buckets.containsKey(chunks.get(z).size())) {
                buckets.get(chunks.get(z).size()).add(chunks.get(z));

            }

        }

        return buckets;
    }



    public ArrayList<Item> mergeChunk(ArrayList<ArrayList<Item>> bucket) {
        ArrayList<Item> blya = new ArrayList<Item>();

        for (int x = 0; x < bucket.size(); x++) {
            for (int z = 0; z < bucket.get(x).size(); z ++) {
                blya.add(bucket.get(x).get(z));
            }

        }

        Sorting<Item> sorting = new Sorting<Item>();
        return sorting.insertionSort(blya);

        // TODO: part 1

    }

    public static void main(String[] args) {
        Sorting<Integer> s = new Sorting<>();
        ArrayList<Integer> A = new ArrayList<Integer>();
        Integer[] K = {3,5,6,8,10,2,4,5,6,1,2,4,5};

        for (Integer k : K) {
            A.add(k);
        }

        s.print(A);

    }

}
