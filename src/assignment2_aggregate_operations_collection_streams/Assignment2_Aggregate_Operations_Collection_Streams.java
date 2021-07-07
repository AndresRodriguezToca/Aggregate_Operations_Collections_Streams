/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2_aggregate_operations_collection_streams;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



//See Week #6  > Exercise21_13 to see how to call URLs and get contents.
//Might need additional Comparator for search(by rank)



class mainBabyClassName implements Comparable<mainBabyClassName>// implements ....

{
    // provide attributes
    String ranking, name, totalbabies, gender, year;
    // only 1 contructor
    public mainBabyClassName(String ranking, String name, String totalbabies, String gender, String year) {
        this.ranking = ranking;
        this.name = name;
        this.totalbabies = totalbabies;
        this.gender = gender;
        this.year = year;
    } 
    // getters/setters
    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalbabies() {
        return totalbabies;
    }

    public void setTotalbabies(String totalbabies) {
        this.totalbabies = totalbabies;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "whateverBabyClassName{" + "ranking=" + ranking + ", name=" + name + ", totalbabies=" + totalbabies + ", gender=" + gender + ", year=" + year + '}';
    }

    
    
    
    
    // others as per instructions
    public int compareTo(mainBabyClassName st){ 
        int compare = this.getName().compareTo(st.getName());
            if(compare == 0){
                compare = this.getYear().compareTo(st.getYear());
            }
            return compare;
    } 
}

class MyDatabase// for storing names

{
    // two attributes
        List<mainBabyClassName> listBabies = new ArrayList<mainBabyClassName>();
    // only 1 constructor

