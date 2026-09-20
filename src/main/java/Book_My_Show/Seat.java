package Book_My_Show;

public class Seat {
    //screen has seat
    int id;
    int row;
    SeatCatagory sc;
    int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setSc(SeatCatagory sc) {
        this.sc = sc;
    }

    public int getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public SeatCatagory getSc() {
        return sc;
    }
}
