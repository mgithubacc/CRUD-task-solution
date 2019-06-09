
import java.util.*;
import java.text.*;



public class Main {

    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        int q = (args.length-1)/3;

        switch (args[0]) {
            case "-c" :  synchronized(allPeople) {

                for (int i=0;i<q;i++) {

                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

                    Date dateCreate = new Date();
                    try{
                        dateCreate = df.parse(args[3+3*i]);
                    } catch (ParseException e) {}

                    if(args[2+3*i].equals("м")) {
                        allPeople.add(Person.createMale(args[3*i+1],dateCreate));
                    }
                    else if(args[2+3*i].equals("ж")) {
                        allPeople.add(Person.createFemale(args[3*i+1],dateCreate));
                    }


                    System.out.println(allPeople.size()-1);
                }
            } ; break;

            case "-u" : synchronized(allPeople){
                int qForU = (args.length-1)/4;
                for (int i=0;i<qForU;i++) {
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                    Date dateUpdate = new Date();
                    try{
                        dateUpdate = df.parse(args[4+4*i]);
                    } catch (ParseException e) {}


                    if(args[3+i*4].equals("м")) {
                        allPeople.get(Integer.parseInt(args[4*i+1])).setSex(Sex.MALE);
                    }
                    else if(args[3+i*4].equals("ж")) {
                        allPeople.get(Integer.parseInt(args[4*i+1])).setSex(Sex.FEMALE);
                    }



                    allPeople.get(Integer.parseInt(args[4*i+1])).setBirthDate(dateUpdate);
                    allPeople.get(Integer.parseInt(args[4*i+1])).setName(args[2+4*i]);

                }

            }; break;

            case "-d" : synchronized(allPeople){
                for (int i=0;i<args.length-1;i++) {
                    allPeople.get(Integer.parseInt(args[1+i])).setSex(null);
                    allPeople.get(Integer.parseInt(args[1+i])).setBirthDate(null);
                    allPeople.get(Integer.parseInt(args[1+i])).setName(null);
                }
            }; break;
            case "-i" :synchronized(allPeople){
                for (int i=0;i<args.length-1;i++) {
                    DateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                    Person p = allPeople.get(Integer.parseInt(args[1+i]));
                    String sexOutput =  p.getSex().equals(Sex.MALE) ? "м" : "ж";
                    System.out.printf("%s"+" "+"%s"+" "+"%s",p.getName(),sexOutput,outputFormat.format(p.getBirthDate()));
                    System.out.println("");
                }
            }

        }






    }
}
