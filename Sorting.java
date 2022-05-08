/**
 * CS 251: Data Structures and Algorithms
 * Project 3: Part 1
 *
 * TODO: implement the sorting methods.
 *
 * @author TODO: add your name here
 * @username TODO: add your Purdue username here
 * @sources TODO: list your sources here
 *
 * DO NOT COPY PASTE THE ALGORITHMS FOR SORTING METHODS FROM THE INTERNET. WE WILL KNOW!
 *
 */

import java.util.ArrayList;

public class Sorting<Item extends Comparable<Item>> {


    /**
     *
     * Default Constructor
     *
     */
    public Sorting() {
    }

    /**
     *
     * returns true if the Item at index @param i in @ param list is greater than Item at index @param j
     * else return false
     *
     * @param list
     * @param i
     * @param j
     * @return
     */
    public boolean greaterThan(ArrayList<Item> list, int i, int j) {
        return list.get(i).compareTo(list.get(j)) > 0;
    }

    /**
     *
     * returns true if the Item @param i is greater than Item @param j else return false
     *
     * @param i
     * @param j
     * @return
     */
    public boolean greaterThan(Item i, Item j) {
        return i.compareTo(j) > 0;
    }


    /**
     *
     * returns true if the Item at index @param i in @ param list is lesser than Item at index @param j
     * else return false
     *
     * @param list
     * @param i
     * @param j
     * @return
     */
    public boolean lessThan(ArrayList<Item> list, int i, int j) {
        return list.get(i).compareTo(list.get(j)) < 0;
    }

    /**
     *
     * returns true if the Item @param i is less than Item @param j else return false
     *
     * @param i
     * @param j
     * @return
     */
    public boolean lessThan(Item i, Item j) {
        return i.compareTo(j) < 0;
    }


    /**
     *
     * returns true if the Item @param i is equal to Item @param j else return false
     *
     * @param i
     * @param j
     * @return
     */
    public boolean equal(Item i, Item j) {
        return i.compareTo(j) == 0;
    }


    /**
     *
     * returns true if the Item at index @param i in @ param list is equal to the Item at index @param j
     * else return false
     *
     * @param list
     * @param i
     * @param j
     * @return
     */
    public boolean equal(ArrayList<Item> list, int i, int j) {
        return list.get(i).compareTo(list.get(j)) == 0;
    }


    /**
     * Sorts the list in ascending order using Insertion Sort.
     *
     *
     * @param list
     */
    public ArrayList<Item> insertionSort(ArrayList<Item> list) {
        int index = 1;
        int swap_index = -1;
        while (index != list.size()) {

            for (int i = index; i >= 0; i--) {
                if (list.get(index).compareTo(list.get(i)) < 0) {
                    swap_index = i;

                }
            }

            if (swap_index != -1) {
                int temp_index = index;
                while (temp_index > swap_index) {
                    Item temp = list.get(temp_index);
                    list.set(temp_index, list.get(temp_index - 1));
                    list.set(temp_index - 1, temp);
                    temp_index = temp_index - 1;

                }
            }
            swap_index = -1;
            index++;

        }

        return list;

    }


    /**
     *
     * Sorts the list in ascending order using Merge Sort.
     *
     * @param list
     */

    public ArrayList<Item> mergeSort(ArrayList<Item> list) {

        return HelpMergeSort(list, list.size());

    }

    private ArrayList<Item> merge(
            ArrayList<Item> lala, ArrayList<Item> wolf, ArrayList<Item> rawr, int sol, int sag) {

        int counter = 0;
        int kounter1 = 0;
        int jounter2 = 0;

        if (counter < sol && kounter1 < sag) {
            do {
                if (wolf.get(counter).compareTo(rawr.get(kounter1)) <= 0) {
                    lala.set(jounter2++, wolf.get(counter++));
                } else {
                    lala.set(jounter2++, rawr.get(kounter1++));
                }
            } while (counter < sol && kounter1 < sag);
        }
        if (counter < sol) {
            do {
                lala.set(jounter2++, wolf.get(counter++));
            } while (counter < sol);
        }
        if (kounter1 < sag) {
            do {
                lala.set(jounter2++, rawr.get(kounter1++));
            } while (kounter1 < sag);
        }
        return lala;
    }

    private ArrayList<Item> HelpMergeSort(ArrayList<Item> list, int rte) {
        if (rte < 2) {
            return null;
        }
        int mid = rte / 2;

        ArrayList<Item> luther =  new ArrayList<>();
        {
            int i =  0;
            while (i < mid) {
                luther.add(list.get(i));
                i++;
            }
        }
        ArrayList<Item> reee =  new ArrayList<>();
        for (int i = rte - 1; i >= mid; i--) {
            reee.add(list.get(i));
        }


        HelpMergeSort(luther, mid);
        HelpMergeSort(reee, rte - mid);

        return merge(list, luther, reee, mid, rte - mid);


    }


    /**
     *
     * prints the Items in the @param list.
     *
     * @param list
     */
    public void print(ArrayList<Item> list) {
        int n = list.size();

        StringBuffer bf = new StringBuffer();
        bf.append(list.get(0).toString());

        for (int i = 1;i < n; i += 1) {
            bf.append(" " + list.get(i).toString());
        }

        System.out.println(bf.toString());
    }


    /**
     *
     * Swaps the element at index @param i with element at index @param j in the @param list.
     *
     * @param list
     * @param i
     * @param j
     */
    private void swap(ArrayList<Item> list, int i, int j) {
        Item temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     *
     * This is the main function to run the Sorting class to help with debugging.
     *
     * @param args
     */
    public static void main(String[] args) {

        Sorting<Integer> s = new Sorting<>();
        ArrayList<Integer> A = new ArrayList<Integer>();
        Integer[] K = {4,4,3,1,3,9,8,2,5,6};

        for (Integer k : K) {
            A.add(k);
        }

        s.print(A);

    }

}
