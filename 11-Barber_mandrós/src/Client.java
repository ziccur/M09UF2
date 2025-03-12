public class Client {
    private String name;

    public Client(int id) {
        this.name = "Client-" + id;
    }

    public String getName() {
        return name;
    }

    public void tallarseElCabell() {
        System.out.println(name + " esta sent ates.");
    }
}