    public MyDatabase(){
    }
        public FileWriter myWriter;
        public void loadData()
        {
            
            // provide implementation // 15 - 20 Lines of code
            String[] years= {"2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010"};
            ArrayList<String> lines = new ArrayList<String>();
            int count = 0;
            for (int i = 0; i < 10; i++) {
                try {
                    URL databaseBabies = new URL("http://liveexample.pearsoncmg.com/data/babynamesranking" + years[i] + ".txt");
                    BufferedReader in = new BufferedReader(
                    new InputStreamReader(databaseBabies.openStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null){
                      lines.add(inputLine);
                      String[] words = lines.get(0).replace(" ", "").split("	");
                      listBabies.add(new mainBabyClassName(words[0], words[1], words[2], "Male", years[i]));
                      listBabies.add(new mainBabyClassName(words[0], words[3], words[4], "Female", years[i]));
                      lines.clear();
                    }
                    Collections.sort(listBabies);
                    in.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            
        }
      
      // String representation of the database, you can call it from print:
      // write it to ouput file and return empty string OR
      // return it as String and output to file in print.
      // No need for streams or lambda
      // public String toString()

        public String printDatabase() {
            
            try {
                myWriter = new FileWriter("babyNamesOuput.txt");
                System.out.println("-------------------------Database Content----------------------------");
                myWriter.write("-------------------------Database Content----------------------------\n");
                String nameLoop = "";
                for (mainBabyClassName baby : listBabies) {
                    if (nameLoop.isEmpty() || !nameLoop.equals(baby.getName())) {
                        nameLoop = baby.getName();
                        System.out.println(nameLoop + " was ranked the following years:");
                        myWriter.write(nameLoop + " was ranked the following years:\n");
                        for (mainBabyClassName baby2 : listBabies) {
                            if (nameLoop.equals(baby2.getName())) {
                                System.out.println(baby2.getYear() + ": " + baby2.getName() + " " + baby2.getGender() + " rank: " + baby2.getRanking() + " count: " + baby2.getTotalbabies());
                                myWriter.write(baby2.getYear() + ": " + baby2.getName() + " " + baby2.getGender() + " rank: " + baby2.getRanking() + " count: " + baby2.getTotalbabies() + "\n");
                            }
                        }
                        System.out.println("\n");
                        myWriter.write("\n");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }
        
        public String printDatabaseGroupedByGender(){
            try {
                System.out.println("---------------Database Content grouped by Gender----------------");
                myWriter.write("-------------------------Database Content----------------------------\n");
                String nameLoop = "";
                for (mainBabyClassName baby : listBabies) {
                    if (nameLoop.isEmpty() || !nameLoop.equals(baby.getName())) {
                        nameLoop = baby.getName();
                        System.out.println(nameLoop + " was ranked the following years:");
                        myWriter.write(nameLoop + " was ranked the following years:\n");
                        System.out.println("As a " + baby.getGender());
                        myWriter.write("As a " + baby.getGender() + "\n");
                        for (mainBabyClassName baby2 : listBabies) {
                            if (nameLoop.equals(baby2.getName())) {
                                System.out.println(baby2.getYear() + ": " + "rank: " + baby2.getRanking() + " count: " + baby2.getTotalbabies());
                                myWriter.write(baby2.getYear() + ": " + "rank: " + baby2.getRanking() + " count: " + baby2.getTotalbabies() + "\n");
                            }
                        }
                        System.out.println("\n");
                        myWriter.write("\n");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            
            return null;
        }
        
        public String search(String name){
            try {
                int count = 0;
                System.out.println("\n");
                myWriter.write("\n");
                System.out.println("Searching by: " + name);
                myWriter.write("Searching by: " + name + "\n");
                for (mainBabyClassName baby : listBabies) {
                    if (baby.getName().equals(name)) {
                        count++;
                        System.out.println(baby.getYear() + ": " + "rank: " + baby.getRanking() + " count: " + baby.getTotalbabies());
                        myWriter.write(baby.getYear() + ": " + "rank: " + baby.getRanking() + " count: " + baby.getTotalbabies() + "\n");
                    }
                }
                if (count == 0) {
                    System.out.println(name + " NOT found in database");
                    myWriter.write(name + " NOT found in database\n");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }
        
        public String search(String name, int year){
            try {
                String yearConverted = String.valueOf(year);
                int count = 0;
                System.out.println("\n");
                myWriter.write("\n");
                System.out.println("Searching by: " + name + " and " + yearConverted);
                myWriter.write("Searching by: " + name + " and " + yearConverted + "\n");
                for (mainBabyClassName baby : listBabies) {
                    if (baby.getName().equals(name) && baby.getYear().equals(yearConverted)) {
                        count++;
                        System.out.println(baby.getYear() + ": " + "rank: " + baby.getRanking() + " count: " + baby.getTotalbabies());
                        myWriter.write(baby.getYear() + ": " + "rank: " + baby.getRanking() + " count: " + baby.getTotalbabies() + "\n");
                    }
                }
                if (count == 0) {
                    System.out.println(name + " NOT found in database");
                    myWriter.write(name + " NOT found in database\n");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }
        
        public String search(String name, String gender){
            try {
                int count = 0;
                System.out.println("\n");
                myWriter.write("\n");
                System.out.println("Searching by: " + name + " and " + gender);
                myWriter.write("Searching by: " + name + " and " + gender + "\n");
                for (mainBabyClassName baby : listBabies) {
                    if (baby.getName().equals(name) && baby.getGender().equals(gender)) {
                        count++;
                        System.out.println(baby.getYear() + ": " + "rank: " + baby.getRanking() + " count: " + baby.getTotalbabies());
                        myWriter.write(baby.getYear() + ": " + "rank: " + baby.getRanking() + " count: " + baby.getTotalbabies() + "\n");
                    }
                }
                if (count == 0) {
                    System.out.println(name + " NOT found in database");
                    myWriter.write(name + " NOT found in database\n");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            
            return null;
        }
        
        public String search(String name, String gender, int year){
            try {
                String yearConverted = String.valueOf(year);
                int count = 0;
                System.out.println("\n");
                myWriter.write("\n");
                System.out.println("Searching by: " + name + " and " + gender + " and " + yearConverted);
                myWriter.write("Searching by: " + name + " and " + gender + " and " + yearConverted + "\n");
                for (mainBabyClassName baby : listBabies) {
                    if (baby.getName().equals(name) && baby.getGender().equals(gender) && baby.getYear().equals(yearConverted)) {
                        count++;
                        System.out.println(baby.getYear() + ": " + "rank: " + baby.getRanking() + " count: " + baby.getTotalbabies());
                        myWriter.write(baby.getYear() + ": " + "rank: " + baby.getRanking() + " count: " + baby.getTotalbabies() + "\n");
                    }
                }
                if (count == 0) {
                    System.out.println(name + " NOT found in database");
                    myWriter.write(name + " NOT found in database\n");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }
        
        public String numberOfYearsRanked(String name, String gender, int year1, int year2){
            try {
                String year1Converted = String.valueOf(year1);
                String year2Converted = String.valueOf(year2);
                int count = 0;
                int countYears = 0;
                System.out.println("\n");
                myWriter.write("\n");
                System.out.println("numberOfYearsRanked by: " + name + " and " + gender + " and " + year1Converted + " " + year2Converted);
                myWriter.write("numberOfYearsRanked by: " + name + " and " + gender + " and " + year1Converted + " " + year2Converted + "\n");
                for (mainBabyClassName baby : listBabies) {
                    if (baby.getName().equals(name) && baby.getGender().equals(gender)) {
                        int yearLoop = Integer.valueOf(baby.getYear());
                        if (yearLoop <= year2 && yearLoop >= year1) {
                            countYears++;
                        }
                    }
                }
                System.out.println("Years: " + countYears);
                myWriter.write("Years: " + countYears + "\n");
            } catch (Exception e) {
                System.out.println(e);
            }
            
            return null;
        }
        
        public String countName(String name, String gender, int year1, int year2){
            try {
                String year1Converted = String.valueOf(year1);
                String year2Converted = String.valueOf(year2);
                int count = 0;
                int countBabies = 0;
                System.out.println("\n");
                myWriter.write("\n");
                System.out.println("numberOfYearsRanked by: " + name + " and " + gender + " and " + year1Converted + " " + year2Converted);
                myWriter.write("numberOfYearsRanked by: " + name + " and " + gender + " and " + year1Converted + " " + year2Converted + "\n");
                for (mainBabyClassName baby : listBabies) {
                    if (baby.getName().equals(name) && baby.getGender().equals(gender)) {
                        int yearLoop = Integer.valueOf(baby.getYear());
                        if (yearLoop <= year2 && yearLoop >= year1) {
                            int babiesTotal = Integer.valueOf(baby.getTotalbabies());
                            countBabies = countBabies + babiesTotal;
                        }
                    }
                }
                System.out.println(name + " total: " + countBabies);
                myWriter.write(name + " total: " + countBabies + "\n");
            } catch (Exception e) {
                System.out.println(e);
            }
            
            return null;
        }
        
        public String search(int rank){
            try {
                int count = 0;
                ArrayList<String> babiesFound = new ArrayList<>();
                System.out.println("\n");
                myWriter.write("\n");
                System.out.println("Searching by rank: " + rank);
                myWriter.write("Searching by rank: " + rank + "\n");
                for (mainBabyClassName baby : listBabies) {
                    int rankConverted = Integer.valueOf(baby.getRanking());
                    if (rankConverted == rank) {
                        count++;
                        babiesFound.add(baby.getYear() + ": " + baby.getName() + " " + baby.getGender() + " rank: " + baby.getRanking() + " count: " + baby.getTotalbabies());
                    }
                }
                System.out.println("Total found: " + count);
                myWriter.write("Total found: " + count + "\n");
                if (count > 0) {
                    for (String line : babiesFound) {
                        System.out.println(line);
                        myWriter.write(line + "\n");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return null;
            
        }
        
        public void closePrintWriter(){
            try {
                myWriter.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            
        }
      
      // to avoid duplication of code with same functionality

      // outputs filteredList or NOT found message. See output file.

      // the second parameter is an Array of criterias

      // Ex: displayBabyNames(oneList, "1", "2", ... n) you can pass any number n of

      // criterias

      // Hint: the count methods do not call this method

      // private void displayBabyNames(List<Something> oneList, String...

      // criteria)  //{ no streams required }
 
      // implement methods derived from main
}

public class Assignment2_Aggregate_Operations_Collection_Streams {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException{
        // TODO code application logic here
        {
        
            MyDatabase myDatabase = new MyDatabase();
            myDatabase.loadData();
            myDatabase.printDatabase();
            myDatabase.printDatabaseGroupedByGender();
            myDatabase.search("Emily");
            myDatabase.search("Taylor");
            myDatabase.search("TaylorXXXX");
            myDatabase.search("TaylorXXXX", 2006);
            myDatabase.search("Taylor", 2008);
            myDatabase.search("Taylor", "Male");
            myDatabase.search("Taylor", "Female");
            myDatabase.search("Taylor", "Female", 2003);
            myDatabase.search("Zara");
            myDatabase.numberOfYearsRanked("Zara", "Female", 2001, 2008);
            myDatabase.numberOfYearsRanked("Zarax", "Female", 2001, 2008);
            myDatabase.countName("Zara", "Female", 2001, 2008);
            myDatabase.countName("Zara", "Female", 2009, 2010);
            myDatabase.search(3);
            myDatabase.search(1200);
            myDatabase.closePrintWriter();
            System.out.println("------- Done -------");
        }
    }
}
