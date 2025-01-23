//package musiquest.Friends;
//
//import musiquest.Users.User;
//
//import javax.persistence.*;
//
//@Entity
//@Table
//public class Friends extends User{
//
//    @JoinColumn(name = "ID")
//    public int friendID1;
//
//    @JoinColumn(name = "ID")
//    public int friendID2;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//
//    public Friends(int friend1, int friend2) {
//        this.friendID1 = friend1;
//        this.friendID2 = friend2;
//    }
//
//    public Friends() {
//
//    }
//
//    public int getFriend1ID() {
//        return friendID1;
//    }
//
//    public void setFriend1ID(int friend1) {
//        this.friendID1 = friendID1;
//    }
//
//    public int getFriend2ID() {
//        return friendID2;
//    }
//
//    public void setFriend2(int friend2) {
//        this.friendID2 = friendID2;
//    }
//
//    public void setId(int id) {
//        this.ID = ID;
//    }
//
//    public int getId() {
//        return ID;
//    }
//}
