public class Client {

    public Client(String serverAddress, int serverPort) {
        try {
            NetworkUtil networkUtil = new NetworkUtil(serverAddress, serverPort);
            new ReadThreadClient(networkUtil);
            new WriteThreadClient(networkUtil);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        //System.out.println("What do you want to do?");
        String serverAddress = "127.0.0.1";
        int serverPort = 44444;
        Client client = new Client(serverAddress, serverPort);
    }
}
