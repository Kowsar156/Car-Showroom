public class Car {
    private String reg_no;
    private int year;
    private String color_1;
    private String color_2;
    private String color_3;
    private String company;
    private String model;
    private int price;
    private int quantity;

    public Car(String reg_no, int year, String color_1, String color_2, String color_3, String company, String model, int price,int quantity){
        this.reg_no = reg_no;
        this.year = year;
        this.color_1 = color_1;
        this.color_2 = color_2;
        this.color_3 = color_3;
        this.company = company;
        this.model = model;
        this.price = price;
        this.quantity = quantity;
    }




    public String getRegNo(){
        return reg_no;
    }

    public int getYear(){
        return year;
    }

    public String getColor_1(){
        return color_1;
    }

    public String getColor_2(){
        return color_2;
    }

    public String getColor_3(){
        return color_3;
    }

    public String getCompany(){
        return company;
    }

    public String getModel(){
        return model;
    }

    public int getPrice(){
        return price;
    }

    public int getQuantity() { return quantity; }

    public void sold(){ quantity--; }

    public void setYear(int year) { this.year=year; }

    public void setColor_1(String year) { this.color_1=year; }

    public void setColor_2(String year) { this.color_2=year; }

    public void setColor_3(String year) { this.color_3=year; }

    public void setCompany(String year) { this.company=year; }

    public void setModel(String year) { this.model=year; }

    public void setPrice(int year) { this.price=year; }

    public void setQuantity(int year) { this.quantity=year; }

    public String getCarInfo(){
        return reg_no + "," + Integer.toString(year) + "," + color_1 + "," + color_2 + "," + color_3 + "," + company + "," + model + "," + Integer.toString(price) + "," + Integer.toString(quantity);
    }
}