package com.example.iconictravel;



public class ListItem  {
    public String id ,  weightOfBags , numberOfSeats  ,ticketPrice , airport , destination  , timeOfTravel  , data  ;

    public ListItem(String id, String airport, String destination, String timeOfTravel, String data, String weightOfBags, String numberOfSeats,
                    String ticketPrice) {
        this.id = id;
        this.weightOfBags = weightOfBags;
        this.numberOfSeats = numberOfSeats;
        this.ticketPrice = ticketPrice;
        this.airport = airport;
        this.destination = destination;
        this.timeOfTravel = timeOfTravel;
        this.data = data;
    }
}