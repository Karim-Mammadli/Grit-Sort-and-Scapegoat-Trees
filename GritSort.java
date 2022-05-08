/**
 * CS 251: Data Structures and Algorithms
 * Project 3: Part 1
 *
 * TODO: implement gritSort.
 *
 * @author TODO: add your name here
 * @username TODO: add your Purdue username here
 * @sources TODO: list your sources here
 *
 * Use the algorithms written in sorting to implement this class.
 *
 */
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

    /**
     *
     * Implement Grit Sort as defined in the Handout.
     * for example:
     * list = {3,5,6,8,10,2,4,5,6,1,2,4,5}
     * return value = {1,2,2,3,4,4,5,5,5,6,6,8,10}
     *
     *
     * @param list
     * @return sorted list
     *
     */
    public ArrayList<Item> grit(ArrayList<Item> list) {

        return mergeChunk(makeChunks(list));




        /*
            TODO: part 1
            TODO: STEP 1: implement and call makeChunks on list to divide the list into the sorted chunks.

            TODO: STEP 1.5: make the required number of buckets(can differ from implementation to implementation) and
            TODO            put each chunk into a bucket of proper size.
            TODO: STEP 2: implement and call mergeChunk to merge the chunks in a bucket. do this for every bucket
            TODO: STEP 3: merge the buckets and return the elements of the merged buckets as a list
         */


    }

    /**
     *
     * This function takes in a list and returns an ArrayList of ArrayList.
     * Each chunk found is stored in an array list and then all the chunks are in turn stored in an array list which is
     * then returned.
     *
     * for example
     * list = {3,5,6,8,10,2,4,5,6,1,2,4,5}
     *
     * return value = {
     *                     {3,5,6,8,10},
     *                     {2,4,5,6},
     *                     {1,2,4,5}
     *                }
     *
     * @param list
     * @return array list of chunks(sorted sub-lists in the original input list)
     *
     */
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



    /**
     *
     * This function takes in an ArrayList of ArrayList and returns an HashMap<Integer, ArrayList<ArrayList<Item>>>
     * Chunks of the same size are stored in the same bucket.
     * Each chunk of the size say S is stored in a bucket which corresponds to that size S.
     * Thus, for the returned HashMap, the key is the chunk size, and the corresponding value is a list of chunks of that size.
     * for example
     * chunks = {
     *              {3,5,6,8,10},
     *              {2,4,5,6},
     *              {1,2,4,5}
     *          }
     *
     * return value = {
     * 					4: {{2,4,5,6},{1,2,4,5}},
     * 					5: {{3,5,6,8,10}}
     * 				  }
     * i.e., the bucket for chunks of size 4 will have {{2,4,5,6},{1,2,4,5}} and
     * bucket for chunks of size 5 will have {{3,5,6,8,10}}
     *
     * @param chunks
     * @return HashMap, where key is the chunk size and value is a list of chunks of that size
     * (sorted sub-lists in the original input list)
     *
     */
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



        // make buckets is a helper function.
        // CHANGE THIS ACCORDING TO YOUR IMPLEMENTATION
//        System.out.println("aue");
        return buckets;
    }





    /**
     *
     * This function takes in a bucket(ArrayList of ArrayList) and returns an ArrayList which has the chunks in
     * the bucket merged.
     *
     * for example
     * bucket = {
     *          {2,4,5,6},
     *          {1,2,4,5}
     *         }
     *
     * return value = {1,2,2,4,4,5,5,6}
     *
     *
     * @param bucket
     * @return merged and sorted list
     *
     */
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




    /**
     *
     * This is the main function to run the gritSort class to help with debugging.
     *
     * @param args
     */
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
