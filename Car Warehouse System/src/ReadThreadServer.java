



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReadThreadServer implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;
    public HashMap<String, NetworkUtil> clientMap;


    public ReadThreadServer(HashMap<String, NetworkUtil> map, NetworkUtil networkUtil) {
        this.clientMap = map;
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                networkUtil.write("Please give your identity \n(1) Manufacturer \n(2) Viewer");
                int id = Integer.parseInt((String)networkUtil.read());
                if(id==2){
                    networkUtil.write("Please give your Username");
                    String username = (String) networkUtil.read();
                    if (username.equalsIgnoreCase("viewer")){
                        while (true){
                            networkUtil.write("(1) View All Cars \n(2) Search a Car \n(3) Buy a Car \n(4) Log Out");
                            int check = Integer.parseInt((String)networkUtil.read());
                            if(check==2){
                                Main.LoadCar();
                                networkUtil.write("What criteria do you want to use to search? \n(1) By Registration Number \n(2) By Car Make and Model \n");
                                int test = Integer.parseInt((String)networkUtil.read());
                                if(test==1){
                                    networkUtil.write("Please input the registration number!");
                                    String number = (String) networkUtil.read();
                                    int index = -1;
                                    for(int i = 0; i<Main.car.size(); i++){
                                        Car demoCar = Main.car.get(i);
                                        if(demoCar.getRegNo().equalsIgnoreCase(number)){
                                            index = i;
                                            break;
                                        }
                                    }
                                    if(index!=-1){
                                        String info = Main.car.get(index).getCarInfo();
                                        networkUtil.write(info);
                                    }
                                    else{
                                        networkUtil.write("No such car with this Registration Number");
                                    }
                                }
                                else if (test==2){
                                    networkUtil.write("Please input the Car Make!");
                                    String company = (String) networkUtil.read();
                                    networkUtil.write("Please input the Car Model!");
                                    String model = (String) networkUtil.read();
                                    check = 0;
                                    for (int i = 0; i < Main.car.size(); i++) {
                                        Car demoCar = Main.car.get(i);
                                        if (demoCar.getCompany().equalsIgnoreCase(company) && demoCar.getModel().equalsIgnoreCase(model)) {
                                            String info = Main.car.get(i).getCarInfo();
                                            networkUtil.write(info);
                                            check = 1;
                                        }
                                    }
                                    if (check == 0) {
                                        networkUtil.write("No such car with this Car Make and Car Model");
                                    }
                                }
                                Main.car.clear();
                                continue;
                            }

                            else if (check == 1){
                                Main.LoadCar();
                                for(int i=0; i<Main.car.size(); i++){
                                    String info = Main.car.get(i).getCarInfo();
                                    networkUtil.write(info);
                                }
                                Main.car.clear();
                                continue;
                            }
                            else if (check == 3){
                                Main.LoadCar();
                                networkUtil.write("Which car do you want to buy? Please input the Car Make first");
                                String company = (String) networkUtil.read();
                                networkUtil.write("Now please input the Car model");
                                String model = (String) networkUtil.read();
                                check = 0;
                                for (int i=0; i<Main.car.size(); i++){
                                    Car demoCar = Main.car.get(i);
                                    if (demoCar.getCompany().equalsIgnoreCase(company) && demoCar.getModel().equalsIgnoreCase(model) && demoCar.getQuantity()!=0) {
                                        networkUtil.write("Congratulations! The car is booked for you. Visit our nearby sales center withing 7 days to complete payment & get delivery. Thank you!");
                                        demoCar.sold();
                                        Main.ExitProgram();
                                        check = 1;
                                        break;
                                    }
                                    if (check != 1) {
                                        networkUtil.write("No such car with this Car Make and Car Model");
                                    }
                                }
                                Main.car.clear();
                                continue;
                            }

                            else if (check==4){
                                break;
                            }

                            else{
                                System.out.println("Error! Please choose between 1-4!");
                            }
                        }
                    }
                }
                if (id==1){
                    networkUtil.write("Please give your username");
                    String username = (String) networkUtil.read();
                    networkUtil.write("Now please give your password");
                    String password = (String) networkUtil.read();
                    Main.LoadIdentity();
                    int check = 0;
                    for (int i=0; i<Main.identity.size(); i++){
                        Identity demoIdentity = Main.identity.get(i);
                        if (demoIdentity.getUsername().equals(username)&&demoIdentity.getPassword().equals(password)){
                            check = 1;
                            break;
                        }
                    }
                    if (check==0){
                        networkUtil.write("Sorry! No such identity!");
                    }
                    if (check==1){
                        networkUtil.write("Welcome to the warehouse!");
                        while (true){
                            networkUtil.write("(1) View All Cars \n(2) Add a Car \n(3) Edit a Car \n(4) Delete a Car \n(5) Log Out");
                            check = Integer.parseInt((String)networkUtil.read());
                            if (check==1){
                                Main.LoadCar();
                                for(int i=0; i<Main.car.size(); i++){
                                    String info = Main.car.get(i).getCarInfo();
                                    networkUtil.write(info);
                                }
                                Main.car.clear();
                                continue;
                            }
                            else if (check==2){
                                Main.LoadCar();
                                networkUtil.write("Please input the Car Company!");
                                String c_company = (String)networkUtil.read();
                                System.out.println("Please input the Car Model!");
                                String c_model = (String)networkUtil.read();
                                System.out.println("Please input the Car Registration Number!");
                                String c_reg = (String)networkUtil.read();
                                System.out.println("Please input the year the Car was made!");
                                int c_year = Integer.parseInt((String)networkUtil.read());
                                System.out.println("Please input the colors of the Cars!");
                                String c_color1 = (String)networkUtil.read();
                                String c_color2 = (String)networkUtil.read();
                                String c_color3 = (String)networkUtil.read();
                                System.out.println("Please input the price of the Car.");
                                int c_price = Integer.parseInt((String)networkUtil.read());
                                System.out.println("Please input the quantity of the Car");
                                int c_quantity = Integer.parseInt((String)networkUtil.read());
                                Main.car.add(new Car(c_reg,c_year,c_color1,c_color2,c_color3,c_company,c_model,c_price,c_quantity));
                                Main.ExitProgram();
                                Main.car.clear();
                            }
                            else if (check==3){
                                Main.LoadCar();
                                networkUtil.write("Please input the registration number of the car you want to edit");
                                String reg = (String) networkUtil.read();
                                networkUtil.write("What do you want to edit? \n(1) Year Made \n(2) Color 1 \n(3) Color 2 \n(4) Color 3 \n(5) Company \n(6) Model \n(7) Price \n(8)Quantity");
                                int number = Integer.parseInt((String)networkUtil.read());
                                int index = -1;
                                for(int i = 0; i<Main.car.size(); i++){
                                    Car demoCar = Main.car.get(i);
                                    if(demoCar.getRegNo().equalsIgnoreCase(reg)){
                                        index = i;
                                        break;
                                    }
                                }
                                if(index == -1){
                                    networkUtil.write("There is no such car!");
                                    continue;
                                }
                                switch (number){
                                    case 1:
                                        networkUtil.write("Please input the changed year");
                                        int year = Integer.parseInt((String)networkUtil.read());
                                        Main.car.get(index).setYear(year);
                                    case 2:
                                        networkUtil.write("Please input the changed Color 1");
                                        String color = (String) networkUtil.read();
                                        Main.car.get(index).setColor_1(color);
                                    case 3:
                                        networkUtil.write("Please input the changed Color 2");
                                        color = (String) networkUtil.read();
                                        Main.car.get(index).setColor_2(color);
                                    case 4:
                                        networkUtil.write("Please input the changed Color 3");
                                        color = (String) networkUtil.read();
                                        Main.car.get(index).setColor_3(color);
                                    case 5:
                                        networkUtil.write("Please input the changed Company");
                                        String company = (String) networkUtil.read();
                                        Main.car.get(index).setCompany(company);
                                    case 6:
                                        networkUtil.write("Please input the changed model");
                                        String model = (String) networkUtil.read();
                                        Main.car.get(index).setModel(model);
                                    case 7:
                                        networkUtil.write("Please input the changed price");
                                        int price = Integer.parseInt((String)networkUtil.read());
                                        Main.car.get(index).setPrice(price);
                                    case 8:
                                        networkUtil.write("Please input the changed quantity");
                                        int quantity = Integer.parseInt((String)networkUtil.read());
                                        Main.car.get(index).setQuantity(quantity);
                                }
                                networkUtil.write("Congratulations! Change is successful!");
                                Main.ExitProgram();
                                Main.car.clear();
                            }
                            else if (check==4){
                                Main.LoadCar();
                                networkUtil.write("Please enter the Registration Number of the Car you want to delete");
                                String reg = (String) networkUtil.read();
                                int index = -1;
                                for(int i = 0; i<Main.car.size(); i++){
                                    Car demoCar = Main.car.get(i);
                                    if(demoCar.getRegNo().equals(reg)){
                                        index = i;
                                    }
                                }
                                if(index!=-1){
                                    Main.car.remove(index);
                                    networkUtil.write("Car deleted successfully!");
                                }
                                else{
                                    networkUtil.write("Sorry! Car not found!");
                                }
                                Main.ExitProgram();
                                Main.car.clear();
                            }
                            else if (check==5){
                                break;
                            }
                            else {
                                networkUtil.write("Error! Please choose between 1-5!");
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}




