import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main {
    static Scanner scn = new Scanner(System.in);
    static List<Car> car = new ArrayList();
    static List<Identity> identity = new ArrayList();
    static int menu = 1;
    public static void LoadCar() {
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("in.txt"));
            while(true){
                line = br.readLine();
                if(line == null) break;
                String[] arrOfStr = line.split(",");
                car.add(new Car(arrOfStr[0],Integer.parseInt(arrOfStr[1]),arrOfStr[2],arrOfStr[3],arrOfStr[4],arrOfStr[5],arrOfStr[6],Integer.parseInt(arrOfStr[7]),Integer.parseInt(arrOfStr[8])));
            }
            br.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void LoadIdentity(){
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("identity.txt"));
            while (true){
                line = br.readLine();
                if (line==null) break;
                String[] arrOfStr = line.split(" ");
                identity.add(new Identity(arrOfStr[0],arrOfStr[1]));
            }
            br.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void AddCar(){

        System.out.println("Please input the Car Company!");
        String fau = scn.nextLine();
        String c_company = scn.nextLine();
        System.out.println("Please input the Car Model!");
        String c_model = scn.nextLine();
        System.out.println("Please input the Car Registration Number!");
        String c_reg = scn.nextLine();
        System.out.println("Please input the year the Car was made!");
        int c_year = scn.nextInt();
        System.out.println("Please input the colors of the Cars!");
        String fau1 = scn.nextLine();
        String c_color1 = scn.nextLine();
        String c_color2 = scn.nextLine();
        String c_color3 = scn.nextLine();
        System.out.println("Please input the price of the Car.");
        int c_price = scn.nextInt();
        System.out.println("Please input the quantity of the Car");
        int c_quantity = scn.nextInt();
        car.add(new Car(c_reg,c_year,c_color1,c_color2,c_color3,c_company,c_model,c_price,c_quantity));
    }

    public static void DeleteCar(){
        System.out.println("Please enter the Registration Number of the Car you want to delete");
        String fau = scn.nextLine();
        String CarName = scn.nextLine();
        int index = -1;
        for(int i = 0; i<car.size(); i++){
            Car demoCar = car.get(i);
            if(demoCar.getRegNo().equals(CarName)){
                index = i;
            }
        }
        if(index!=-1){
            car.remove(index);
        }
        else{
            System.out.println("Sorry! Car not found!");
        }
    }

    public static void ExitProgram(){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("in.txt"));
            for(int i = 0; i<car.size(); i++){
                Car demoCar = car.get(i);
                bw.write(demoCar.getCarInfo());
                bw.write("\n");
            }
            bw.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        //menu = 0;
    }

    public static void SearchCar(){
        System.out.println("What criteria do you want to use to search?");
        System.out.println("1. By Registration Number");
        System.out.println("2. By Car Make and Car Model");
        System.out.println("3. Back to Main Menu");

        int test = scn.nextInt();
        if(test==1){
            System.out.println("Please input the registration number!");
            String fau = scn.nextLine();
            String number = scn.nextLine();
            int index = -1;
            for(int i = 0; i<car.size(); i++){
                Car demoCar = car.get(i);
                if(demoCar.getRegNo().equalsIgnoreCase(number)){
                    index = i;
                    break;
                }
            }
            if(index!=-1){
                String info = car.get(index).getCarInfo();
                System.out.println(info);
            }
            else{
                System.out.println("No such car with this Registration Number");
            }
        }
        else if(test == 2) {
            System.out.println("Please input the Car Make!");
            String fau = scn.nextLine();
            String company = scn.nextLine();
            System.out.println("Please input the Car Model!");
            String model = scn.nextLine();
            if (model.equalsIgnoreCase("ANY")) {
                int check = 0;
                for (int i = 0; i < car.size(); i++) {
                    Car demoCar = car.get(i);
                    if (demoCar.getCompany().equalsIgnoreCase(company)) {
                        String info = car.get(i).getCarInfo();
                        System.out.println(info);
                        check = 1;
                    }
                }
                if (check == 0) {
                    System.out.println("No such car with this Car Make and Car Model");
                }
            } else {
                int check = 0;
                for (int i = 0; i < car.size(); i++) {
                    Car demoCar = car.get(i);
                    if (demoCar.getCompany().equalsIgnoreCase(company) && demoCar.getModel().equalsIgnoreCase(model)) {
                        String info = car.get(i).getCarInfo();
                        System.out.println(info);
                        check = 1;
                    }
                }
                if (check == 0) {
                    System.out.println("No such car with this Car Make and Car Model");
                }
            }
        }
    }


    public static void main(String[] args) {
        LoadCar();
        while(menu==1){
            System.out.println("(1) Search Cars");
            System.out.println("(2) Add Car");
            System.out.println("(3) Delete Car");
            System.out.println("(4) Exit System");
            int check = scn.nextInt();
            if(check==1){
                SearchCar();
                continue;
            }
            else if (check == 2){
                AddCar();
                continue;
            }
            else if (check == 3){
                DeleteCar();
                continue;
            }

            else if (check == 4){
                ExitProgram();
            }
            else{
                System.out.println("Error! Please choose between 1-4!");
            }
        }
    }


}
